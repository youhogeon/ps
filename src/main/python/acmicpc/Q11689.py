# 11689

import math

N = int(input())
SN = int(math.sqrt(N))

is_prime = [True] * (SN + 1)
is_prime[0] = False
is_prime[1] = False

for i in range(SN + 1):
    if not is_prime[i]:
        continue
    
    for j in range(i * 2, SN + 1, i):
        is_prime[j] = False

primes = []

for prime, _is_prime in enumerate(is_prime):
    if not _is_prime:
        continue

    primes.append(prime)

result = N
soinsu: set[int] = set()
for p in primes:
    while result % p == 0:
        soinsu.add(p)
        result //= p

if result > 1:
    soinsu.add(result)

result = N
for s in soinsu:
    result -= result // s

print(result)