import generate as gen

def genera_lista(v1, v2):
    v3 = []
    for i in range(len(v1)):
        if i%2 == 0:
            v3.append(gen_new_element(v1, i, "sum"))
        else:
            v3.append(gen_new_element(v2, i, "multiplication"))

    return v3

def gen_new_element(v, start_range, operation):
    if operation == "sum":
        element = 0
        for i in range(start_range + 1, len(v)):
            element += v[i]

    elif operation == "multiplication":
        element = 1
        for i in range(start_range + 1, len(v)):
            element *= v[i]

    return element


length = int(input("Vector length: "))
vector1 = gen.list_gen(length)
vector2 = gen.list_gen(length)
vector3 = genera_lista(vector1, vector2)

print("Vector 1: {}, vector 2: {}".format(vector1, vector2))
print("Generated vector: {}".format(vector3))
