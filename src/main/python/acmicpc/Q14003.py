N = int(input())
A = list(map(int, input().split(" ")))

seq = []
cnt = []

def find_large_idx(v):
    """나보다 크거나 같은 원소의 idx 조회"""

    left, right = -1, len(seq)

    while left + 1 < right:
        mid = (left + right) // 2

        if seq[mid] < v:
            left = mid
        else:
            right = mid
    
    return right

for a in A:
    i = find_large_idx(a)

    if i >= len(seq):
        seq.append(a)
        cnt.append(len(seq))
    else:
        seq[i] = a
        cnt.append(i + 1)

answer = max(cnt)
answer_seq = []

print(answer)

for value, c in zip(A[::-1], cnt[::-1]):
    if c == answer:
        answer_seq.append(value)
        answer -= 1

print(" ".join(map(str, answer_seq[::-1])))