<template>
    <div>
        <form @submit="register">
            <input type="email" v-model="email" placeholder="Email"/>
            <input type="text" v-model="name" placeholder="Name"/>
            <input type="password" v-model="password" placeholder="Password"/>
            <button type="submit">확인</button>
        </form>
    </div>
</template>

<script>
    import authApi from "../../api/authApi";
    import authService from "../../services/authService";

    export default {
        name: "Register",
        data() {
            return {
                email: "",
                name: "",
                password: ""
            };
        },
        beforeCreate() {
            authApi.register = authApi.register.bind(this);
            authApi.session = authApi.session.bind(this);

            authService.register = authService.register.bind(this);
            authService.progressIfUserAuthenticated = authService.progressIfUserAuthenticated.bind(this);
        },
        async created() {
            await authService.progressIfUserAuthenticated();
        },
        methods: {
            register: async function (evt) {
                evt.preventDefault();
                const {email, name, password} = this;
                await authService.register({email, name, password});
            }
        }
    };
</script>

<style scoped>
</style>