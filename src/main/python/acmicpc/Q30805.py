import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split(" ")))
M = int(input())
B = list(map(int, input().split(" ")))


found: list[int] = []

while A:
    for v in sorted(set(A), reverse=True):
        if v in B:
            found.append(v)

            A = A[A.index(v)+1:]
            B = B[B.index(v)+1:]

            break
    else:
        break

print(len(found))
print(" ".join(map(str, found)))
