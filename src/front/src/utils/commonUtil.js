export default {
    getAuthenticationHeaderBearer(accessToken) {
        return 'Bearer ' + accessToken;
    },
    pagination(totalElements, totalPages, pageSize) {
        // 페이지 그룹 사이즈 (5, 10)
        // 시작 페이지, 마침 페이지
        // 최초 페이지, 마지막 페이지
        // 이전페이지 여부, 다음 페이지 여부
        // 현재 페이지
    }
};