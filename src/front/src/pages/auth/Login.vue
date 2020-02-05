<template>
    <div>
        <router-link to="/">home</router-link>

        <form @submit="submit">
            <input type="email" v-model="email">
            <input type="password" v-model="password">
            <button type="submit">확인</button>
        </form>

        <button type="button" @click="session">세션확인</button>
    </div>
</template>

<script>
    import authApi from "../../api/authApi";

    export default {
        name: "Login",
        data() {
            return {
                email: '',
                password: ''
            }
        },
      async beforeCreate() {
          authApi.bind(this);



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

                try {
                    const result = await authApi.login({email, password});
                    const { token } = result.data;
                    this.$cookie.set('accessToken', token, 1000);
                    await this.$router.push('/articles');
                } catch (err) {
                    console.log(err);
                }

            },
            session: async function(evt) {
                try {
                    const result = await authApi.session();
                    console.log(result);
                } catch (err) {
                    console.log(err);
                }
            }
        }
    }
</script>

<style scoped>

</style>