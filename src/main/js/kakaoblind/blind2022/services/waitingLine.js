const { getRank } = require("./userScore");

module.exports.createMatchByScoreGap = (userScore, waitingLine, gapLimit=999) => {
    const rank = getRank(userScore);
    const allPair = [];

    for (let i = 0; i < waitingLine.length; i++) {
        for (let j = i + 1; j < waitingLine.length; j++) {
            allPair.push({
                'p1': waitingLine[i].id,
                'p2': waitingLine[j].id,
                'gap': Math.abs(rank.indexOf(waitingLine[i].id - 1) - rank.indexOf(waitingLine[j].id - 1)),
                'from': waitingLine[i].from + waitingLine[j].from
            });
        }
    }

    allPair.sort(function (a, b) {
        if (a.gap != b.gap) return a.gap - b.gap;

        return a.from - b.from;
    });

    const matched = {};
    const result = [];

    for (let i = 0; i < allPair.length; i++) {
        if (matched.hasOwnProperty(allPair[i].p1) || matched.hasOwnProperty(allPair[i].p2)) continue;
        if (allPair[i].gap > gapLimit && userScore[allPair[i].p1 - 1] != 0 && userScore[allPair[i].p2 - 1] != 0) continue;
        
        result.push([allPair[i].p1, allPair[i].p2]);
        matched[allPair[i].p1] = 0;
        matched[allPair[i].p2] = 0;
    }

    return result;
}