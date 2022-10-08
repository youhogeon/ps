const axios = require('axios');
const logger = require('../utils/logger');

axios.defaults.baseURL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";
axios.defaults.headers.common['Content-Type'] = "application/json";
axios.defaults.headers.common['X-Auth-Token'] = "40308bb6f43c356deec02445087c2ba2";

const requestLogger = (e) => {
    logger('😧 Exception', e);
}

module.exports.get = async (url, Authorization = '', params = {}) => {
    const response = await axios.get(url, {
        params,
        headers: { Authorization }
    }).catch(requestLogger);

    return response?.data;
}

module.exports.post = async (url, Authorization = '', params = {}) => {
    const response = await axios.post(url, params, {
        headers: { Authorization }
    }).catch(requestLogger);

    return response?.data;
}

module.exports.put = async (url, Authorization = '', params = {}) => {
    const response = await axios.put(url, params, {
        headers: { Authorization }
    }).catch(requestLogger);

    return response?.data;
}