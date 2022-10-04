const { start, match, score, gameResult, waitingLine, changeGrade } = require("../../models");
const { createEmptyData, getRank } = require("../../services/userScore");
const { createGradeData } = require("../../services/changeGrade");
const { createMatchByScoreGap } = require("../../services/waitingLine");
const logger = require("../../utils/logger");

module.exports = async (paramA, paramB) => {
    const auth = await start(1)

    let userScore = createEmptyData(30);

    for (let i = 0; i < 595; i++) {
        //게임 결과 수신
        const rawGR = await gameResult(auth);
        for (let j = 0; j < rawGR.length; j++) {
            // userScore[rawGR[j].win - 1].grade += rawGR[j].taken;
            // userScore[rawGR[j].lose - 1].grade -= rawGR[j].taken;

            userScore[rawGR[j].win - 1].grade = ( (2828 * paramA * (40 - rawGR[j].taken) / (paramA * 2 - 1)) * paramB + (userScore[rawGR[j].win - 1].grade) * (1 - paramB) );
            userScore[rawGR[j].lose - 1].grade = ( (2828 * (1 - paramA) * (40 - rawGR[j].taken) / (paramA * 2 - 1)) * paramB + (userScore[rawGR[j].lose - 1].grade) * (1 - paramB) );
        }

        //게임 큐 생성
        const rawWL = await waitingLine(auth);
        const dataWL = createMatchByScoreGap(userScore, rawWL);

        //매칭
        await match(auth, dataWL);
    }

    const dataCG = createGradeData(userScore);
    await changeGrade(auth, dataCG);

    await match(auth, []);
    
    const result = await score(auth);

    logger('@ RESULT ' + paramA + " / " + paramB, result);

    return result;
}