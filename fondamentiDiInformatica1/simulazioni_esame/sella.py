import ulm
import json

def max_list(matrix, col):
    ret = 0
    maximum = float("-inf")
    for i in range(len(matrix)):
        el = matrix[i][col]
        if el > maximum:
            maximum = el
            ret = i
    
    return ret

def min_list(array):
    ret = 0
    minimum = float("inf")
    for i, el in enumerate(array):
        if el < minimum:
            minimum = el
            ret = i

    return ret

def sella(matrix):
    selle = []
    for i, row in enumerate(matrix):
        index_of_min = min_list(row)
        max_in_col = max_list(matrix, index_of_min)
        if matrix[max_in_col][index_of_min] == row[index_of_min]:
            selle.append((i,index_of_min))

    return selle

def create_data(ord, max_value, wanted_selle):
    selle_totali = []
    data_dict = {}
    index = 0
    cont = 0
    while cont < wanted_selle:
        index += 1
        m = ulm.costruisci_matrice_valori_casuali(ord, ord, max_value)
        selle = sella(m)
        selle_num = len(selle)
        cont += selle_num
        data_dict[index] = {"length": selle_num, "selle": selle, "matrice": m}

    return data_dict

def selle_list(data):
    selle_totali = []
    for key in data:
        selle_totali.extend(data[key]["selle"])

    return selle_totali

def main(ord, max_value, wanted_selle):
    data = create_data(ord, max_value, wanted_selle)
    selle_totali = selle_list(data)

    json_data = {}
    json_data["selle"] = {"length": len(selle_totali), "coordinate": selle_totali}
    json_data["verbose_data"] = {"length": len(data), "data": data}

    final_dictionary = json.dumps(json_data)
    return final_dictionary

import sys

def extract_argvs():
    ord = sys.argv[1]
    max_value = sys.argv[2]
    wanted_selle = sys.argv[3]
    return int(ord), int(max_value), int(wanted_selle)

ord, max_value, wanted_selle = extract_argvs()

data = main(ord, max_value, wanted_selle)
print(data) # write to file with standard output because I'm too lazy to do it with python
