const axios = require('axios');
const logger = require('../services/logger');

axios.defaults.baseURL = "https://68ecj67379.execute-api.ap-northeast-2.amazonaws.com/api";
axios.defaults.headers.common['Content-Type'] = "application/json";
axios.defaults.headers.common['X-Auth-Token'] = "f7926be63b76374151505f04df5a5add";

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