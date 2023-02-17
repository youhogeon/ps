const { start, score, newRequests } = require("../models");
const { findAvailableRoomNumber, createEmptyRoomData, fillRoomData } = require("../services/room");
const { createReplyWrapper } = require("../services/reply");
const { getMaxDay } = require("../services/consts");
const { createAssignWrapper } = require("../services/assign");

/**
 * {"accuracy_score":80,"efficiency_score":2.900398406374502,"penalty_score":150.73523152400367,"score":432.16516688237084}
 * {"accuracy_score":80,"efficiency_score":1.2841949610419154,"penalty_score":74.83097212519237,"score":506.4532228358495}
 */

module.exports = async (PID) => {    
    const paramLimitAcceptImmediately = PID == 1 ? 8 : 27;
    const paramLimitReplyDay = PID == 1 ? 14 : 13;
    //parametric search를 통해 구한 parameter 값 정의
        
    const maxDay = getMaxDay(PID);
    const room = createEmptyRoomData(PID);
    const assign = createAssignWrapper(PID); //i일에 입실하는 고객
    const reply = createReplyWrapper(PID); //i일에 배정(reply) 여부를 통지받는 고객
    
    const auth = await start(PID);

    for (let i = 1; i <= maxDay; i++) { //매일 반복
        const dataNR = await newRequests(auth);
        
        for (let nr of dataNR) {
            if (nr.amount >= paramLimitAcceptImmediately) reply.addQueue(i, 0, nr);
            else {
                let roomFloorId = findAvailableRoomNumber(PID, room, nr.check_in_date, nr.check_out_date, nr.amount);

                if (roomFloorId !== false) reply.addQueue(i, paramLimitReplyDay, nr);
                else reply.addRefuse(nr)
            }
        }

        const replyList = reply.getQueue(i);

        for (let nr of replyList) {
            let roomFloorId = findAvailableRoomNumber(PID, room, nr.check_in_date, nr.check_out_date, nr.amount);

            if (roomFloorId !== false) {
                fillRoomData(room, roomFloorId, nr.id, nr.check_in_date, nr.check_out_date, nr.amount);
                assign.addData(nr, roomFloorId);

                reply.addAccept(nr)
            } else {
                reply.addRefuse(nr)
            }
        }

        await reply.sendReply(auth);
        await assign.sendAssign(auth, i);
    }

    const result = await score(auth);
    return result;
}