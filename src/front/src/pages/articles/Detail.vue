<template>
    <div>
        <div>
            title : <span v-if="!pending">{{ article.title }}</span>
        </div>
        <div>
            content: <p v-if="!pending">{{ article.content }}</p>
        </div>

    </div>
</template>

<script>
  import articleApi from "../../api/articleApi";

  export default {
    name: "Detail",
    data() {
      return {
        article: null,
        pending: true
      }
    },
    async beforeCreate() {
      try {
        const result = await articleApi.getArticle.bind(this)(this.$route.params.id);
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