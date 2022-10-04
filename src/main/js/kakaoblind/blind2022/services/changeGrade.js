const { getRank } = require("./userScore");

module.exports.createGradeData = (userScore) => {
    let i = 0;

    const rank = getRank(userScore);

    return rank.map((obj) => {
        return {
            'id': obj + 1,
            'grade': ++i
        }
    });
}