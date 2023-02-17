const { getMaxFloor, getMaxId, getMaxDay } = require('./consts');

const createEmptyRoomData = (PID) => {
    const maxFloor = getMaxFloor(PID);
    const maxId = getMaxId(PID);
    const maxDay = getMaxDay(PID);

    let room = []; //room[day][floor][id] = user_id
    for (let i = 0; i <= maxDay; i++) {
        room[i] = [];
        for (let j = 0; j <= maxFloor; j++) {
            room[i][j] = Array.from({length: maxId + 1}, () => 0);
        }
    }

    return room;
}

const createEmptyRoomAssignData = (PID) => {
    maxDay = getMaxDay(PID);

    return Array.from({length: maxDay + 1}, () => []);
}

const floorId2roomNumber = (floor, id) => {
    return floor.toString() + id.toString().padStart(3, '0');
}

const roomNumber2FloorId = (roomNumber) => {
    roomNumber = parseInt(roomNumber);
    return [parseInt(roomNumber / 1000), roomNumber % 1000];
}

const findAvailableRoomNumber = (PID, room, checkin, checkout, size) => {
    maxFloor = getMaxFloor(PID);
    maxId = getMaxId(PID);

    let merged = [];
    for (let j = 1; j <= maxFloor; j++) merged[j] = Array.from({length: maxId + 1}, () => 0);

    for (let i = checkin; i < checkout; i++) {
        for (j = 1; j <= maxFloor; j++) {
            for (k = 1; k <= maxId; k++) {
                if (room[i][j][k] != 0) merged[j][k] = i;
            }
        }
    }
    //해당 날짜에 가능한 방 리스트 (0이면 예약 가능)

    let availableRoom = [];

    for (let i = 1; i <= maxFloor; i++) {
        let combo = 0, comboStart = 1;

        for (let j = 1; j <= maxId + 1; j++) {
            if (j == maxId + 1 || merged[i][j] > 0) {
                if (PID == 1) {
                    if (combo > 0) availableRoom[combo] = [i, comboStart];
                } else {
                    let nearValue = 0;

                    if (comboStart == 1 && j == maxId + 1) nearValue = 9999;
                    else if (comboStart == 1) nearValue = merged[i][j]
                    else if (j == maxId + 1) nearValue = merged[i][comboStart - 1];
                    else nearValue = Math.max(merged[i][comboStart - 1], merged[i][j]);

                    if (combo > 0 && (!availableRoom[combo] || availableRoom[combo][2] < nearValue)) availableRoom[combo] = [i, comboStart, nearValue];
                }
                combo = 0;
                comboStart = j + 1;
            } else combo++;
        }
    }
    //예약 기간에 따라 적절한 방을 선별 (중요 알고리즘)

    let d = false;
    for (let i = maxId; i >= 1; i--) {
        if (!availableRoom[i]) availableRoom[i] = d;
        else d = availableRoom[i];
    }

    return availableRoom[size];
}

const fillRoomData = (room, roomFloorId, userId, checkin, checkout, size) => {
    for (let i = checkin; i < checkout; i++) {
        for (let j = 0; j < size; j++) {
            room[i][roomFloorId[0]][roomFloorId[1] + j] = userId;
        }
    }
}

module.exports = {
    createEmptyRoomData,
    createEmptyRoomAssignData,
    floorId2roomNumber,
    roomNumber2FloorId,
    findAvailableRoomNumber,
    fillRoomData
}
