import axios from "axios";

export default async function () {
  await axios({
    method: 'get',
    url: '/api/users',
    headers: {
      'Authorization': 'Bearer ' + this.$cookie.get('accessToken')
    }
  });
}