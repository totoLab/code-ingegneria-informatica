; scrivere una procedura assembly che riceva un vettore dw e un valore k
; e restutuisce il numero di coppie di elementi (v[i], v[(i+1)%n]),
; dove n è la lunghezza di v, tali che la loro somma non è multipla di 2^k.
%include "../lib/utils.nasm"

section .data
    v dw 4,5,-2,6,13
    n equ ($-v)/2
    k equ 3

section .bss
    cont resw 1

section .text
    extern _proc
    global _start
_start:
    add ESP, 4
    push v
    push dword n
    push cont
    push dword k
    call _proc
    printw word [cont]
    exit 0
