import axios from "axios";

function login(data) {
  const { email, password } = data;
  return axios({
    method: 'post',
    url: '/api/auth/login',
    data: {
      email,
      password
    }
  });
}

function currentUser(token) {
  return axios({
    method: 'get',
    url: '/api/users',
    headers: {
      'Authorization': 'Bearer ' + token
    }
  });
}

function register(data) {
  const { email, name, password } = data;
  return axios({
    method: 'post',
    url: '/api/auth/register',
    data
  });
}


export default {
  login,
  currentUser,
  register
}