%include "../lib/utils.nasm"
section .data
    v dw 2,5,10,1,16,9,4
    n equ ($-v)/2

section .bss
    ris resw 1

section .text
    extern _max
    global _start
_start:
    add ESP, 4
    push v
    push n
    push ris
    call _max
    printw word [ris]
    exit 0