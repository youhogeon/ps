const { get, post, put } = require('./request');
const logger = require('../utils/logger');

/**
 * 문제를 풀기 위한 key를 발급한다.
    {
    "auth_key": "1fd74321-d314-4885-b5ae-3e72126e75cc",
    "problem": 1,
    "time": 0
    }
 */
module.exports.start = async (problem) => {
    const response = await post('/start', null, {
        problem
    });

    logger('start', response);

    return response.auth_key;
}

/**
 * 현재 대기열에서 매칭을 대기 중인 유저들의 정보를 반환한다.
    [
        { "id": 1, "from": 3 },
        { "id": 2, "from": 14 },
        ...
    ]
 */
module.exports.waitingLine = async (auth) => {
    const response = await get('/waiting_line', auth);

    logger(`waitingLine (${auth})`, response.waiting_line);

    return response.waiting_line;
}

/**
 * 이번 턴에 게임이 끝난 유저들의 게임 결과를 반환한다.
    [
        {"win": 10, "lose": 2, "taken": 7 },
        {"win": 7, "lose": 12, "taken": 33 },
        ...
    ]
 */
module.exports.gameResult = async (auth) => {
    const response = await get('/game_result', auth);

    logger(`gameResult (${auth})`, response.game_result);

    return response.game_result;
}

/**
 * 모든 유저들의 현재 등급을 반환한다.
    [
        { "id": 1, "grade": 2100 },
        { "id": 13, "grade": 1501 },
        ...
    ]
 */
module.exports.userInfo = async (auth) => {
    const response = await get('/user_info', auth);

    logger(`userInfo (${auth})`, response.user_info);

    return response.user_info;
}

/**
 * 시뮬레이션이 끝난 뒤 정확성 점수와 효율성 점수, 총점을 반환한다.
    {
    "status": "finished",
    "efficiency_score": 1.0,
    "accuracy_score1": 0.0,
    "accuracy_score2": 32.62,
    "score": 39.944
    }
 */
module.exports.score = async (auth) => {
    const response = await get('/score', auth)

    logger(`score (${auth})`, response);

    return response;
}

/**
 * 대기열에서 매칭 대기 중인 두 유저를 매칭하여 게임을 시작하도록 한다. 
 * @param {Array} pairs 
    {
    "status": "ready",
    "time": 312
    }
 */

module.exports.match = async (auth, pairs) => {
    const response = await put('/match', auth, {
        pairs
    })

    logger(`match (${auth})`, response);

    return response;
}

/**
 * 여러 유저의 등급을 수정할 수 있다. 등급의 범위는 0 이상 9,999 이하 정수이다.
 * @param {Array} commands 
    {
    "status": "ready"
    }
 */
module.exports.changeGrade = async (auth, commands) => {
    const response = await put('/change_grade', auth, {
        commands
    })

    logger(`changeGrade (${auth})`, response);

    return response;
}