const problem = require("./controllers/p1/v1.js");

for (let i = 0.55; i <= 1; i+=0.05) {
    for (let j = 0.1; j <= 1; j += 0.1) {
        problem(i, j);
    }
}