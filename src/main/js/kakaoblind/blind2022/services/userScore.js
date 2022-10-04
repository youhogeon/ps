module.exports.createEmptyData = (n) => {
    const userScore = [];
    for (let i = 0; i < n; i++) userScore.push({ 'id': i, 'grade': 40000 });

    return userScore;
}

/**
 * 점수가 낮은 순서대로 ID가 삽입된 배열 반환
 * @param {array} userScore 
 * @returns {array} ID가 담긴 배열
 */
module.exports.getRank = (userScore) => {
    const sorted = [...userScore].sort(function (a, b) {
        return a.grade - b.grade;
    });

    const result = [];
    for (let i = 0; i < sorted.length; i++) result.push(sorted[i].id);

    return result;
}