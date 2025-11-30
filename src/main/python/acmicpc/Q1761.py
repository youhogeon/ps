N = int(input())

rel = [[] for _ in range(N)]

for _ in range(N-1):
    a, b, c = map(int, input().split())
    rel[a-1].append((b-1, c))
    rel[b-1].append((a-1, c))

M = int(input())
query = [tuple(map(lambda x : int(x) - 1, input().split())) for _ in range(M)]


# pids, costs(누적), depth
nodes: list[tuple[list[int], int, int]] = [None] * N # type: ignore
nodes[0] = ([0], 0, 0)

queue = [0]
while queue:
    parent = queue.pop()

    for nid, c in rel[parent]:
        if nodes[nid]:
            continue

        nodes[nid] = ([parent], nodes[parent][1]+c, nodes[parent][2] + 1)
        queue.append(nid)

        pnode = nodes[parent]
        depth = pnode[2]
        i = 0
        while i < len(pnode[0]):
            nodes[nid][0].append(pnode[0][i])
            pnode = nodes[pnode[0][i]]
            depth -= 1 << i
            i += 1
    
def findCP(a: int, b: int):
    if nodes[a][2] > nodes[b][2]: # a가 더 깊으면
        tmp = a
        a = b
        b = tmp

    while (diff := nodes[b][2] - nodes[a][2]):
        jmp = diff.bit_length() - 1
        b = nodes[b][0][jmp]
    
    if a == b:
        return a

    while nodes[a][0][0] != nodes[b][0][0]:
        for x, y in zip(nodes[a][0], nodes[b][0]):
            if x == y:
                break
        
            a = x
            b = y

    return nodes[a][0][0]

for a, b in query:
    mid = findCP(a, b)
    print(nodes[a][1] + nodes[b][1] - 2 * nodes[mid][1])