%include "../lib/utils.nasm"
section .data
    a dw 1000,4,5
    n equ ($-a)/2

section .bss
    s resb 1

section .text
    extern proc
    global _start
_start:
    add ESP, 4
    push a
    push n
    push s
    call proc
    printw word [s]
    exit 0