import authApi from "../api/authApi";
import authentication from "../middlewares/authentication";
import commonUtil from "../utils/commonUtil";

export default {
  async login(email, password) {
    try {
      const result = await authApi.login({email, password});
      const { token } = result.data;
      this.$cookie.set('accessToken', token, 1000);
      await this.$router.push('/articles');
    } catch (err) {
      const message = err.response.data.message;
      if (~message.indexOf('패스워드')) {
        alert('패스워드가 일치하지 않습니다.');
      }
    }
  },
  async logout() {
    try {
      this.$cookie.set('accessToken', null, 0);
      await this.$router.push('/');
    } catch (e) {
      console.log(e);
    }
  },
  async progressIfUserAuthenticated() {

  },
  async banishIfUserUnAuthenticated() {
    try {
      await authApi.session(commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
    } catch (e) {
      alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
      await this.$router.replace('/');
      return;
    }
  }
}