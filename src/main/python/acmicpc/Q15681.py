import sys
input = sys.stdin.readline
sys.setrecursionlimit(100100)

N, R, Q = map(int, input().split(" "))

near = [[] for i in range(N)]
parent_ids = [-1 for i in range(N)]
children_count = [0 for i in range(N)]

for _ in range(N - 1):
    u, v = map(int, input().split(" "))

    near[u-1].append(v - 1)
    near[v-1].append(u - 1)

def set_parent(my_id, parent_id):
    parent_ids[my_id] = parent_id

    for n in near[my_id]:
        if n == parent_id:
            continue

        set_parent(n, my_id)

def calc_children_count(id):
    count = 1
    parent_id = parent_ids[id]
    
    for n in near[id]:
        if n == parent_id:
            continue

        count += calc_children_count(n)

    children_count[id] = count

    return count


set_parent(R - 1, -1)
calc_children_count(R - 1)

for _ in range(Q):
    u = int(input())

    print(children_count[u-1])
