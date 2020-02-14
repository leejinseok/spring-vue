<template>
    <div>
        <Header :session="session"/>
        <div class="article-wrapper" v-if="!pending">
            <article v-for="article in articles" v-bind:key="article.id">
                <router-link :to="{name: 'DetailArticle', params: {id: article.id}}">
                    <p class="title">{{ article.title }}</p>
                </router-link>
                <span @click="clickUser">{{ article.user.name }}</span>
            </article>

            <br/>

            <div v-if="user">
                <div>
                    <router-link to="/articles/write">글쓰기</router-link>
                </div>

                <div>
                    <button type="button" @click="logout">로그아웃</button>
                </div>

                <div>
                    <router-link to="/me">My</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import authService from "../../services/authService";
    import articleService from "../../services/articleService";
    import Header from '../../components/common/Header';

    export default {
        name: "List",
        components: {
          Header
        },
        props: {
            startSpin: Function,
            stopSpin: Function,
            setSession: Function,
            session: Object
        },
        data() {
            return {
                articles: [],
                pending: true,
                user: null
            };
        },
        async beforeCreate() {
            articleService.getArticles = articleService.getArticles.bind(this);
            authService.logout = authService.logout.bind(this);
            authService.session = authService.session.bind(this);
        },
        async created() {
            this.startSpin();

            try {
                const { data } = await authService.session();
                this.user = data;
                this.setSession(data);
            } catch (err) {
                console.log(err);
            }

            const articles = await articleService.getArticles({});

            this.articles = articles.content;
            this.pending = false;

            this.stopSpin(true);
        },
        methods: {
            async logout() {
                if (!confirm("정말 로그아웃 하시겠습니까?")) return;
                await authService.logout();
            },
            clickUser(evt) {
                console.log(evt.target);
            }
        }
    };
</script>

<style scoped>
    article {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 16px;
    }

    article a {
        font-size: 18px;
        text-align: left;
    }

    article a:hover {
        color: black;
    }

    .article-wrapper {
        padding: 20px 16px;
        box-sizing: border-box;
        max-width: 900px;
        margin-left: auto;
        margin-right: auto;
    }
</style>