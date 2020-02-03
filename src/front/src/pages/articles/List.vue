<template>
    <div>
        <article v-for="article in articles" v-bind:key="article.id">
            <span>{{ article.title }}</span>
            <span>{{ article }}</span>
        </article>

        <div>
            <router-link to="/articles/write">글쓰기</router-link>
        </div>
    </div>
</template>

<script>
    import articleApi from "../../api/articleApi";
    import authApi from "../../api/authApi";

    export default {
        name: "List",
        data() {
          return {
            articles: [],
            pending: false
          }
        },
        async beforeCreate() {
          try {
            await authApi.session(this.$cookie.get('accessToken'));
          } catch (e) {
            alert('토큰이 존재하지 않거나 올바르지 않은 토큰입니다.');
            await this.$router.push('/');
          }

          try {
            const result = await articleApi.getArticles.bind(this)({});
            this.articles = result.data;
          } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err.response);
          }
        }
    }
</script>

<style scoped>

</style>