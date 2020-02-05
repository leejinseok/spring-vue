import axios from "axios";

export default {
  bind(context) {
    this.session = this.session.bind(context);
  },
  async session() {
    await axios({
      method: 'get',
      url: '/api/users',
      headers: {
        'Authorization': 'Bearer ' + this.$cookie.get('accessToken')
      }
    });
  }
}