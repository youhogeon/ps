const api = require("./api.js");

const truckLocation = [];

const load = (count) => {
    const data = [];

    while (count-- > 0) data.push(5);

    return data;
}

const release = (count) => {
    const data = [];

    while (count-- > 0) data.push(6);

    return data;
}

const move = (truckID, to) => {
    let data = moveCalc(truckLocation[truckID], to);

    return data;
}

const moveCalc = (from, to) => {
    let fromR = 59 - (from % 60), fromC = parseInt(from / 60);
    let toR = 59 - (to % 60), toC = parseInt(to / 60);

    let gapR = Math.abs(fromR - toR), gapC = Math.abs(fromC - toC);

    let data = [];

    for (let i = 0; i < gapR; i++) data.push((fromR > toR) ? 1 : 3);
    for (let i = 0; i < gapC; i++) data.push((fromC > toC) ? 4 : 2);

    return data;
}

const minID = (loc, locations) => {
    let mapData = near(loc);

    let min = 9999, minID = -1;
    for (let i in mapData) {
        let idx = mapData[i];

        if (min > locations[idx]) {
            min = locations[idx];
            minID = idx;
        }
    }

    return minID;
}

const maxID = (loc, locations) => {
    let mapData = near(loc);

    let max = -1, maxID = -1;
    for (let i in mapData) {
        let idx = mapData[i];

        if (max < locations[idx]) {
            max = locations[idx];
            maxID = idx;
        }
    }

    return maxID;
}

const near = (loc) => {
    let r = 59 - (loc % 60), c = parseInt(loc / 60);

    let dx = [4, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -3, -3, -3, -4];
    let dy = [0, 1, 0, -1, -2, -1, 0, 1, 2, -3, -2, -1, 0, 1, 2, 3, -4, -3, -2, -1, 1, 2, 3, 4, -3, -2, -1, 0, 1, 2, 3, -2, -1, 0, 1, 2, -1, 0, 1, 0];

    let data = [];

    for (let i = 0; i < 40; i++) {
        let newR = r + dx[i], newC = c + dy[i];
        if (newR < 0 || newC < 0 || newR >= 60 || newC >= 60) continue;

        data.push(newC * 60 + 60 - newR);
    }

    return data;
}

module.exports = async () => {
    await api.start(2)

    for (let time = 0; time < 720; time++) {
        const data = [[], [], [], [], [], [], [], [], [], []];
        const locations = await api.locations();
        const trucks = await api.trucks();

        for (let i = 0; i < 10; i++) truckLocation[i] = trucks[i][0];

        let truckCnt = 10;

        if (time < 12) {
            data[0] = move(0, 60 * 14 + 14);
            data[1] = move(1, 60 * 14 + 30);
            data[2] = move(2, 60 * 14 + 45);
            data[3] = move(3, 60 * 30 + 14);
            data[4] = move(4, 60 * 30 + 30);
            data[5] = move(5, 60 * 30 + 45);
            data[6] = move(6, 60 * 45 + 14);
            data[7] = move(7, 60 * 45 + 30);
            data[8] = move(8, 60 * 45 + 45);
            data[9] = move(9, 3595);

            truckCnt = 0;
        }

        if (time >= 240 && time <= 252) {
            data[9] = move(9, 724);

            truckCnt = 9;
        }

        if (time >= 480 && time <= 492) {
            data[9] = move(9, 969);

            truckCnt = 9;
        }

        for (let truckID = 0; truckID < truckCnt; truckID++) {
            let maxIdx = maxID(truckLocation[truckID], locations), minIdx = minID(maxIdx, locations);

            if (locations[minIdx] != locations[maxIdx]) {
                let toMax = moveCalc(truckLocation[truckID], maxIdx)
                let toMin = moveCalc(maxIdx, minIdx)
                let loadCount = parseInt(Math.min((10 - toMax.length + toMin.length) / 2, (locations[maxIdx] - locations[minIdx]) / 2));

                if (loadCount) {
                    truckLocation[truckID] = minIdx;
                    locations[minIdx] += loadCount;
                    locations[maxIdx] -= loadCount;
                    data[truckID] = [...toMax, ...load(loadCount), ...toMin, ...release(loadCount)];
                    //console.log(maxIdx + "->" + minIdx + " / " + loadCount + " / " + JSON.stringify(data[truckID]));
                    //console.log(locations)
                }
            }
        }

        //console.log(data);

        await api.simulate(data)
    }

    console.log(await api.score());
}