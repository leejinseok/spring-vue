import axios from "axios";

export default async function () {
  try {
    const token = this.$cookie.get('accessToken');
    const result = await axios({
      method: 'get',
      url: '/api/users',
      headers: {
        'Authorization': 'Bearer ' + token
      }
    });
  } catch (e) {

  }
}