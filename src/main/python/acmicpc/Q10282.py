# 10282

import heapq
import sys
input = sys.stdin.readline

BIG_VALUE = 1 << 31
T = int(input())

for _ in range(T):
    N, D, C = map(int, input().split())

    edges: list[tuple[int, int]] = [[] for _ in range(N)] # (to, cost)

    for _ in range(D):
        a, b, s = map(int, input().split())

        edges[b - 1].append((a - 1, s))

    visited: set[int] = set()
    dist: list[int] = [BIG_VALUE] * N
    dist[C - 1] = 0

    pq = [(0, C - 1)]

    while pq:
        _, current = heapq.heappop(pq)

        if current in visited:
            continue

        for target, cost in edges[current]:
            if dist[target] > dist[current] + cost:
                dist[target] = dist[current] + cost

                heapq.heappush(pq, (dist[current] + cost, target))
        
        visited.add(current)

    max_value, count = 0, 0
    for d in dist:
        if d == BIG_VALUE:
            continue

        count += 1        
        max_value = max(max_value, d)
    
    print(count, max_value)