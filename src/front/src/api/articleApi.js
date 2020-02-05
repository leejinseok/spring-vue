import axios from 'axios';
import commonUtil from "../utils/commonUtil";

export default {
  bind(context) {
    this.getArticle = this.getArticle.bind(context);
    this.getArticles = this.getArticles.bind(context);
    this.postArticle = this.postArticle.bind(context);
    this.updateArticle = this.updateArticle.bind(context);
    this.removeArticle = this.removeArticle.bind(context);
  },
  getArticles({page = 0, size = 10, q = ''}) {

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
  },
  getArticle(id) {
    return axios({
      url: '/api/articles/' + id,
      headers: {
        'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
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
  removeArticle(id) {

    return axios({
      url: '/api/articles/' + id,
      headers: {
        'Authorization': commonUtil.getAuthenticationHeaderBearer.bind(this)()
      },
      method: 'delete'
    });
  }
}