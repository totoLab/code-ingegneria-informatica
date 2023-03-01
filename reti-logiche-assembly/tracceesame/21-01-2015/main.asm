%include "../lib/utils.nasm"

section .data
    w dw 2,3,3,4,5,8,9
    n equ ($-v)/2

section .bss
    v resw n
    ris resw 1

section .text
    extern _proc
    global _start
_start:
    add ESP, 4
    push w
    push dword n
    push v
    push ris
    call _proc
    printw word [ris]   ; result
    prints 10           ; new line

    xor ESI, ESI
print_v:
    cmp ESI, n
    jge end
    printw word [n + ESI*2]
    inc ESI
    jmp print_v

    exit 0