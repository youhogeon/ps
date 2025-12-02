def set_node_value(nodes: list[int], nid: int):
    l_child = nid * 2
    r_child = l_child + 1

    if len(nodes) <= r_child:
        return

    set_node_value(nodes, l_child)
    set_node_value(nodes, r_child)

    nodes[nid] = min(
        nodes[l_child], nodes[r_child]
    )

while (inputs := input()) != "0":
    inputs = list(map(int, inputs.split()))[1:]

    count = len(inputs)
    leaf_count = 2 ** (count - 1).bit_length()
    nodes = [1000000001] * leaf_count + [*inputs] + [0] * (leaf_count - count) # 미사용 노드(id: 0) 포함

    set_node_value(nodes, 1)

    max_value = -1

    for init_node in range(leaf_count, leaf_count * 2):
        curr_node = init_node

        l_p_node: int | None = None
        r_p_node: int | None = None

        while True:
            if l_p_node is not None and r_p_node is not None:
                break

            if curr_node == 1:
                break

            if curr_node % 2 == 0: # 좌측노드이었으면
                if nodes[curr_node + 1] < nodes[init_node] and r_p_node is None:
                    r_p_node = curr_node + 1
            else: # 우측노드이었으면
                if nodes[curr_node - 1] < nodes[init_node] and l_p_node is None:
                    l_p_node = curr_node - 1

            curr_node //= 2

        if l_p_node is not None:
            # 내려가면서 init_node보다 작으면 교체 (우측 자식이 우선순위 높음)
            while l_p_node * 2 < len(nodes):
                if nodes[l_p_node * 2 + 1] < nodes[init_node]:
                    l_p_node = l_p_node * 2 + 1
                else:
                    l_p_node = l_p_node * 2

        if r_p_node is not None:
            # 내려가면서 init_node보다 작으면 교체 (좌측 자식이 우선순위 높음)
            while r_p_node * 2 < len(nodes):
                if nodes[r_p_node * 2] < nodes[init_node]:
                    r_p_node = r_p_node * 2
                else:
                    r_p_node = r_p_node * 2 + 1

        if l_p_node is None:
            l_p_node = leaf_count - 1

        if r_p_node is None:
            r_p_node = leaf_count * 2

        max_value = max(max_value, (r_p_node - l_p_node -1) * nodes[init_node])

    print(max_value)