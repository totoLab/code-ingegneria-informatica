def fibSum(secondLast, last, max):
    current = secondLast + last
    print(current)
    last = secondLast
    secondLast = current
    max -= 1
    if max > 0:
        fibSum(secondLast, last, max)

last = 0
secondLast = 1
max = int(input("How many numbers of the sequence? "))
current = fibSum(secondLast, last, max)