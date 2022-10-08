const { start, score, simulate } = require("../models");
const { moveCommands } = require("../services/location");
const { createSimulateCommands } = require("../services/simulate");

module.exports = async () => {
    const auth = await start(1)

    const v = createSimulateCommands([
        moveCommands(5, 0, 8),
        moveCommands(5, 0, 6),
        moveCommands(5, 0, 12),
        moveCommands(5, 0, 16),
        moveCommands(5, 0, 18),
    ])
    await simulate(auth, v);

    for (let time = 1; time < 720; time++) {
        await simulate(auth, []);
    }

    const result = await score(auth);
    return result;
}