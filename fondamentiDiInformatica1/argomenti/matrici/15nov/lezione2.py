import matplotlib.pyplot as plt

x = ['Italia', 'Germania', 'UK']
y = [65, 80, 60]

plt.bar(x,y,label='Popolazione', width = 0.5)

plt.legend()
plt.xlabel('Nazioni')
plt.ylabel('Popolazione (in milioni)')
plt.title('Il mio grafico')

plt.show()