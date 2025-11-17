import sys
input = sys.stdin.readline

INF = 9999999999

N, M= map(int, input().split(" "))

leaf_count = 1 << (N - 1).bit_length()

NUM = [((x := int(input())), x) for _ in range(N)] + [(INF, -1)] * (leaf_count - N)
OP = [map(int, input().split(" ")) for _ in range(M)]

TREE = [(INF, -1)] * leaf_count + NUM

def build(idx: int):
    if idx >= leaf_count:
        return TREE[idx]
    
    left = build(idx * 2)
    right = build(idx * 2 + 1)
    TREE[idx] = (min(left[0], right[0]), max(left[1], right[1]))

    return TREE[idx]

def get(idx: int, a: int, b: int):
    depth = idx.bit_length() - 1 # 층
    count = leaf_count >> depth # 현재 노드가 포함한 leaf갯수
    rel_idx = idx & ((1 << depth) - 1) # 현재 층에서의 idx

    A, B = (rel_idx * count, (rel_idx + 1) * count -1)

    if A >= a and B <= b:
        return TREE[idx]
    
    if b < A:
        return (INF, -1)
    
    if a > B:
        return (INF, -1)
    
    left = get(idx * 2, a, b)
    right = get(idx * 2 + 1, a, b)

    return (min(left[0], right[0]), max(left[1], right[1]))


build(1)

for a, b in OP:
    x, y = get(1, a-1, b-1)
    print(f"{x} {y}")