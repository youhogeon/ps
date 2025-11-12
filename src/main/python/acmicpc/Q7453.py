import sys
input = sys.stdin.readline


N = int(input())

A, B, C, D = [], [], [], []

for _ in range(N):
    a, b, c, d = input().split(" ")
    A.append(int(a))
    B.append(int(b))
    C.append(int(c))
    D.append(int(d))

AB = {}
CD = []

for i in range(N):
    for j in range(N):
        AB[A[i] + B[j]] = AB.get(A[i] + B[j], 0) + 1
        CD.append(C[i] + D[j])

count = 0

for k in CD:
    count += AB.get(-k, 0)
    
print(count)