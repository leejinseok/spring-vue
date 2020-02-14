<template>
    <div>
        <div></div>
        <div>
            title :
            <span>{{ article.title }}</span>
        </div>
        <div>
            content:
            <p>{{ article.content }}</p>
        </div>

        <div v-if="init">
            createdAt:
            <span>{{ article.createdAt }}</span>
        </div>

        <div v-if="article.isOwn">
            <button type="button" @click="remove">삭제</button>
            <router-link :to="{name: 'WriteArticle', query: {id: article.id}}">수정</router-link>
        </div>
    </div>
</template>

<script>
    import authService from "../../services/authService";
    import articleService from "../../services/articleService";

    export default {
        name: "Detail",
        data() {
            return {
                article: {},
                init: false,
                test: ""
            };
        },
        async beforeCreate() {
            articleService.getArticle = articleService.getArticle.bind(this);
            articleService.removeArticle = articleService.removeArticle.bind(this);
        },
        async created() {
            this.article = await articleService.getArticle(this.$route.params.id);
            this.init = true;
        },
        methods: {
            async remove() {
                const articleId = this.$route.params.id;
                if (!confirm("정말 삭제하시겠습니까?")) return;

                await articleService.removeArticle(articleId);
            }
        }
    };
</script>

<style scoped>
</style>