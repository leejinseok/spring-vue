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

        <div v-if="article.isOwn">
            <button type="button" @click="remove">삭제</button>
            <router-link :to="{name: 'WriteArticle', query: {id: article.id}}">수정</router-link>
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
    },
    methods: {
      async remove() {
        const articleId = this.$route.params.id;
        if(!confirm('정말 삭제하시겠습니까?')) return;

        try {
          await articleApi.removeArticle.bind(this)(articleId);
          await this.$router.push('/articles');
        } catch (e) {
          alert('문제가 발생하였습니다.');
          console.log(e);
        }

      }
    }
  }
</script>

<style scoped>

</style>