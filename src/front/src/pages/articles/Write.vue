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
  import articleService from "../../services/articleService";
  import authService from "../../services/authService";

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
      authService.banishIfUserUnAuthenticated = authService.banishIfUserUnAuthenticated.bind(this);
      articleService.postArticle = articleService.postArticle.bind(this);
      articleService.updateArticle = articleService.updateArticle.bind(this);
      articleService.getArticle = articleService.getArticle.bind(this);
      articleService.doseSessionHasPermission = articleService.doseSessionHasPermission.bind(this);
    },
    async created() {

      const id = this.$route.query.id;
      if (id) {
        await authService.banishIfUserUnAuthenticated();
        const { title, content, user } = await articleService.getArticle(id);
        await articleService.doseSessionHasPermission(user);

        this.title = title;
        this.content = content;
        this.isEdit = true;
      }
    },
    methods: {
        async create(evt) {
          evt.preventDefault();
          const {title, content} = this;
          await articleService.postArticle({title, content});
        },
        async update(evt) {
          evt.preventDefault();
          const {title, content} = this;
          await articleService.updateArticle(this.$route.query.id, {title, content});
        }
    }
  }
</script>

<style scoped>
    label {
        display: block;
    }
</style>