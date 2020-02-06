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
      authentication.session = authentication.session.bind(this);
      articleApi.getArticle = articleApi.getArticle.bind(this);
      articleApi.postArticle = articleApi.postArticle.bind(this);
      articleApi.updateArticle = articleApi.updateArticle.bind(this);
    },
    async created() {
      await authentication.session();

      const id = this.$route.query.id;

      if (id) {
        try {
          const { data } = await articleApi.getArticle(id);
          const {title, content} = data;
          this.title = title;
          this.content = content;
          this.isEdit = true;
        } catch (err) {
          alert('문제가 발생하였습니다.');
          console.log(err);
        }
      }
    },
    methods: {
        async create(evt) {
          evt.preventDefault();

          const {title, content} = this;

          const data = {
            title,
            content
          };

          try {
            await articleApi.postArticle(data);
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
            await articleApi.updateArticle(id, data);
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