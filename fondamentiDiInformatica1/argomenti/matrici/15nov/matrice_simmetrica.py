import ulm
def e_simmetrica(matrice):
	if not ulm.e_quadrata(matrice):
		return False
	ordine = len(matrice)
	for i in range(ordine - 1):
		for j in range(i + 1, ordine):
			if matrice[i][j] != matrice[j][i]:
				return False
	return True


matrice = [
	[1,3,5,4,2],
	[3,2,1,1,6],
	[5,1,0,2,3],
	[4,1,2,0,9],
	[2,6,3,9,4]
]
print(e_simmetrica(matrice))
