function getAuthenticationHeaderBearer() {
  return 'Bearer ' + this.$cookie.get('accessToken');
}

export default {
  getAuthenticationHeaderBearer
}