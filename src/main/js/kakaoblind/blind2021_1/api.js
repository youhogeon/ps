const axios = require('axios');

axios.defaults.baseURL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";
axios.defaults.headers.common['X-Auth-Token'] = "54977c701e088418c7263c66792463e0";
axios.defaults.headers.common['Content-Type'] = "application/json";

let pid = 0;

module.exports.start = async (problem) => {
    const response = await axios.post('/start', {
        problem
    })

    pid = problem;

    axios.defaults.headers.common['Authorization'] = response.data.auth_key;

    console.log("initialized : " + response.data.auth_key);
}

module.exports.locations = async () => { // [ 위치0수, 위치1수, ... ]
    const response = await axios.get('/locations')

    const data = [];
    for (const idx in response.data.locations) {
        data[response.data.locations[idx].id] = response.data.locations[idx].located_bikes_count;
    }

    console.log("GET Location Data");

    // let k = (pid == 1 ? 5 : 60);
    // for (let i = 0; i < k; i++) {
    //     for (let j = 0; j < k; j++) {
    //         process.stdout.write(data[j * k - i + k - 1].toString());
    //         process.stdout.write(" ");
    //     }
    //     process.stdout.write("\n");
    // }

    return data;
}

module.exports.trucks = async () => { // [ [위치, 실린 갯수] ... ]
    const response = await axios.get('/trucks')

    console.log("GET Trucks Data");

    const data = [];
    for (const idx in response.data.trucks) {
        const obj = response.data.trucks[idx]
        data[obj.id] = [obj.location_id, obj.loaded_bikes_count];
    }

    return data;
}

module.exports.simulate = async (data) => {
    const commands = [];

    for (let truck_id in data) {
        if (data[truck_id].length) commands.push({ truck_id, command: data[truck_id] });
    }

    const response = await axios.put('/simulate', {
        commands
    })

    console.log("simulate " + JSON.stringify(response.data));

    return response.data;
}

module.exports.score = async () => {
    const response = await axios.get('/score')

    console.log("GET Score");

    return response.data.score;
}