<template>
    <div>
        <div>
            title : <span>{{ article.title }}</span>
        </div>
        <div>
            content: <p>{{ article.content }}</p>
        </div>
        <div>
            createdAt: {{ article.createdAt }}
        </div>
    </div>
</template>

<script>
  import articleApi from "../../api/articleApi";

  export default {
    name: "Detail",
    data() {
      return {
        article: {
          title: '',
          content: '',
          createdAt: '',
          isOwn: null
        },
        pending: true
      }
    },
    async beforeCreate() {
      try {
        const articleId = this.$route.params.id;
        const result = await articleApi.getArticle.bind(this)(articleId);
        this.article = result.data;
        this.pending = false;
      } catch (e) {
        console.log(e);
      }
    }
  }
</script>

<style scoped>

</style>