const axios = require('axios');

axios.defaults.baseURL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";
axios.defaults.headers.common['X-Auth-Token'] = "54977c701e088418c7263c66792463e0";
axios.defaults.headers.common['Content-Type'] = "application/json";

module.exports.start = async (problem) => {
    const response = await axios.post('/start', {
        problem
    })

    pid = problem;

    axios.defaults.headers.common['Authorization'] = response.data.auth_key;

    console.log("initialized : " + response.data.auth_key);
}