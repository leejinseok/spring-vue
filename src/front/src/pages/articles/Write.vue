<template>
    <div>
        <form @submit="isEdit ? update($event) : create($event)">
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
        content: '',
        isEdit: false
      }
    },
    async beforeCreate() {
      try {
        authentication.bind(this)();
      } catch (err) {
        alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
        await this.$router.replace('/');
        return;
      }

      const id = this.$route.query.id;

      if (id) {
        try {
          const result = await articleApi.getArticle.bind(this)(id);
          const {title, content} = result.data;
          this.title = title;
          this.content = content;
          this.isEdit = true;
        } catch (err) {
          console.log(err);
        }
      }
    },
    methods: {
        create: async function(evt) {
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

        },
        async update(evt) {
          evt.preventDefault();

          const id = this.$route.query.id;

          const {title, content} = this;
          const data = {
            title,
            content
          };

          try {
            await articleApi.updateArticle.bind(this)(id, data);
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