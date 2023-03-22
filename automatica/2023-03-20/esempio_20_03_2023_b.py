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
C = 1
D = 0

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
_, _, x = signal.lsim(circuito_RC,u,t,x0)

# Grafico della tensione
plt.plot(t,x)
plt.grid(True)