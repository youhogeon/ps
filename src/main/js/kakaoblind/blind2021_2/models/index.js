const { get, post, put } = require('./request');
const logger = require('../utils/logger');


/**
 * Start API
 * 문제를 풀기 위한 key를 발급한다. Start API를 실행하면 파라미터로 전달한 문제 번호에 맞게 각 자전거 대여소 및 트럭에 대한 정보를 초기화한다.


 */
module.exports.start = async (problem) => {
    const response = await post('/start', null, {
        problem
    });

    logger('start', response);

    return response.auth_key;
}

/**
 * Locations API
 * 현재 카카오 T 바이크 서비스 시각에 각 자전거 대여소가 보유한 자전거 수를 반환한다.
 */
module.exports.locations = async (auth) => {
    const response = await get('/locations', auth);

    logger('locations', response);

    return response.locations;
}

/**
 * Trucks API
 * 현재 카카오 T 바이크 서비스 시각에 각 트럭의 위치와 싣고 있는 자전거 수를 반환한다.
 */
module.exports.trucks = async (auth) => {
    const response = await get('/trucks', auth);

    logger('trucks', response);

    return response.trucks;
}

/**
 * Simulate API
 * 현재 시각 ~ 현재 시각 + 1분 까지 각 트럭이 행할 명령을 담아 서버에 전달한다.
 * 0: 6초간 아무것도 하지 않음
 * 1: 위로 한 칸 이동
 * 2: 오른쪽으로 한 칸 이동
 * 3: 아래로 한 칸 이동
 * 4: 왼쪽으로 한 칸 이동
 * 5: 자전거 상차
 * 6: 자전거 하차
 */
module.exports.simulate = async (auth, commands) => {
    const response = await put('/simulate', auth, {
        commands
    });

    logger('simulate', response);

    return response;
}

/**
 * Score API
 * 해당 Auth key로 획득한 점수를 반환한다. 점수는 높을수록 좋다. 카카오 T 바이크 서버의 상태가 finished가 아닐 때 본 API를 호출하면 response의 score는 무조건 0.0이다.
 */
module.exports.score = async (auth) => {
    const response = await get('/score', auth);

    logger('score', response);

    return response.score;
}