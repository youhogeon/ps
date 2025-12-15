# 11400

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
counter = 0
cut_edges: list[tuple[int, int]] = []

def dfs(curr: int, parent: int) -> int:
    global counter

    seq[curr] = counter
    counter += 1

    return_value = 9999999999
    child_counter = 0

    # print(f'curr: {curr}, parent: {parent}')

    for child in children[curr]:
        if child == parent:
            continue

        if seq[child] != -1: # visited
            return_value = min(return_value, seq[child])

            continue

        child_value = dfs(child, curr)
        child_counter += 1
        return_value = min(return_value, child_value)

        # print(f'curr: {curr}, child: {child}, child_value: {child_value}, seq_curr: {seq[curr]}')

        if child_value > seq[curr]:
            cut_edges.append((min(curr, child) + 1, max(curr, child) + 1))

    return return_value


for i in range(N):
    if seq[i] != -1:
        continue

    dfs(i, -1)

print(len(cut_edges))
print("\n".join(f'{a} {b}' for a, b in sorted(cut_edges)))
