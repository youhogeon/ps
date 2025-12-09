import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

N, M = map(int, input().split())

children = [set() for _ in range(N)]

for _ in range(M):
    a, b = map(int, input().split())

    children[a-1].add(b-1)
    children[b-1].add(a-1)

visited = [False] * N
nid = [0] * N
mins = [1000000] * N
count = 0

is_djj = [False] * N

def dfs(i: int, parent: int):
    global count
    count += 1
    
    nid[i] = count
    mins[i] = count
    visited[i] = True
    
    child_cnt = 0

    for child in children[i]:
        if child == parent:
            continue

        if not visited[child]:
            dfs(child, i)

            mins[i] = min(mins[i], mins[child])

            if parent != -1 and mins[child] >= nid[i]:
                is_djj[i] = True
            
            child_cnt += 1
        else:
            mins[i] = min(mins[i], nid[child])

    if parent == -1 and child_cnt >= 2:
        is_djj[i] = True

for i in range(N):
    if visited[i]:
        continue

    dfs(i, -1)

djj_cnt = [(i + 1) for i, v in enumerate(is_djj) if v]

print(len(djj_cnt))
print(" ".join(map(str, djj_cnt)))