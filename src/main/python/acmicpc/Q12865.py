import sys
input = sys.stdin.readline

N, K = map(int, input().split(" "))

W = []
V = []
for _ in range(N):
    w, v = map(int, input().split(" "))
    W.append(w)
    V.append(v)

dp = [[0] * (K+1) for _ in range(N+1)] # dp[item][weight]f


for item_idx, (w, v) in enumerate(zip(W, V)):
    item_idx += 1

    for k in range(K + 1):
        if k < w:
            dp[item_idx][k] = dp[item_idx-1][k]
        else:
            dp[item_idx][k] = max( dp[item_idx-1][k], dp[item_idx-1][k - w] + v )

print(dp[-1][-1])
