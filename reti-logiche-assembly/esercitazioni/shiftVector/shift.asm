%include "../lib/utils.nasm"
; dato un vettore di n elementi, spostare tutti gli elementi a destra di un posto, lasciando 0 al primo elemento
section .data
    v dw 5,6,3,2,3
    n equ ($-v) / 2

section .text
    global _start
_start:
    xor ESI, ESI
    xor AX, AX
loop:
    cmp ESI, n
    jge stampa_v
    mov BX, [v + ESI * 2]
    mov [v + ESI * 2], AX
    mov AX, BX
    inc ESI
    jmp loop

stampa_v:
    XOR ESI, ESI
loop_print:
    cmp ESI, n
    jge exit_p
    printw word [v + ESI * 2]
    inc ESI
    jmp loop_print
exit_p:
    exit 0