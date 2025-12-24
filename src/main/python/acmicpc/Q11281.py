# 11281

import sys
input = sys.stdin.readline

from collections import deque
from enum import Enum


class Status(Enum):
    NOT_VISITED = 0
    T = 1
    F = 2

    @classmethod
    def from_bool(cls, val: bool):
        if val:
            return Status.T

        return Status.F


N, M = map(int, input().split())

nodes = [Status.NOT_VISITED] * N
edges: list[list[tuple[int, bool, bool]]] = [[] for _ in range(N)] # (dest, is_positive1, is_positive2)

for _ in range(M):
    a, b = map(int, input().split())

    aa, bb = abs(a), abs(b)

    edges[aa - 1].append((bb - 1, a > 0, b > 0))
    edges[bb - 1].append((aa - 1, b > 0, a > 0))


def bfs(nid: int, my_value: bool) -> bool:
    visited: list[int] = [nid]
    queue: deque[int] = deque([nid])
    nodes[nid] = Status.from_bool(my_value)

    result = True

    while queue and result:
        nid = queue.popleft()
        status = nodes[nid]

        for child, is_pos1, is_pos2 in edges[nid]:
            if status == Status.T and is_pos1:
                continue

            if status == Status.F and not is_pos1:
                continue

            if nodes[child] == Status.T and not is_pos2 or nodes[child] == Status.F and is_pos2:
                result = False
                break

            if nodes[child] != Status.NOT_VISITED:
                continue

            # 자식의 값은 반드시 is_pos2이어야 함
            nodes[child] = Status.from_bool(is_pos2)
            visited.append(child)
            queue.append(child)

    if not result:
        for v in visited:
            nodes[v] = Status.NOT_VISITED

    return result


for i in range(N):
    if nodes[i] != Status.NOT_VISITED:
        continue

    if bfs(i, True):
        continue

    if bfs(i, False):
        continue

result = int(all(x is not Status.NOT_VISITED for x in nodes))
print(result)
if result == 1:
    print(" ".join(map(str, map(int, (x == Status.T for x in nodes)))))
