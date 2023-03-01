%include "../lib/utils.nasm"

section .data
    a dw 2,5,1,-2,-5,-7,0,-1,1
    n equ ($-a)/2

section .bss
    ks resw 1

section .text
    extern _salto
    global _start
_start:
    add ESP, 4
    push a
    push dword n
    push ks
    call _salto
    printw word [ks]
    exit 0