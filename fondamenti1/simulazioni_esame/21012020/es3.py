# 3.1
def verifica(F, L):
	for i in range(len(L)):
		if somma_colonna(F, i) <= L[i][1]:
			return False
	return True

# 3.2
def dominante(F,k):
	for dipartimento in range(len(F)):
		if somma_superiore(F, dipartimento, k) and maggiore_tutti(F, dipartimento, k):
			return dipartimento
	
	return -1

def somma_superiore(F, dipartimento, k):
	if F[dipartimento][k] > F[k][dipartimento]:
		return True
	return False

def maggiore_tutti(F, dipartimento, k):
	for i in range(len(F[dipartimento])):
		if i!=k and i!=dipartimento and F[dipartimento][i] < F[k][i]:
			return False
	return True

# 3.3
def somma_riga(matrice, riga):
	somma = 0
	for i in range(len(matrice[0])):
		somma += matrice[riga][i]

	return somma

def somma_colonna(matrice, col):
	somma = 0
	for riga in range(len(matrice)):
		somma += matrice[riga][col]

	return somma


def statistiche(F,L):
	ret = []
	for i in range(len(F)):
		saldo = somma_colonna(F, i) - somma_riga(F, i)
		ret.append([L[i][0], saldo])

	return ret

# 3.4
def controllo_invio(matrice, dipartimenti, i, j, d):
	somma_totale = somma_colonna(matrice, j) + d
	if L[i][1] >= somma_totale:
		return True
	return False

def aggiungi_invio(F,L,i,j,d):
	if F[i][j] == 0 and controllo_invio(F, L, i, j, d):
		F[i][j] = d

	return F

F = [
	[0, 50, 20, 50, 60],  
	[20, 0, 20, 0, 40],   
	[0, 30, 0, 0, 0],     
	[40, 40, 50, 0, 60],
	[30, 0, 30, 50, 0]
]
	
L = [
	['Amministrazione', 100], #[nome, massimo_badget]
	['Produzione', 150],
	['Distribuzione', 200],
	['Acquisti', 150],
	['Vendite', 200],
]

print(verifica(F, L))
print(dominante(F, 4))
print(statistiche(F,L))
print(aggiungi_invio(F, L, 1, 3, 20))