def binary_search(V, x, first, last):
	if first > last:
		return -1
	else:
		middle = (last + first) // 2
		if x == V[middle]:
			return middle
		if x < V[middle]:
			return binary_search(V, x, first, middle - 1)
		else:
			return binary_search(V, x, middle + 1, last)
