import axios from "axios";


export default {
  bind(context) {
    this.login = this.login.bind(context);
    this.session = this.session.bind(context);
    this.register = this.register.bind(context);
    this.logout = this.logout.bind(context);
  },
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
  session() {
    return axios({
      method: 'get',
      url: '/api/users',
      headers: {
        'Authorization': 'Bearer ' + this.$cookie.get('accessToken')
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

