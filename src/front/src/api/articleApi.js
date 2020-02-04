import axios from 'axios';
import commonUtil from "../utils/commonUtil";

function getArticles({page = 0, size = 10, q = ''}) {

  return axios({
    url: '/api/articles',
    headers: {
      'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
    },
    params: {
      page,
      size,
      q
    }
  });
}

function postArticle({title = '', content = ''}) {

  return axios({
    url: '/api/articles',
    method: 'post',
    headers: {
      'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
    },
    data: {
      title,
      content
    }
  });
}

export default {
  getArticles,
  postArticle
}