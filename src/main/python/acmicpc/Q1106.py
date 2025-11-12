import sys
input = sys.stdin.readline

C, N = map(int, input().split(" "))

W = []
V = []
for _ in range(N):
    w, v = map(int, input().split(" "))
    W.append(w)
    V.append(v)

dp = [[0] * (C+1) for _ in range(N)] # dp[city][weight]


for item_idx, (w, v) in enumerate(zip(W, V)):
    for c in range(C + 1):
        values = [w * ((c-1) // v + 1)]

        if item_idx > 0:
            values.append(dp[item_idx - 1][c])

            if c > v:
                values.append( dp[item_idx - 1][c - v] + w )
                values.append( dp[item_idx][c - v] + w )
        
        dp[item_idx][c] = min(values)

print(dp[-1][-1])
