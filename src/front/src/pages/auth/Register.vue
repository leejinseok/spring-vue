<template>
    <div>
        <form @submit="register">
            <input type="email" v-model="email" placeholder="Email">
            <input type="text" v-model="name" placeholder="Name">
            <input type="password" v-model="password" placeholder="Password">
            <button type="submit">확인</button>
        </form>
    </div>
</template>

<script>
    import authApi from "../../api/authApi";
    export default {
        name: "Register",
        data() {
            return {
              email: '',
              name: '',
              password: ''
            }
        },
        methods: {
            register: async function(evt) {
                evt.preventDefault();
                const { email, name, password } = this;
                try {
                    const result = await authApi.register({email, name, password});
                } catch (err) {
                    if (err.response.status === 409) {
                      alert('이미 존재하는 이메일입니다.');
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>