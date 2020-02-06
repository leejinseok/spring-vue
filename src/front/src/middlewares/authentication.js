import axios from "axios";
import commonUtil from "../utils/commonUtil";

export default {
  async session() {
    try {
      await axios({
        method: 'get',
        url: '/api/users',
        headers: {
          'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
        }
      });
    } catch (e) {
      alert('토큰이 존재하지 않거나 유효하지 않은 토큰입니다.');
      await this.$router.replace('/');
    }

  }
}