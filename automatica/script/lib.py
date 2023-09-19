import sys
import re

def convertToMatrix(string):
    string = string.strip()
    rows = string[1:len(string)].split("},")
    rows = [re.sub("\}|\{|\s", "",row) for row in rows]
    M = []
    for i, row in enumerate(rows):
        M.append([])
        for number in row.split(","):
            M[i].append(number)

    return M

def parseNumber(number):
    if "/" in number:
        number = re.sub("\(|\)", "",number)
        num, den = number.split("/")
        return "\\frac{" + num +"}{" + den +"}"
    return number

def convertToLatexMatrix(matrix):
    latexMatrix = "\\begin{bmatrix}\n"
    for row in matrix:
        latexMatrix += "\t"
        for number in row:
            latexMatrix += f"{parseNumber(number)} & "
        latexMatrix = latexMatrix[:len(latexMatrix) - 2]
        latexMatrix += "\\\\ \n"

    latexMatrix = latexMatrix[:len(latexMatrix) - 4]
    latexMatrix += "\\\\ \n\\end{bmatrix}"
    return latexMatrix

if __name__ == "__main__":
    args = sys.argv
    if len(args) < 2:
        print(f"No input for the program")
    else:
        string = "".join(args[1:])
        matrix = convertToMatrix(string)
        latexMatrix = convertToLatexMatrix(matrix)
        print(latexMatrix)