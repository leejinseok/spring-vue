import authApi from "../api/authApi";
import commonUtil from "../utils/commonUtil";

export default {
    async login(email, password) {
        try {
            const result = await authApi.login({email, password});
            const {token} = result.data;
            this.$cookie.set('accessToken', token, 1000);
            await this.$router.push('/articles');
        } catch (err) {
            const message = err.response.data.message;
            if (~message.indexOf('패스워드')) {
                alert('패스워드가 일치하지 않습니다.');
            }
        }
    },
    async register(data) {
        try {
            await authApi.register(data);
            alert('가입이 완료되었습니다. 로그인 해 주세요');
            await this.$router.push('/auth/login');
        } catch (err) {
            if (err.response.status === 409) {
                alert('이미 존재하는 이메일입니다.');
            }
        }
    },
    session() {
        return authApi.session(commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));

    },
    async logout() {
        try {
            this.$cookie.set('accessToken', null, 0);
            location.reload();
        } catch (err) {
            console.log(err);
        }
    },
    async progressIfUserAuthenticated() {
        try {
            await authApi.session(commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
            await this.$router.replace('/articles');
        } catch (err) {
            console.log(err);
        }
    },
    async banishIfUserUnAuthenticated() {
        try {
            await authApi.session(commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
        } catch (err) {
            alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
            console.log(err);
            await this.$router.replace('/');
        }
    },

}