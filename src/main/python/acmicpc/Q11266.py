#11266

import sys

input = sys.stdin.readline
sys.setrecursionlimit(100000)

N, M = map(int, input().split())

children: list[set[int]] = [set() for _ in range(N)]

for _ in range(M):
    a, b = map(int, input().split())

    children[a-1].add(b-1)
    children[b-1].add(a-1)

seq = [-1] * N
is_cut_node = [False] * N
counter = 0

def dfs(curr: int, parent: int) -> int:
    global counter

    seq[curr] = counter
    counter += 1

    return_value = 9999999999
    child_counter = 0

    for child in children[curr]:
        if child == parent:
            continue

        if seq[child] != -1: # visited
            return_value = min(return_value, seq[child])

            continue

        child_value = dfs(child, curr)
        child_counter += 1
        return_value = min(return_value, child_value)

        if child_value >= seq[curr]:
            is_cut_node[curr] = True

    if parent == -1 and child_counter > 1:
        is_cut_node[curr] = True

    return return_value


for i in range(N):
    if seq[i] != -1:
        continue

    dfs(i, -1)

cut_nodes = [i + 1 for i, is_cut in enumerate(is_cut_node) if is_cut]

print(len(cut_nodes))
print(" ".join(map(str, cut_nodes)))
