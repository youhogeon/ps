import sys
input = sys.stdin.readline

W, N = map(int, input().split(" "))
A = list(map(int, input().split(" ")))

def do():
    dp = [
        []
        for _ in range(W+1)
    ]

    for i in range(N):
        for j in range(i+1, N):
            v = A[i] + A[j]
            if v > W:
                continue

            dp[v] = [i, j]


    for i in range(N):
        for j in range(i+1, N):
            v = A[i] + A[j]

            if v > W:
                continue

            left = W - v

            if not dp[left]:
                continue

            if dp[left][0] == i or dp[left][0] == j or dp[left][1] == i or dp[left][1] == j:
                continue

            return True

    return False

print("YES" if do() else "NO")