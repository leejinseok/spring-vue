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

function getArticle(id) {
  return axios({
    url: '/api/articles/' + id,
    headers: {
      'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
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

function updateArticle(id, {title = '', content = ''}) {
  return axios({
    url: '/api/articles/' + id,
    method: 'put',
    headers: {
      'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
    },
    data: {
      title,
      content
    }
  });
}

function removeArticle(id) {

  return axios({
    url: '/api/articles/' + id,
    headers: {
      'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
    },
    method: 'delete'
  });
}

export default {
  getArticles,
  getArticle,
  postArticle,
  updateArticle,
  removeArticle
}