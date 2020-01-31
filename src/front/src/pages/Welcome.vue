<template>
   <div>
       home
       <router-link to="/auth/login">로그인</router-link>
       <router-link to="/auth/register">회원가입</router-link>
       <router-link to="/articles">게시글</router-link>
   </div>
</template>

<script>
  export default {
    name: "Welcome"
  }
</script>

<style scoped>

</style>