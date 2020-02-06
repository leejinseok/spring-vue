import articleApi from "../api/articleApi";
import commonUtil from "../utils/commonUtil";

export default {
  async getArticles({page = 0, size = 10}) {
    try {
      const result = await articleApi.getArticles({page, size}, commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
      return result.data;
    } catch (err) {
      alert('문제가 발생하였습니다.');
      console.log(err.response);
    }
  },
  async getArticle(articleId) {
    try {
      const authorization = commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken'));
      const result = await articleApi.getArticle({articleId}, authorization);
      return result.data;
    } catch (e) {
      alert('문제가 발생하였습니다.');
      console.log(e);
    }
  },
  async removeArticle(articleId) {
    try {
      const authorization = commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken'));
      await articleApi.removeArticle({articleId}, authorization);
      await this.$router.push('/articles');
    } catch (e) {
      alert('문제가 발생하였습니다.');
      console.log(e);
    }
  }
}