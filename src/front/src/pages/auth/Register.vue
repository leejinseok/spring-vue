<template>
    <div>
        <form @submit="register">
            <input type="email" v-model="email" placeholder="Email">
            <input type="text" v-model="name" placeholder="Name">
            <input type="password" v-model="password" placeholder="Password">
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
          console.log(result.data);
        } catch (err) {
            console.log(err);
        }
      }
    }
  }
</script>

<style scoped>

</style>