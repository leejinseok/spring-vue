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
    import authApi from "../../api/authApi";

    export default {
        name: "Info",
        data() {
            return {
                user: null,
                init: false
            };
        },
        async beforeCreate() {
            authApi.bind(this);

            try {
                const {data} = await authApi.session();
                this.user = data;
            } catch (err) {
                await this.$router.replace("/auth/login");
            }
        }
    };
</script>

<style scoped>
</style>