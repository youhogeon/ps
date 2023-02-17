const problem = require("./controllers/v1.js");

for (let PID = 1; PID <= 2; PID++) {
    //for (let paramLimitAcceptImmediately = 1; paramLimitAcceptImmediately <= 10; paramLimitAcceptImmediately++) 
    //for (let paramLimitReplyDay = 1; paramLimitReplyDay <= 14; paramLimitReplyDay++) 
        problem(PID);

    //여러 파라미터들을 병렬 수행하며 최적의 값을 찾음(parametric search)
}