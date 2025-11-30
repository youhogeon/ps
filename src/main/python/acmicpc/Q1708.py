N = int(input())

coord = [
    list(map(int, input().split(" ")))
    for _ in range(N)
]

coord.sort()

nodes = [coord[0]]
coord = coord[1:]

for coor in coord:
    if coor[0] == nodes[0][0]:
        coor.append(999999999)
    else:
        coor.append((coor[1] - nodes[0][1]) / (coor[0] - nodes[0][0]))
    
    coor.append( (coor[0] - nodes[0][0]) * (coor[0] - nodes[0][0]) + (coor[1] - nodes[0][1]) * (coor[1] - nodes[0][1]) )

coord.sort(key=lambda x: (-x[2], x[3]))

nodes.append(coord[0])
coord = coord[1:]


idx = 0
while len(coord) > idx:
    new_dot = coord[idx]

    x1, y1 = nodes[-2][0], nodes[-2][1]
    x2, y2 = nodes[-1][0], nodes[-1][1]
    x3, y3 = new_dot[0], new_dot[1]

    direction = x1*y2+x2*y3+x3*y1-x2*y1-x3*y2-x1*y3

    if direction == 0:
        nodes.pop()
        nodes.append(coord[idx])
        idx += 1
    elif direction < 0:
        nodes.append(coord[idx])
        idx += 1
    else:
        nodes.pop()

print(len(nodes))
