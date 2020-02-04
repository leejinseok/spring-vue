import axios from "axios";


export default {
  login(data) {
    const { email, password } = data;
    return axios({
      method: 'post',
      url: '/api/auth/login',
      data: {
        email,
        password
      }
    });
  },
  session(token) {
    return axios({
      method: 'get',
      url: '/api/users',
      headers: {
        'Authorization': 'Bearer ' + token
      }
    });
  },
  register(data) {
    const { email, name, password } = data;
    return axios({
      method: 'post',
      url: '/api/auth/register',
      data: {
        email,
        name,
        password
      }
    });
  },
  logout() {
    this.$cookie.set('accessToken', null, 0);
  }
}