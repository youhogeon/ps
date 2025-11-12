import heapq
import math

N = int(input())
A = tuple(map(int, input().split(" ")))
M = [
    list(map(int, input().split(" ")))
    for _ in range(int(input()))
]

nodes: dict[tuple[int, ...], 'Node'] = {}

class Node:
    def __init__(self, data):
        self.data = data
        self.relations: dict[tuple[int, ...], int] = {} # cost map

    def make_rel(self):
        new_nodes = []
        
        for l, r, c in M:
            new_data = list(self.data)
            tmp = new_data[l - 1]
            new_data[l - 1] = new_data[r - 1]
            new_data[r - 1] = tmp

            new_data = tuple(new_data)
            if new_data in self.relations:
                continue

            node = nodes.get(new_data)
            if not node:
                node = Node(new_data)
                nodes[new_data] = node
                new_nodes.append(node)
            
            node.relations[self.data] = c
            self.relations[new_data] = c
        
        for node in new_nodes:
            node.make_rel()


nodes[A] = Node(A)
nodes[A].make_rel()

def dijkstra(start, goal):
    dist = {start: 0}
    prev = {}
    pq = [(0, start)]

    while pq:
        d, u = heapq.heappop(pq)

        if d != dist.get(u, math.inf):
            continue

        if u == goal:
            break

        for v, cost in nodes[u].relations.items():
            nd = d + cost
            if nd < dist.get(v, math.inf):
                dist[v] = nd
                prev[v] = u
                heapq.heappush(pq, (nd, v))

    if goal not in dist:
        return math.inf

    path = [goal]
    while path[-1] != start:
        path.append(prev[path[-1]])

    path.reverse()

    return dist[goal]

DEST = tuple(sorted(A))
if DEST not in nodes:
    print(-1)
else:
    print(dijkstra(A, DEST))
