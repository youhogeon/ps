# 5719

import heapq

BIG_VALUE = 1 << 31

def dijkstra(edges: list[list[int | None]], start: int):
    visited: set[int] = set()
    dist: list[tuple[list[int], int]] = [([], BIG_VALUE) for _ in range(len(edges))] # from, cost
    dist[start] = ([], 0)

    pq = [(0, start)]

    while pq:
        _, current = heapq.heappop(pq)

        if current in visited:
            continue

        for target, cost in enumerate(edges[current]):
            if cost is None:
                continue

            new_cost = dist[current][1] + cost
            if dist[target][1] == new_cost:
                dist[target][0].append(current)
            elif dist[target][1] > new_cost:
                dist[target] = ([current], new_cost)

                heapq.heappush(pq, (new_cost, target))

        visited.add(current)

    return dist

while True:
    N, M = map(int, input().split())
    if N == 0 and M == 0:
        break

    S, D = map(int, input().split())
    edges: list[list[int | None]] = [[None for _ in range(N)] for _ in range(N)] # edges[from][to] = cost
    for _ in range(M):
        U, V, P = map(int, input().split())
        edges[U][V] = P

    ####
    dist = dijkstra(edges, S)
    val = dist[D][1]

    while val != BIG_VALUE:
        # 간선제거
        nids: list[int] = [D]
        visited = set()
        while nids:
            nid = nids.pop()

            if nid in visited:
                continue

            for fr in dist[nid][0]:
                nids.append(fr)
                visited.add(nid)
                edges[fr][nid] = None
    
        # 재계산
        dist = dijkstra(edges, S)
        new_val = dist[D][1]

        if new_val != val:
            val = new_val
            break

    if val != BIG_VALUE:
        print(val)
    else:
        print(-1)
