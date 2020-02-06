import axios from "axios";

export default {
    login(data) {
        const {email, password} = data;
        return axios({
            method: 'post',
            url: '/api/auth/login',
            data: {
                email,
                password
            }
        });
    },
    session(authorization) {
        return axios({
            method: 'get',
            url: '/api/users',
            headers: {
                'Authorization': authorization
            }
        });
    },
    register(data) {
        const {email, name, password} = data;
        return axios({
            method: 'post',
            url: '/api/auth/register',
            data: {
                email,
                name,
                password
            }
        });
    },
}

