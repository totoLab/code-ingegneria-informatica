; dato un vettore di n elementi (n pari), costruito così: (i, i+1) con i pari, rappresenta la temperatura minima e massima per una citta.
; scrivere una procedura che stampi l'escursione termica più alta e l'indice della temperatura minima della città a cui corrisponde tale escursione.
%include "../lib/utils.nasm"

section .data
    c dw 2,7,2,8,1,10,-2,6
    n equ ($-c)/2

section .bss
    max resw 1
    i resd 1

section .text
    extern _esc
    global _start
_start:
    push c
    push dword n
    push max
    push i
    call _esc
    printw word [max]
    printd dword [i]

    exit 0