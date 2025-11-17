T = input()
P = input()

p_idx = [0] * len(P)
j = 0
for i in range(1, len(P)):
    while j > 0 and P[i] != P[j]:
        j = p_idx[j - 1]
    if P[i] == P[j]:
        j += 1
        p_idx[i] = j


i = 0
j = 0
answer = []
while i + j < len(T) and j < len(P):
    if T[i + j] == P[j]:
        j += 1

        if j == len(P):
            answer.append(i + 1)

            if j > 1:
                combo = p_idx[j - 2]
                i = i + j - combo - 1
                j = combo
            else:
                i += 1
                j = 0

        continue

    if j == 0:
        i += 1
        j = 0

        continue

    combo = p_idx[j - 1]
    i = i + j - combo
    j = combo


print(len(answer))
print(" ".join(map(str, answer)))
