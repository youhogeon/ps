import sys
input = sys.stdin.readline
sys.setrecursionlimit(200000)

N, M, K = map(int, input().split(" "))
C = list(map(int, input().split(" ")))

relations = [[] for _ in range(N)]

for _ in range(M):
    a, b = map(int, input().split(" "))

    relations[a - 1].append(b - 1)
    

visited = [False] * N

def walk(nid):
    cnt = 1
    value = C[nid]

    visited[nid] = True

    for rel in relations[nid]:
        if visited[rel]:
            continue

        c, v = walk(rel)
        cnt += c
        value += v
    
    return cnt, value
    
W = []
V = []

for nid in range(N):
    if visited[nid]:
        continue

    cnt, value = walk(nid)

    W.append(cnt)
    V.append(value)


dp = [0] * K

for i, (v, w) in enumerate(zip(V, W)):
    for j in range(K - 1, -1, -1):
        if j >= w:
            dp[j] = max(dp[j], dp[j - w] + v)

print(dp[-1])