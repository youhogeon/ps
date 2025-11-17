N = int(input())

C = [[1] * 53 for _ in range(53)]

for i in range(1, 53):
    C[52][i] = int(C[52][i - 1] * (53-i)/i)

for i in range(51, 0, -1):
    for j in range(i + 1):
        C[i][j] = int(C[i + 1][j] * (i - j + 1) / (i + 1))


def nofourcards(tot: int, n: int, leftpair: int) -> int:
    if tot < 4:
        return 0
    
    if leftpair <= 0:
        return 0
    
    if n < 3:
        return C[tot][n]
    
    return C[tot][n] - fourcards(tot, n, leftpair)
    
def fourcards(tot: int, n: int, leftpair: int) -> int:
    if n < 4:
        return 0
    
    if leftpair <= 0:
        return 0
    
    if tot <= 0:
        return 0
    
    answer = 0

    i = 0
    while (n - (i - 1) * 4) >= 8:
        answer += C[leftpair][1 + i] * nofourcards(tot - 4 * (i + 1), n - (i + 1) * 4, leftpair - 1 - i)
        i += 1

    return answer

print(fourcards(52, N, 13) % 10007 if N < 52 else 1)