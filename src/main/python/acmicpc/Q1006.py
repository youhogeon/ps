T = int(input())

def get(l, idx) -> int:
    if idx < 0:
        return 0

    return l[idx]

INF = 99999999999999

for _ in range(T):
    N, W = map(int, input().split(" "))
    MAP = [
        list(map(int, input().split(" "))),
        list(map(int, input().split(" ")))
    ]

    a = [0] * N
    b = [0] * N
    d = [0] * N

    def do():
        for i in range(1, N):
            a[i] = min(
                d[i - 1] + 1,
                b[i - 1] + 1 if MAP[0][i] + MAP[0][i - 1] <= W else INF
            )

            b[i] = min(
                d[i - 1] + 1,
                a[i - 1] + 1 if MAP[1][i] + MAP[1][i - 1] <= W else INF
            )

            d[i] = min(
                d[i - 1] + (1 if MAP[0][i] + MAP[1][i] <= W else 2),
                get(d, i - 2) + 2 if MAP[0][i] + MAP[0][i - 1] <= W and MAP[1][i] + MAP[1][i - 1] <= W else INF,
                a[i] + 1,
                b[i] + 1
            )

    a[0] = 1
    b[0] = 1
    d[0] = 1 if MAP[0][0] + MAP[1][0] <= W else 2
    do()
    answer = d[-1]

    if N > 2 and MAP[0][0] + MAP[0][-1] <= W and MAP[1][0] + MAP[1][-1] <= W:
        a[0] = 0
        b[0] = 0
        d[0] = 0
        do()
        answer = min(answer, d[-2] + 2)
    if N > 2 and MAP[0][0] + MAP[0][-1] <= W:
        a[0] = 0
        b[0] = 1
        d[0] = 1
        do()
        answer = min(answer, b[-1] + 1)
    if N > 2 and MAP[1][0] + MAP[1][-1] <= W:
        a[0] = 1
        b[0] = 0
        d[0] = 1
        do()
        answer = min(answer, a[-1] + 1)
    
    
    print(answer)