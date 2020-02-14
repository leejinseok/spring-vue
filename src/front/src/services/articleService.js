import articleApi from "../api/articleApi";
import commonUtil from "../utils/commonUtil";
import authApi from "../api/authApi";

export default {
    async getArticles({page = 0, size = 10}) {
        try {
            const accessToken = this.$cookie.get('accessToken');
            const authorization = accessToken ? commonUtil.getAuthenticationHeaderBearer(accessToken) : '';

            const result = await articleApi.getArticles({
                page,
                size
            }, authorization);
            return result.data;
        } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err.response);
        }
    },
    async getArticle(articleId) {
        try {
            const accessToken = this.$cookie.get('accessToken');
            const authorization = accessToken ? commonUtil.getAuthenticationHeaderBearer(accessToken) : null;

            const {data} = await articleApi.getArticle({articleId}, authorization);
            return data;
        } catch (err) {
            alert('문제가 발생하였습니다.');
        }
    },
    async removeArticle(articleId) {
        try {
            const authorization = commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken'));
            await articleApi.removeArticle({articleId}, authorization);
            await this.$router.push('/articles');
        } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err);
        }
    },
    async postArticle(data) {
        try {
            await articleApi.postArticle(data, commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
            await this.$router.push('/articles');
        } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err);
        }
    },
    async updateArticle(id, data) {
        try {
            await articleApi.updateArticle(id, data, commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
            await this.$router.push("/articles/" + id);
        } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err);
        }
    },
    async doseSessionHasPermission(user) {
        try {
            const result = await authApi.session(commonUtil.getAuthenticationHeaderBearer(this.$cookie.get('accessToken')));
            const session = result.data;
            if (session.id !== user.id) {
                alert("현재 사용자가 해당 게시글에 권한이 없습니다.");
                await this.$router.replace("/articles/" + this.$route.query.id);
            }
        } catch (err) {
            alert('문제가 발생하였습니다.');
            console.log(err);
        }
    }
}