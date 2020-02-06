import axios from 'axios';
import commonUtil from "../utils/commonUtil";

export default {
  getArticles({page = 0, size = 10, q = ''}, authorization) {

    return axios({
      url: '/api/articles',
      headers: {
        'Authorization': authorization
      },
      params: {
        page,
        size,
        q
      }
    });
  },
  getArticle({articleId}, authorization) {
    return axios({
      url: '/api/articles/' + articleId,
      headers: {
        'Authorization': authorization
      }
    });
  },
  postArticle({title = '', content = ''}) {

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
  },
  updateArticle(id, {title = '', content = ''}) {
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
  },
  removeArticle({articleId}, authorization) {

    return axios({
      url: '/api/articles/' + articleId,
      headers: {
        'Authorization': authorization
      },
      method: 'delete'
    });
  }
}