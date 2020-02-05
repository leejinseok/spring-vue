<template>
    <div v-if="!pending">
        <article v-for="article in articles" v-bind:key="article.id">
            <router-link :to="{name: 'DetailArticle', params: {id: article.id}}">
                <span>{{ article.title }}</span>
            </router-link>
            <span @click="clickUser">{{ article.user.name }}</span>
        </article>

        <br>

        <div>
            <router-link to="/articles/write">글쓰기</router-link>
        </div>

        <div>
            <button type="button" @click="logout">로그아웃</button>
        </div>
    </div>
</template>

<script>
    import articleApi from "../../api/articleApi";
    import authentication from "../../middlewares/authentication";
    import authApi from "../../api/authApi";

    export default {
        name: "List",
        data() {
          return {
            articles: [],
            pending: true
          }
        },
        async beforeCreate() {
          authentication.bind(this);
          articleApi.bind(this);
          authApi.bind(this);

          try {
            await authentication.session();
          } catch (e) {
            alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
            await this.$router.replace('/');
            return;
          }

          try {
            const result = await articleApi.getArticles({});
            this.articles = result.data;
            this.pending = false;
          } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err.response);
          }
        },
        methods: {
          async logout() {
            if (!confirm('정말 로그아웃 하시겠습니까?')) return;

            try {
              await authApi.logout();
              await this.$router.push('/');
            } catch (e) {
                console.log(e);
            }

          },
          clickUser(evt) {
            console.log(evt.target);
          }
        }
    }
</script>

<style scoped>

</style>