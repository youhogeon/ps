const getMaxDay = PID => PID == 1 ? 200 : 1000;
const getMaxFloor = PID => PID == 1 ? 3 : 10;
const getMaxId = PID => PID == 1 ? 20 : 200;

module.exports = {
    getMaxDay,
    getMaxFloor,
    getMaxId
}