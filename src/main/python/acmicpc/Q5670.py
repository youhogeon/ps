import sys
input = sys.stdin.readline

class Node:
    def __init__(self):
        self.is_word = False
        self.children: list[Node | None] = [None] * 26
        self.count = 0

    def append(self, chars: str):
        node: Node = self
        for ch in chars:
            nid = ord(ch) - 97

            if node.children[nid] is None:
                node.children[nid] = Node()
                node.count += 1

            node = node.children[nid]
        
        node.is_word = True

    def __str__(self):
        return f'{self.is_word} / {self.children}'
    
    def calc(self, chars: str):
        node: Node = self
        count = 0

        for ch in chars:
            nid = ord(ch) - 97

            node = node.children[nid]
            if node.count == 1 and not node.is_word:
                continue

            count += 1

        return count

while True:
    try:
        N = int(input())
    except:
        break

    WORDS = [input().strip() for _ in range(N)]

    ROOT = Node()

    for word in WORDS:
        ROOT.append(word)

    count = 0
    for word in WORDS:
        count += ROOT.calc(word)
    
    print(f"{(count/N):.2f}")