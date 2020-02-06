<template>
    <div>
        <router-link to="/">home</router-link>
        <form @submit="submit">
            <input type="email" v-model="email"/>
            <input type="password" v-model="password"/>
            <button type="submit">확인</button>
        </form>
    </div>
</template>

<script>
    import authApi from "../../api/authApi";
    import authService from "../../services/authService";

    export default {
        name: "Login",
        beforeCreate() {
            authApi.session = authApi.session.bind(this);
            authService.login = authService.login.bind(this);
            authService.progressIfUserAuthenticated = authService.progressIfUserAuthenticated.bind(this);

        },
        data() {
            return {
                email: "",
                password: ""
            };
        },
        async created() {
            await authService.progressIfUserAuthenticated()
        },
        methods: {
            submit: async function (evt) {
                evt.preventDefault();
                const {email, password} = this;
                await authService.login(email, password);
            }
        }
    };
</script>

<style scoped>
</style>