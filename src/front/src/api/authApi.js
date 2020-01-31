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


export default {
  login,
  currentUser
}