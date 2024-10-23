def sieve(divisor_index, numbers):
    # Convert keys to a list and get the current divisor
    keys_list = list(numbers.keys())
    divisor = keys_list[divisor_index]
    
    # Iterate through the numbers and remove non-primes
    for number in keys_list[divisor_index + 1:]:
        if number % divisor == 0:
            numbers.pop(number)

    # Recursively call sieve until we have processed all numbers
    if divisor_index < len(numbers) - 1:
        sieve(divisor_index + 1, numbers)
    else:
        print("Prime numbers:", list(numbers.keys()))

def sieveI(numbers: list):
    index = 0
    while index < len(numbers) - 1:
        divisor = numbers[index]
        for i, number in reversed(list(enumerate(numbers[index + 1:]))):
            if number % divisor == 0:
                numbers.pop(index + 1 + i)
        index += 1

    print("Prime numbers:", numbers)

def sieveI_optimized(numbers):
    index = 0
    while index < len(numbers) - 1:
        divisor = numbers[index]
        i = index + 1
        while i < len(numbers):
            if numbers[i] % divisor == 0:
                numbers.pop(i)
            else:
                i += 1
        index += 1

    print("Prime numbers:", numbers)

n = 10 ** 8 # int(input("Last number of the sequence to generate? "))
numbers = {number: None for number in range(2, n + 1)}

sieveI_optimized(list(range(1, n + 1, 2))[1:])