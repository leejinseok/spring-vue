<template>
    <div>
        <Header />
        <div v-if="!pending">
            <article v-for="article in articles" v-bind:key="article.id">
                <router-link :to="{name: 'DetailArticle', params: {id: article.id}}">
                    <span>{{ article.title }}</span>
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
            stopSpin: Function
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
            // authService.banishIfUserUnAuthenticated = authService.banishIfUserUnAuthenticated.bind(
            //     this
            // );
        },
        async created() {
            this.startSpin();

            try {
                const { data } = await authService.session();
                this.user = data;
            } catch (err) {
                console.log(err);
            }

            this.articles = await articleService.getArticles({});
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
</style>