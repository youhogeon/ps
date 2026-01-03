#19585
import sys
input = sys.stdin.readline

C, N = map(int, input().split())
colors = [input()[:-1] for _ in range(C)]
nicknames = [input()[:-1] for _ in range(N)]
Q = int(input())
teams = [input()[:-1] for _ in range(Q)]

def add_ch(node: list[bool, list], char: int):
    if not node[1]:
        node[1] = [None] * 26

    if node[1][char] is None:
        node[1][char] = [False, []]

    return node[1][char]

def add(node: list[bool, list], data: str):
    for ch in data:
        node = add_ch(node, ord(ch) - 97)
    
    node[0] = True

color_root: list[bool, list] = [False, []]
nickname_root: list[bool, list] = [False, []]
nickname_set = set()

for color in colors:
    add(color_root, color)

for nickname in nicknames:
    nickname_set.add(nickname)
    # add(nickname_root, nickname[::-1])

def check(team: str):
    found_a = []
    current = color_root
    for i in range(len(team)-1):
        if not current[1]:
            break

        ch = team[i]

        current = current[1][ord(ch) - 97]

        if current is None:
            break

        if current[0]:
            # found_a.append(i + 1)
            if team[i + 1:] in nickname_set:
                return True
            
    return False

    

    # found_b = []
    # current = nickname_root
    # for i in range(len(team)-1):
    #     if not current[1]:
    #         break

    #     ch = team[len(team) - 1 - i]

    #     current = current[1][ord(ch) - 97]

    #     if current is None:
    #         break

    #     if current[0]:
    #         found_b.append(i + 1)

    # found_b = found_b[::-1]
    # i, j = 0, 0
    # while i < len(found_a) and j < len(found_b):
    #     a, b = found_a[i], found_b[j]
    #     if a+b == len(team):
    #         return True

    #     if a+b < len(team):
    #         i += 1
    #     else:
    #         j += 1

    # return False


for team in teams:
    print("Yes" if check(team) else "No")