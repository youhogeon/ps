N = int(input())
X = list(map(int, input().split(" ")))

MAX = 1000001

answer = [10000000] * (MAX + 1)

for x in X:
    answer[x] = 0


for x in X:
    for i in range(x * 2, MAX, x):
        if answer[i] == 10000000:
            continue

        answer[i] -= 1
        answer[x] += 1

print(" ".join(map(str, list(answer[x] for x in X))))