#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Mar 20 12:17:21 2023

@author: expert
"""

import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

plt.rcParams['text.usetex'] = True
plt.rc('text', usetex=True)
plt.rc('font', family='serif')

# Modello del circuito con due condesantori

# Costanti del circuito
R1 = 1e3
R2 = 2e3
C1 = 1e-7 
L1 = 0.05

# Definiamo la quaterna A,B,C,D
A = [  [-1/(R1*C1), -1/(C1)], \
       [1/(L1), -R2/(L1)] ] 
B = [ [1/(R1*C1)], [0] ]
C = [1,-R2]
D = 0

# Stato inizale [v_{c10}, v_{c20}]
x0 = [0,0] # [1,3]

# Definisco un modulo che esplicita la forma temporale dell'ingresso
def u_t(t):
    return 1 # 0
# Asse dei tempi
t = np.arange(0,8e-4,1e-6)

# Campione l'ingresso in base all'asse dei tempi
u = np.zeros(len(t))

for i in range(len(t)):
    u[i] = u_t(t[i])
    
# Variabile python che imahazzina il modello 
circuito_RLC = signal.StateSpace(A,B,C,D)

# Simula il circuito
_, y, x = signal.lsim(circuito_RLC,u,t,x0)

# Grafico della tensione sull'induttanza

plt.plot(t,y)
plt.ylabel(r'$v_{L1}$', rotation=0)
plt.grid(False)
plt.xlabel('sec.')
plt.show()