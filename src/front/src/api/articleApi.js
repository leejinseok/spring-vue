import axios from 'axios';

function getArticles(page, size, q) {
  return axios({
    url: '/api/articles',
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