## 입력 처리
N = int(input())

rels: list[list[tuple[int, int]]] = [[] for _ in range(N)] # (target_node, cost)

for _ in range(N - 1):
    a, b, c = map(int, input().split())
    rels[a - 1].append((b - 1, c))
    rels[b - 1].append((a - 1, c))

M = int(input())
queries: list[tuple[int, int]] = []
for _ in range(M):
    queries.append(tuple(map(int, input().split()))) # type: ignore


## Graph 구성
p_nodes: list[list[tuple[int, int, int]]] = [[]] * N # (parent, min, max)
depths: list[int] = [0] * N
top_sorted: list[int] = []

queue: list[tuple[int, int, int]] = [
    (0, r[0], r[1])
    for r in rels[0]
] # (parent, child, cost)

while queue:
    p, c, cost = queue.pop()
    depths[c] = depths[p] + 1
    p_nodes[c] = [(p, cost, cost)]
    top_sorted.append(c)

    for r in rels[c]:
        if r[0] == p:
            continue
        queue.append((c, r[0], r[1]))

## 희소배열 구성
for nid in top_sorted:
    pid, curr_min, curr_max = p_nodes[nid][0]
    count = 0

    while count < len(p_nodes[pid]):
        curr_min = min(curr_min, p_nodes[pid][count][1])
        curr_max = max(curr_max, p_nodes[pid][count][2])

        p_nodes[nid].append((p_nodes[pid][count][0], curr_min, curr_max))
        pid = p_nodes[pid][count][0]
        count += 1

## LCA
def LCA(a: int, b: int):
    _min, _max = 1 << 30, -1

    if depths[a] > depths[b]:
        tmp = a
        a = b
        b = tmp

    while depths[a] != depths[b]:
        diff = depths[b] - depths[a]
        diff_log = diff.bit_length() - 1

        parent, x, y = p_nodes[b][diff_log]

        b = parent
        _min = min(_min, x)
        _max = max(_max, y)

    while a != b:
        for i in range(len(p_nodes[b]) - 1, -1, -1):
            p_a, min_a, max_a = p_nodes[a][i]
            p_b, min_b, max_b = p_nodes[b][i]

            if p_a == p_b and i != 0:
                continue

            a = p_a
            b = p_b

            _min = min(_min, min_a, min_b)
            _max = max(_max, max_a, max_b)

            break

    return (_min, _max)


for a, b in queries:
    _min, _max = LCA(a - 1, b - 1)

    print(f'{_min} {_max}')