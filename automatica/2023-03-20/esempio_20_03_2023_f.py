#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Mar 20 12:17:21 2023

@author: expert
"""

import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# Modello del circuito con due condesantori

# Costanti del circuito
R1 = 1e3
R2 = 2e3
C1 = 1e-6 
C2 = 0.4e-6

# Definiamo la quaterna A,B,C,D
A = [  [-(1/(R1*C1))*(1+R1/R2), 1/(R2*C1)], \
       [1/(R2*C2), -1/(R2*C2)] ] 
B = [ [1/(R1*C1)], [0] ]
C = [0,1]
D = 0

# Stato inizale [v_{c10}, v_{c20}]
x0 = [0,0] # [1,3]

# Definisco un modulo che esplicita la forma temporale dell'ingresso
def u_t(t):
    return 1 # 0
# Asse dei tempi
t = np.arange(0,1e-2,1e-5)

# Campione l'ingresso in base all'asse dei tempi
u = np.zeros(len(t))

for i in range(len(t)):
    u[i] = u_t(t[i])
    
# Variabile python che imahazzina il modello 
circuito_RC12 = signal.StateSpace(A,B,C,D)

# Simula il circuito
_, _, x = signal.lsim(circuito_RC12,u,t,x0)

# Grafico della tensione

plt.subplot(2,1,1)
plt.plot(t,x[:,0])
plt.ylabel('v_c1')
plt.grid(True)

plt.subplot(2,1,2)
plt.plot(t,x[:,1])
plt.ylabel('v_c2')
plt.xlabel('sec.')
plt.grid(True)