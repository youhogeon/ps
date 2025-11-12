from queue import Queue
import sys
input = sys.stdin.readline

N = int(input())

move = ((-1, 0), (0, -1), (0, 1), (1, 0))

class Space:
    def __init__(self, space: list[list[int]] | None = None):
        if space is None:
            return

        self.space = space

        for r, row in enumerate(space):
            for c, el in enumerate(row):
                if el == 9:
                    self.r = r
                    self.c = c
                    self.space[r][c] = 0

                    return
    
    def clone(self):
        space = Space()
        space.space = [list(row) for row in self.space]
        space.r = self.r
        space.c = self.c

        return space
    
    def move(self, dr: int, dc: int, my_size: int):
        if self.r == 0 and dr < 0 or self.r == N-1 and dr > 0 or self.c == 0 and dc < 0 or self.c == N-1 and dc > 0:
            return None, False
        
        if self.space[self.r + dr][self.c + dc] > my_size:
            return None, False
    
        space = self.clone()
        space.r = self.r + dr
        space.c = self.c + dc

        if 0 < space.space[space.r][space.c] < my_size:
            space.space[space.r][space.c] = 0
            return space, True

        return space, False

    def __str__(self):
        result = ''
        for row in self.space:
            result += f'{row}\n'

        return f'{result}{self.r} / {self.c}'


space: Space = Space([
    list(map(int, input().split(" ")))
    for _ in range(N)
])

########

def calc_next_space(space: Space, my_size: int):
    visited = [
        [False] * N
        for _ in  range(N)
    ]

    queue: Queue[tuple[int, Space]] = Queue()
    queue.put((0, space))

    catched_at = 100000000000
    catched_spaces: list[Space] = []

    while not queue.empty():
        moved, space = queue.get()

        if moved >= catched_at:
            break

        for m in move:
            new_space, catched = space.move(*m, my_size)

            if new_space is None:
                continue

            if catched:
                catched_at = moved + 1
                catched_spaces.append(new_space)

                continue

            if visited[new_space.r][new_space.c]:
                continue

            visited[new_space.r][new_space.c] = True
            queue.put((moved + 1, new_space))
    
    if catched_at != 100000000000:
        catched_spaces.sort(key=lambda s: (s.r, s.c))

        return catched_spaces[0], catched_at

    return None, -1


tot_moved = 0
size = 2
left_to_size_up = 2

while True:
    new_space, moved = calc_next_space(space, size)

    if new_space is None:
        break

    tot_moved += moved
    space = new_space
    left_to_size_up -= 1

    if left_to_size_up == 0:
        size += 1
        left_to_size_up = size
        
print(tot_moved)