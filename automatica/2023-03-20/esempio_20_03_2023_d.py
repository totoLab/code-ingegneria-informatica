#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Mar 20 10:56:05 2023

@author: Massimo
"""

import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# Modello del circuito RC

# Costanti del circuito
R = 1
C0 = 1 # Capacità

# Definiamo la quaterna A,B,C,D
A = -1/(R*C0)
B = 1/(R*C0)

# Misuro contestualmente la tensione sul condensatore e la corrente che attraversa il circuito
C = [ [1],[-1/R] ]
D = [ [0],[1/R] ]

# Stato inizale v_{c0}
x0 = 0

# Definisco un modulo che esplicita la forma temporale dell'ingresso
def u_t(t):
    return 1

# Asse dei tempi
t = np.arange(0,4,0.01)

# Campione l'ingresso in base all'asse dei tempi
u = np.zeros(len(t))

for i in range(len(t)):
    u[i] = u_t(t[i])
    
# Variabile python che imahazzina il modello 
circuito_RC = signal.StateSpace(A,B,C,D)

# Simula il circuito
_, y, x = signal.lsim(circuito_RC,u,t,x0)

# Grafico della tensione e della corrente
plt.subplot(2,1,1)
plt.plot(t,y[:,0])
plt.grid(True)
plt.title('Tensione sul condensatore')

plt.subplot(2,1,2)
plt.plot(t,y[:,1])
plt.grid(True)
plt.title('Coorente nel circuito')