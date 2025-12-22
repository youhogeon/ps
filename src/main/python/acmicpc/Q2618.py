# 2618

# import sys
# input = sys.stdin.readline
# sys.setrecursionlimit(100000)


N = int(input())
W = int(input())

events = [
    tuple(map(int, input().split()))
    for _ in range(W)
]

dist = [[abs(events[i - 1][0] - events[j - 1][0]) + abs(events[i - 1][1] - events[j - 1][1]) for j in range(W + 1)] for i in range(W + 1)] # A가 움직이는 경우 [작][큰] B가움직이는경우 [큰][작]

for i in range(W+1):
    if i == 0:
        continue

    dist[0][i] = events[i - 1][0] + events[i - 1][1] - 2
    dist[i][0] = N + N - (events[i - 1][0] + events[i - 1][1])

dp = [[-1 for _ in range(W + 1)] for _ in range(W + 1)]
dp[0][1] = dist[1][0]
dp[1][0] = dist[0][1]

chain: list[list[tuple[int, int]]] = [[(-1, -1) for _ in range(W + 1)] for _ in range(W + 1)]

for i in range(W + 1):
    for j in range(W + 1):
        if i == j or i + j == 1:
            continue

        if i > j + 1: # 위 값 받기
            dp[i][j] = dp[i - 1][j] + dist[i - 1][i]
            chain[i][j] = (i - 1, j)
        elif j > i + 1: # 좌 값 받기
            dp[i][j] = dp[i][j - 1] + dist[j][j - 1]
            chain[i][j] = (i, j - 1)
        elif i == j + 1: # 위 aggr
            dp[i][j] = 9999999999999

            for k in range(i - 1):
                new_val = dp[k][j] + dist[k][i]

                if new_val >= dp[i][j]:
                    continue

                dp[i][j]= new_val
                chain[i][j] = (k, j)
        elif j == i + 1: # 좌 aggr
            dp[i][j] = 9999999999999

            for k in range(j - 1):
                new_val = dp[i][k] + dist[j][k]

                if new_val >= dp[i][j]:
                    continue

                dp[i][j]= new_val
                chain[i][j] = (i, k)



candidates: list[tuple[int, int, int]] = sorted([
    (dp[W][c], W, c) for c in range(W)
] + [
    (dp[r][W], r, W) for r in range(W)
])

answer, *answer_rc = candidates[0]
traces: list[int] = []


while True:
    i, j = answer_rc

    if i == 1 and j == 0:
        traces.append(1)
        break

    if i == 0 and j == 1:
        traces.append(2)
        break

    t_i, t_j = chain[i][j]

    if t_j == j:
        traces.append(1)
    else:
        traces.append(2)

    answer_rc = chain[i][j]


print(answer)
for t in traces[::-1]:
    print(t)