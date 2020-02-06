<template>
    <div>
        <router-link to="/auth/login">로그인</router-link>
        <br/>
        <router-link to="/auth/register">회원가입</router-link>
        <br/>
    </div>
</template>

<script>
    import authApi from "../api/authApi";
    import authService from "../services/authService";

    export default {
        name: "Welcome",
        async beforeCreate() {
            authApi.session = authApi.session.bind(this);
            authService.progressIfUserAuthenticated = authService.progressIfUserAuthenticated.bind(this);
        },
        async created() {
            await authService.progressIfUserAuthenticated();
        }
    };
</script>

<style scoped>
</style>