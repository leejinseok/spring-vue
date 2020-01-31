import axios from 'axios';

function getArticles({page = 0, size = 10, q = ''}) {
  const accessToken = this.$cookie.get('accessToken');

  return axios({
    url: '/api/articles',
    headers: {
      'Authorization': 'Bearer ' + accessToken,
    },
    params: {
      page,
      size,
      q
    }
  });
}

export default {
  getArticles
}