<template>
    <header>
        <div class="header__inner">
            <span>
                게시판
            </span>

            <ul class="clearfix" v-if="!authenticated">
                <li>
                    <router-link to="/auth/login">로그인</router-link>
                </li>
                <li>
                    <router-link to="/auth/register">회원가입</router-link>
                </li>
            </ul>
    </div>
    </header>
</template>

<script>
    export default {
        name: "Header",
        props: {
            session: Object
        },
        computed: {
            authenticated: function () {
                return this.session.id;
            }
        }
    }
</script>

<style scoped>
header {
    padding: 4px;
    box-sizing: border-box;
}

.header__inner {
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 900px;
    margin-left: auto;
    margin-right: auto;
}

ul li {
    float: left;
    margin-left: 4px;
}
</style>