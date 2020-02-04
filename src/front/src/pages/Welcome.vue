<template>
   <div>
       <router-link to="/auth/login">로그인</router-link> <br>
       <router-link to="/auth/register">회원가입</router-link> <br>
   </div>
</template>

<script>
    import authApi from "../api/authApi";

    export default {
        name: "Welcome",
      async beforeCreate() {
        const accessToken = this.$cookie.get('accessToken');
        try {
          await authApi.session.bind(this)(accessToken);
          await this.$router.replace('/articles');
        } catch (e) {
          console.log(e);
        }
      }
    }
</script>

<style scoped>

</style>