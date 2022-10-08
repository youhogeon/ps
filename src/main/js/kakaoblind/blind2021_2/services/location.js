/**
 * 대여소 ID를 좌표로 변환
 */
const id2coor = (N, id) => {
    let r = id % N;
    let c = parseInt(id / N);

    return { r, c };
}

/**
 * 대여소 ID간 거리 계산
 */
module.exports.distance = (N, idA, idB) => {
    let coorA = id2coor(N, idA), coorB = id2coor(N, idB);

    return Math.abs(coorA.r - coorB.r) + Math.abs(coorA.c - coorB.c);
}

/**
 * 대여소 ID간 이동 명령 배열 생성
 */
module.exports.moveCommands = (N, idA, idB) => {
    let coorA = id2coor(N, idA), coorB = id2coor(N, idB);
    let r = coorB.r - coorA.r, c = coorB.c - coorA.c;

    let commands = [];
    for (let i = 0; i < Math.abs(r); i++) commands.push(r > 0 ? 1 : 3);
    for (let i = 0; i < Math.abs(c); i++) commands.push(c > 0 ? 2 : 4);

    return commands;
}