<template>
    <div>
        <div>
            title : <span>{{ article.title }}</span>
        </div>
        <div>
            content: <p>{{ article.content }}</p>
        </div>

        <div v-if="init">
            createdAt: <span>{{ article.createdAt }}</span>
        </div>

    </div>
</template>

<script>
  import articleApi from "../../api/articleApi";

  export default {
    name: "Detail",
    data() {
      return {
        article: {},
        init: false
      }
    },
    async beforeCreate() {
      try {
        const articleId = this.$route.params.id;
        const result = await articleApi.getArticle.bind(this)(articleId);
        this.article = result.data;
        this.init = true;
      } catch (e) {
        console.log(e);
      }
    }
  }
</script>

<style scoped>

</style>