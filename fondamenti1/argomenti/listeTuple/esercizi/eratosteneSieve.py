def sieve(divisor, array):
    for number in array:
        if number != divisor and number % divisor == 0:
            array.pop(array.index(number))

    #print("Divisor: {}, state of list: {}".format(divisor, array))
    if array[len(array) - 1] != divisor:
        newDivisor = array[array.index(divisor) + 1]
        sieve(newDivisor, array)
    else:
        print("First {} prime numbers: {}".format(len(array), array))

n = int(input("Last number of the sequence to generate? "))
array = list(range(2, n))
sieve(array[0], array)