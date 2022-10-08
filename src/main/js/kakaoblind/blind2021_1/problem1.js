const api = require("./api.js");

const mapData = [
    [4, 9, 14, 3, 8, 13, 2, 7, 12],
    [14, 19, 24, 13, 18, 23, 12, 17, 22],
    [2, 7, 12, 1, 6, 11, 0, 5, 10],
    [12, 17, 22, 11, 16, 21, 10, 15, 20],
    [8, 13, 18, 7, 12, 17, 6, 11, 16],
];

const truckLocation = [0, 0, 0, 0, 0];

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
    truckLocation[truckID] = to;

    return data;
}

const moveCalc = (from, to) => {
    let fromR = 4 - (from % 5), fromC = parseInt(from / 5);
    let toR = 4 - (to % 5), toC = parseInt(to / 5);

    let gapR = Math.abs(fromR - toR), gapC = Math.abs(fromC - toC);

    let data = [];

    for (let i = 0; i < gapR; i++) data.push((fromR > toR) ? 1 : 3);
    for (let i = 0; i < gapC; i++) data.push((fromC > toC) ? 4 : 2);

    return data;
}

const minID = (truckID, locations) => {
    let min = 9999, minID = -1;
    for (let i in mapData[truckID]) {
        let idx = mapData[truckID][i];

        if (min > locations[idx]) {
            min = locations[idx];
            minID = idx;
        }
    }

    return minID;
}

const maxID = (truckID, locations) => {
    let max = -1, maxID = -1;
    for (let i in mapData[truckID]) {
        let idx = mapData[truckID][i];

        if (max < locations[idx]) {
            max = locations[idx];
            maxID = idx;
        }
    }

    return maxID;
}

module.exports = async () => {
    await api.start(1)
    //await api.locations()
    //await api.trucks()

    for (let i = 0; i < 720; i++) {
        const data = [[], [], [], [], []];
        const locations = await api.locations();

        if (i == 0) {
            data[0] = move(0, 8);
            data[1] = move(1, 18);
            data[2] = move(2, 6);
            data[3] = move(3, 16);
            data[4] = move(4, 12);
        }

        for (let truckID = 0; truckID < 5; truckID++) {
            let minIdx = minID(truckID, locations), maxIdx = maxID(truckID, locations);

            if (minIdx >= 0 && maxIdx >= 0 && locations[minIdx] != locations[maxIdx]) {
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