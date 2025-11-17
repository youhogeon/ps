N = int(input())
HO = [tuple(map(int, input().split())) for _ in range(N)]
D = int(input())


pm: list[tuple[int, int]] = [] # 자의 끝이 x일 때 y만큼 값을 더함

for _h, _o in HO:
    h = min(_h, _o)
    o = max(_h, _o)

    if o - h > D:
        continue

    pm.append((o, 1))
    pm.append((h+D+1, -1))

pm.sort()

count = 0
max_count = 0
for i, (pos, delta) in enumerate(pm):
    next_pos = pm[i + 1][0] if i + 1 < len(pm) else -1

    count += delta

    if pos != next_pos:
        max_count = max(max_count, count)

print(max_count)