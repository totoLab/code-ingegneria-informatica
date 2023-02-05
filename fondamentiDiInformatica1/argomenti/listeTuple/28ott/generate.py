import random

def list_gen(length):
    randomlist = []
    for _ in range(length):
        generated = random.randint(0, length**3)
        randomlist.append(generated)

    return randomlist

def list_same_element(length):
    return [random.randint(0, 10000)] * length
