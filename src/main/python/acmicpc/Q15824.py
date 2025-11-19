N = int(input())
V = sorted(map(int, input().split(" ")))

MOD = 1000000007
POW2 = [1]

for i in range(1, N):
    POW2.append((POW2[-1] << 1) % MOD)

mul = -(POW2[N-1]-1)
result = 0
for i, v in enumerate(V):
    result = (result + mul * v) % MOD

    mul += POW2[N-i-2] + POW2[i]

print(result)