const { simulate } = require("../models");
const { createEmptyRoomAssignData, floorId2roomNumber } = require("./room");

const addData = function (nr, roomFloorId) {
    this.data[nr.check_in_date].push({ id: nr.id, room_number: floorId2roomNumber(roomFloorId[0], roomFloorId[1]) });
}

const sendAssign = async function (auth, day) {
    return await simulate(auth, this.data[day]);
}

module.exports.createAssignWrapper = (PID) => {
    const obj = {};
    obj.data = createEmptyRoomAssignData(PID);

    obj.addData = addData;
    obj.sendAssign = sendAssign;

    return obj;
}