N, M = map(int, input().split())

nodes: list[list[set[int]]] = [[set(), set()] for _ in range(N)] # incoming, outgoing

for _ in range(M):
    a, b = map(int, input().split())

    nodes[a-1][1].add(b-1)
    nodes[b-1][0].add(a-1)

graphs: list[list[int]] = []
visited: set[int] = set()
for i in range(N):
    if i in visited:
        continue

    queue = [i]
    i_visited = {i}
    while queue:
        nid = queue.pop()
        not_visited = nodes[nid][0] - i_visited
        queue.extend(not_visited)
        i_visited |= not_visited

    queue = [i]
    o_visited = {i}
    while queue:
        nid = queue.pop()
        not_visited = nodes[nid][1] - o_visited
        queue.extend(not_visited)
        o_visited |= not_visited

    scc = o_visited & i_visited

    graphs.append(sorted(list(scc)))
    visited |= scc

print(len(graphs))
for g in graphs:
    print(" ".join(map(lambda x: str(x + 1), g)) + " -1")


