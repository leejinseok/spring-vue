<template>
    <div>
        <router-link to="/">home</router-link>
        <form @submit="submit">
            <input type="email" v-model="email">
            <input type="password" v-model="password">
            <button type="submit">확인</button>
        </form>
    </div>
</template>

<script>
    import authApi from "../../api/authApi";
    import authService from "../../services/authService";

    export default {
        name: "Login",
        data() {
            return {
                email: '',
                password: ''
            }
        },
      beforeCreate() {
        authApi.session = authApi.session.bind(this);
        authService.login = authService.login.bind(this);
      },
      async created() {
        try {
          await authApi.session();
          await this.$router.replace('/articles');
        } catch (e) {
          console.log(e);
        }
      },
      methods: {
            submit: async function(evt) {
                evt.preventDefault();
                const { email, password } = this;
                await authService.login(email,password);
            },
        }
    }
</script>

<style scoped>

</style>