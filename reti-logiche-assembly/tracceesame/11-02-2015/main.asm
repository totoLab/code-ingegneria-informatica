%include "../lib/utils.nasm"

section .data
    v dw 13,8,-1,-2,-3
    n equ ($-v)/2

section .bss
    ris resw 1

section .text
    extern _suffisso
    global _start
_start:
    add ESP, 4
    push v
    push dword n
    push ris
    call _suffisso
    printw word [ris]
    exit 0