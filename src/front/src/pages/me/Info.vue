<template>
    <div>
        <div v-if="user">
            <table>
                <tr>
                    <td>이름</td>
                    <td>{{ user.name }}</td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>{{ user.email }}</td>
                </tr>
                <tr>
                    <td>가입일</td>
                    <td>{{ user.createdAt || '-' }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script>
    import authService from "../../services/authService";

    export default {
        name: "Info",
        data() {
            return {
                user: null,
                init: false
            };
        },
        async beforeCreate() {
            authService.session = authService.session.bind(this);
            authService.banishIfUserUnAuthenticated = authService.banishIfUserUnAuthenticated.bind(this);
        },
        async created() {
            await authService.banishIfUserUnAuthenticated();

            try {
                const {data} = await authService.session();
                this.user = data;
            } catch (err) {
                console.log(err);
                this.$router.back();
            }
        }
    };
</script>

<style scoped>
</style>