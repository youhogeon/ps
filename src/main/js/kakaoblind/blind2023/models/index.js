const { get, post, put } = require('./request');
const logger = require('../services/logger');

/**
 * Start API
 * 
 */
module.exports.start = async (problem) => {
    const response = await post('/start', null, {
        problem
    });

    logger('start', response);

    return response.auth_key;
}

/**
 * NewRequests
 * 현재 날짜에 새로 들어온 예약 요청의 정보를 반환합니다.
 * [
    {"id": 453217, "amount": 10, "check_in_date": 104, "check_out_date": 110},
    {"id": 412424, "amount": 2, "check_in_date": 108, "check_out_date": 110},
    {"id": 707981, "amount": 5, "check_in_date": 107, "check_out_date": 108},
    ...
  ]
 */
module.exports.newRequests = async (auth) => {
    const response = await get('/new_requests', auth, {
        
    });

    logger('newRequests', response);

    return response.reservations_info;
}

/**
 * Reply API
 * 특정 예약 요청에 대한 승낙 / 거절을 답변합니다.
 * [
           { "id": 412424, "reply": "accepted"},
           { "id": 707981, "reply": "refused"},
           ...
    ]
 */
module.exports.reply = async (auth, replies) => {
    const response = await put('/reply', auth, {
        replies
    });

    logger('reply', response);

    return response;
}

/**
 * Simulate API
 * 오늘 호텔에 체크인 하려는 손님들에게 객실 번호를 배정해 서버에 전달하고 1일이 진행됩니다. 현재 날짜가 (시나리오의 최대 날짜)일 때 api를 호출하면 시뮬레이션이 종료됩니다. 호출 시 서버에서는 다음과 같은 일이 진행됩니다.
 * 
 * 응시자에게 전달받은 순서대로 객실을 배정합니다.
 * (전달받은 객실 번호) ~ (전달받은 객실 번호 + 객실 수 - 1)의 객실을 손님에게 배정합니다.
 * 만약 객실이 비어있지 않거나 객실 번호의 범위가 올바르지 않다면 무시합니다.
 * 객실을 성공적으로 배정했다면 해당 객실들은 체크아웃 날짜 전까지 사용 중인 상태가 됩니다.
 * 호텔에 도착한 손님 중 객실이 배정되지 않은 손님은 객실 배정에 실패한 것으로 처리됩니다.
 * 답변하지 않은 예약 중 답변 기한이 현재 날짜인 예약은 거절한 것으로 처리됩니다.
 * 현재 날짜가 1일 증가합니다.
 * 현재 날짜에 체크아웃하는 객실들이 빈 객실이 됩니다.
 * 현재 날짜에 체크인하는 손님들이 호텔에 도착합니다.
 * 
 * "room_assign": [
           { "id": 707981, "room_number": 2023},
           { "id": 975671, "room_number": 1001},
           ...
         ]
 */
module.exports.simulate = async (auth, room_assign) => {
    const response = await put('/simulate', auth, {
        room_assign
    });

    logger('simulate', response);

    if (response.fail_count != 0) logger('😡😡😡😡😡', response);

    return response;
}

/**
 * Score API
 * 
 */
module.exports.score = async (auth) => {
    const response = await get('/score', auth);

    logger('score', response);

    return response.score;
}