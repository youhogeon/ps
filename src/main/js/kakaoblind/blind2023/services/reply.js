const { reply } = require("../models");
const { createEmptyRoomAssignData } = require("./room");

const addAccept = function (nr) {
    let id = nr.id;

    this.queue.push({ id, reply: 'accepted' });
}

const addRefuse = function (nr) {
    let id = nr.id;

    this.queue.push({ id, reply: 'refused' });
}

const sendReply = async function (auth) {
    await reply(auth, this.queue);
    this.queue = [];
}

const addQueue = function (day, limitReplyDay, nr) {
    this.data[Math.min(nr.check_in_date - 1, day + limitReplyDay)].push(nr);
}

const getQueue = function (day) {
    this.data[day].sort((a, b) => b.amount - a.amount); //점유율 높은 고객부터 정렬

    return this.data[day];
}

module.exports.createReplyWrapper = (PID) => {
    const obj = {};
    obj.data = createEmptyRoomAssignData(PID);
    obj.queue = [];

    obj.addAccept = addAccept;
    obj.addRefuse = addRefuse;
    obj.sendReply = sendReply;
    obj.addQueue = addQueue;
    obj.getQueue = getQueue;

    return obj;
}