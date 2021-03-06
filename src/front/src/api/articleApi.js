import axios from 'axios';

export default {
    getArticles({page, size, q = ''}, authorization) {

        const options = {
            url: '/api/articles',
            params: {
                page,
                size,
                q
            }
        };

        if (authorization) {
            options.headers = {
                'Authorization': authorization
            };
        }

        return axios(options);
    },
    getArticle({articleId}, authorization) {
        const options = {
            url: '/api/articles/' + articleId,
        };

        if (authorization) {
            options.headers = {
                'Authorization': authorization
            }
        }

        return axios(options);
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