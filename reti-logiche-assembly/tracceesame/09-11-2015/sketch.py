v = [7,9,5,0,1,3,2,6,8,4]
w = [0] * len(v)

i = 0
while i <= len(v) - 1:
    w[v[i]] = i
    i += 1

print(v) 
print(w)