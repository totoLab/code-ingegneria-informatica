def verificaDueNodi(A):
	return verifica(A, 0, 0)

def verifica(A, l, cont):
	if isEmpty(A):
		return False
	if value(A) + l < 0
		cont += 1
	if cont >= 2:
		return True
	return verifica( left(A), l + 1, cont ) or verifica( right(A), l + 1, cont )


