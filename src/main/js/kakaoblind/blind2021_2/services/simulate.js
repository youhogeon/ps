/**
 * 시뮬레이트 명령 생성
 */
module.exports.createSimulateCommands = (commands) => {
    const truckList = [];

    for (let truck_id = 0; truck_id < commands.length; truck_id++) {
        truckList.push({
            truck_id,
            command: commands[truck_id],
        });
    }

    return truckList;
}