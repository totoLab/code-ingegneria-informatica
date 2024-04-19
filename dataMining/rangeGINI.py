def get_data():
    M = [
        [125, False],
        [100, False],
        [70, False],
        [120, False],
        [95, True],
        [60, False],
        [220, False],
        [85, True],
        [75, False],
        [90, True]
    ]  
    return M

def weighted_gini_matrix(matrix):
    a, b = matrix[0]
    c, d = matrix[1]

    sum_n1 = a + c
    gini_a_c = 1 - (a / sum_n1)**2 - (c / sum_n1)**2
    sum_n2 = b + d
    gini_b_d = 1 - (b / (sum_n2))**2 - (d / (sum_n2))**2
    
    total = sum_n1 + sum_n2
    weighted_gini = (sum_n1 / total) * gini_a_c + (sum_n2 / total) * gini_b_d
    
    return round(weighted_gini, 3)

def calculate_ginis(M, split_positions):
    GINIs = []
    for split_point in split_positions:
        count_matrix = [
            [0, 0],
            [0, 0]
        ]
        for [value, cheat] in M:
            if value <= split_point:
                j = 0
            else:
                j = 1
            if cheat:
                i = 0
            else:
                i = 1
            
            count_matrix[i][j] += 1

        try:
            gini = weighted_gini_matrix(count_matrix)
        except:
            gini = float("inf")
            
        GINIs.append(gini)
    return GINIs

def min_index(L):
    best_index = min(L)
    for i, value in enumerate(L):
        if value == best_index:
            return i

def best_split_continuos_value(M):
    sorted_values = sorted(M, key=lambda x: x[0], reverse=False)
    split_positions = []
    sum = 0
    for i in range(1, len(sorted_values)):
        first = sorted_values[i - 1][0]
        second = sorted_values[i][0]
        half_difference = (second - first) / 2
        sum += half_difference
        split_positions.append(int(first + half_difference))

    media = sum/len(sorted_values) - 1 
    first = int(sorted_values[0][0] - media)
    last  = int(sorted_values[len(sorted_values) - 1][0] + media)
    split_positions = [first] + split_positions + [last]

    GINIs = calculate_ginis(M, split_positions)

    index = min_index(GINIs)
    best_value = split_positions[index]
    return best_value


def main():
    M = get_data()
    print(f"best split value: {best_split_continuos_value(M)}")

if __name__ == "__main__":
    main()