
export default {
  getAuthenticationHeaderBearer() {
    return 'Bearer ' + this.$cookie.get('accessToken');
  }
};