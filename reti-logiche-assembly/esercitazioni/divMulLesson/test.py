x = [10,3,-7,12,-2]
y = [-2,5,6,4,15]

q = [0]*len(x)
r = [0]*len(x)

def main():
    for i in range(len(x)):
        xi = x[i]
        yi = y[i]
        q[i] = xi // yi
        r[i] = xi % yi

        print(f"{xi}\n{yi}\n{q[i]}\n{r[i]}")

main()
