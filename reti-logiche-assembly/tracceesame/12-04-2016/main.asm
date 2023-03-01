%include "../lib/utils.nasm"

section .data
    a dw 2,1,3,7,4,9,4,6,12,11,9,1
    n equ ($-a)/2

section .bss
    res resw 1

section .text
    extern _sommaAlterna
    global _start
_start:
    add ESP, 4
    push a
    push dword n
    push res
    call _sommaAlterna
    printw word [res]
    exit 0