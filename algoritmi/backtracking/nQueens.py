def nQueens(C, row): # O(n^n)
    if row == len(C): # if esisteSoluzione
        return True
    for col in range(len(C)):
        if check(C, row, col):
            C[row][col] = 1 # assegna
            if nQueens(C, row + 1): # prossimoPuntoDiScelta
                return True
            C[row][col] = 0 # deassegna
    return False

def check(C, row, col): # O(row) -> O(n)
    for i in range(row):
        if C[i][col] == 1:
            return False

    for j in range(col):
        if C[row][j] == 1:
            return False

    i = row - 1
    j = col - 1
    while i >= 0 and j >= 0:
        # elemento su diagonale principale ed elemento specchiato sulla diagonale secondaria
        if C[i][j] == 1 or C[i][len(C[0]) - j - 1]:
            return False
        i, j = i - 1, j - 1

    return True

n = 4
chess = [ [0 for i in range(n)] for j in range(n) ]
if nQueens(chess, 0):
    printMatrix(chess)
else:
    print("Nessuna soluzione")