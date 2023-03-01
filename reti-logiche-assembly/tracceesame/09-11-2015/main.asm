%include "../lib/utils.nasm"

section .data
    v dw 7,9,5,0,1,3,2,6,8,4
    n equ ($-v)/2

section .bss
    w resw n

section .text
    extern _proc
    global _start
_start:
    add ESP, 4
    push v
    push dword n
    push w
    call _proc

    xor ESI, ESI
stampa_w:
    cmp ESI, n
    jge end
    printw word [w + ESI*2]
    inc ESI
    jmp stampa_w
end:
    exit 0