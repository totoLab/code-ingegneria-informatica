import matplotlib.pyplot as plt

x = ['a', 'b', 'c']
y1 = [2, 4, 3]
y2 = [3,4,5]

plt.plot(x,y1,label='Linea 1')
plt.plot(x,y2,label='Linea 2', linetyle='dashed')

plt.legend()
plt.xlabel('Asse x')
plt.ylabel('Asse y')
plt.title('Il mio grafico')

plt.show()