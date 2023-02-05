def confronto_e_scambio_ascii(lista, el1, el2):
	max_loops = min(len(lista[el1]), len(lista[el2]))
	for x in range(max_loops):
		letter = lista[el1][x]
		previous_letter = lista[el2][x]
		if previous_letter != letter:
			if ord(previous_letter) > ord(letter):
				t = lista[el1]
				lista[el1] = lista[el2]
				lista[el2] = t

	return lista

	

def string_bubble_sort(lista):
	n = len(lista)
	for i in range(n - 1):
		for j in range(n - 1, i, -1):
			#confronto_e_scambio_ascii(lista, j, j - 1)
			if lista[j - 1] > lista[j]:
				t = lista[j]
				lista[j] = lista[j - 1]
				lista[j - 1] = t

	return lista

def sort_strings_list(unsorted_list):
	if len(unsorted_list) < 2:
		return unsorted_list

	return string_bubble_sort(unsorted_list)

def f(L1, L2):
	ret = []
	for element in L1:
		if element in L2:
			ret.append(element)

	ret = sort_strings_list(ret)

	return ret

L1 = ['albero', 'mare', 'zebra', 'casa']
L2 = ['zebra', 'albero', 'barca', 'verde', 'abaco']
print(f(L1, L2))