import axios from 'axios';

export default {
    getArticles({page = 0, size = 10, q = ''}, authorization) {

        const request = axios({
            url: '/api/articles',
            params: {
                page,
                size,
                q
            }
        });

        if (authorization) {
            request.headers = {
                'Authorization': authorization
            };
        }

        return request;
    },
    getArticle({articleId}, authorization) {
        const request = axios({
            url: '/api/articles/' + articleId,

        });

        if (authorization) {
            request.headeres = {
                'Authorization': authorization
            }
        }

        return request;

    },
    postArticle({title = '', content = ''}, authorization) {

        return axios({
            url: '/api/articles',
            method: 'post',
            headers: {
                'Authorization': authorization
            },
            data: {
                title,
                content
            }
        });
    },
    updateArticle(id, {title = '', content = ''}, authorization) {
        return axios({
            url: '/api/articles/' + id,
            method: 'put',
            headers: {
                'Authorization': authorization
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