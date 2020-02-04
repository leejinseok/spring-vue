<template>
    <div v-if="!pending">
        <article v-for="article in articles" v-bind:key="article.id">
            <router-link :to="{name: 'DetailArticle', params: {id: article.id}}"><span>{{ article.title }}</span></router-link>
        </article>

        <div>
            <router-link to="/articles/write">글쓰기</router-link>
        </div>
    </div>
</template>

<script>
    import articleApi from "../../api/articleApi";
    import authentication from "../../middlewares/authentication";

    export default {
        name: "List",
        data() {
          return {
            articles: [],
            pending: true
          }
        },
        async beforeCreate() {
          try {
            await authentication.bind(this)();
          } catch (e) {
            alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
            await this.$router.replace('/');
            return;
          }

          try {
            const result = await articleApi.getArticles.bind(this)({});
            this.articles = result.data;
            this.pending = false;
          } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err.response);
          }
        }
    }
</script>

<style scoped>

</style>