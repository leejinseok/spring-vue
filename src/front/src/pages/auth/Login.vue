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

                // try {
                //     const result = await authApi.login({email, password});
                //     const { token } = result.data;
                //     this.$cookie.set('accessToken', token, 1000);
                //     await this.$router.push('/articles');
                // } catch (err) {
                //     const message = err.response.data.message;
                //     if (~message.indexOf('패스워드')) {
                //       alert('패스워드가 일치하지 않습니다.');
                //     }
                // }

            },
        }
    }
</script>

<style scoped>

</style>