import sys
import eratosteneSieve

def generate_table(x):
    d = {}
    for i in range(11):
        d[i] = x * i
    
    return d

def bigger_smaller(d, x):
    ret = 1
    max = -1
    for key in d:
        value =  d[key]
        if value <= x and value > max:
            max = value
            ret = key

    return ret, max

def reciprocal(x):
    return division(1, x)

def division(x, y):
    ret = ""
    y_table = generate_table(y)
    remainders = []
    while (len(ret) < y * 2 - 2 and x != 0 and x < y):
        x = x * 10
        to_ret, bs = bigger_smaller(y_table, x)
        x -= bs
        if x not in remainders:
            remainders.append(x)
            ret += str(to_ret)
        else:
            return ret, remainders

    return ret,remainders

x = int(sys.argv[1])
if x == 1 or not prime(x):
    quoziente, resti = reciprocal(x)
    # zero_dot = f"0.{r}"
    print(f"1/{x} = 0.{quoziente}")
else:
    print("not a prime, use your calculator")