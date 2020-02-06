export default {
    getAuthenticationHeaderBearer(accessToken) {
        return 'Bearer ' + accessToken;
    }
};