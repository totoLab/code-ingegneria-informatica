import math

a = float(input("a: "))
b = float(input("b: "))
c = float(input("c: "))

delta = b * b - 4 * a * c
if delta >= 0:
    x1 = -1 * b - math.sqrt(delta)
    x2 = -1 * b + math.sqrt(delta)
    print("Real solutions are: x1 = {} and x2 = {}.".format(x1, x2))
else:
    print("No real solutions.")