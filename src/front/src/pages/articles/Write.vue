<template>
    <div>
        <form @submit="submit">
            <div class="row">
                <label for="title">Title</label>
                <input type="text" id="title" v-model="title">
            </div>
            <div class="row">
                <label for="content">Content</label>
                <textarea id="content" v-model="content"></textarea>
            </div>
            <div class="row">
                <button type="submit">확인</button>
            </div>
        </form>
    </div>
</template>

<script>
    import articleApi from "../../api/articleApi";
    import authentication from "../../middlewares/authentication";

  export default {
    name: "Write",
    data() {
      return {
        title: '',
        content: ''
      }
    },
    async beforeCreate() {
      try {
        authentication.bind(this)();
      } catch (e) {
        alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
        await this.$router.replace('/');
      }
    },
    methods: {
        submit: async function(evt) {
          evt.preventDefault();

          const {title, content} = this;

          const data = {
            title,
            content
          };

          try {
            await articleApi.postArticle.bind(this)(data);
            await this.$router.push('/articles');
          } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err);
          }

        }
    }
  }
</script>

<style scoped>
    label {
        display: block;
    }
</style>