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


def merge_sort(A, first, last):
	if first < last:
		middle = (first + last) // 2
		merge_sort(A, first, middle)
		merge_sort(A, middle + 1, last)
		merge(A, first, middle, last)

def merge(A, first, middle, last):
	i, j = first, middle + 1
	C = []
	while i <= middle and j <= last:
		if A[i] <= A[j]:
			C.append(A[i])
			i += 1
		else:
			C.append(A[j])
			j += 1

	while i <= middle:
		C.append(A[i])
		i += 1

	while j <= last:
		C.append(A[j])
		j += 1

	for i in range(len(C)):
		A[first + i] = C[i]
