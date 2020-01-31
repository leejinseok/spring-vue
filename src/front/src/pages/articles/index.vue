<template>
    <div>
        <div v-for="article in articles" v-bind:key="article.id">
            {{ article.title }}
        </div>
    </div>
</template>

<script>
    import articleApi from "../../api/articleApi";
    export default {
        name: "index",
        data() {
          return {
            articles: [],
            pending: false
          }
        },
        async beforeCreate() {
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