# https://en.wikipedia.org/wiki/Longest_common_subsequence

def LCS(X, Y, i, j):
    if i < 0 or j < 0:
        return 0
    if X[i] == Y[j]:
        return 1 + LCS(X, Y, i - 1, j - 1)
    else:
        return max(
            LCS(X, Y, i - 1, j), LCS(X, Y, i, j - 1)
        )

def LCSi(X, Y):
    X.insert(0, "e")
    Y.insert(0, "e")
    M = []
    for i in range(len(X)):
        M.append([])
        for j in range(len(Y)):
            M[i].append(0)

    for i in range(1, len(X)):
        for j in range(1, len(Y)):
            if X[i] == Y[j]:
                M[i][j] = M[i - 1][j - 1] + 1
            else:
                M[i][j] = max(
                    M[i - 1][j],
                    M[i][j - 1]
                )
    return M

def printMatrixWithTitles(M, X, Y):
    print("   " + "  ".join(Y))
    for i, row in enumerate(M):
        print(X[i], row)
    print()

X, Y = ["A", "B", "C", "B", "D", "A", "B"], ["B", "D", "C", "A", "B", "A"]
M = LCSi(X, Y)
printMatrixWithTitles(M, X, Y)

# TODO modificare il codice per salvare i percorsi oltre la lunghezza