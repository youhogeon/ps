import sys
input = sys.stdin.readline

N, M, K = map(int, input().split(" "))

leaf_count = 1 << (N - 1).bit_length()

NUM = [int(input()) for _ in range(N)] + [1] * (leaf_count - N)
OP = [map(int, input().split(" ")) for _ in range(M + K)]

TREE = [0] * leaf_count + NUM

def build(idx: int):
    if idx >= leaf_count:
        return TREE[idx]
    
    TREE[idx] = (build(idx * 2) * build(idx * 2 + 1)) % 1000000007

    return TREE[idx]

def edit(idx: int, v: int):
    idx += leaf_count - 1

    TREE[idx] = v

    while True:
        idx = idx // 2

        if idx <= 0:
            break

        TREE[idx] = (TREE[idx * 2] * TREE[idx * 2 + 1]) % 1000000007

def get(idx: int, a: int, b: int):
    depth = idx.bit_length() - 1 # 층
    count = leaf_count >> depth # 현재 노드가 포함한 leaf갯수
    rel_idx = idx & ((1 << depth) - 1) # 현재 층에서의 idx

    A, B = (rel_idx * count, (rel_idx + 1) * count -1)

    if A >= a and B <= b:
        return TREE[idx]
    
    if b < A:
        return 1
    
    if a > B:
        return 1
    
    return (get(idx * 2, a, b) * get(idx * 2 + 1, a, b)) % 1000000007


build(1)

for a, b, c in OP:
    if a == 1:
        edit(b, c)
    else:
        print(get(1, b-1, c-1))