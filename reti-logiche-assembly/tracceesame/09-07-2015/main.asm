%include "../lib/utils.nasm"

section .data
    v dw 2,4,1,2,5,8,9,3
    l equ ($-v)/4

section .bss
    w resw l

section .text
    extern _proc
    global _start
_start:
    add ESP, 4
    push v
    push dword l
    push w
    call _proc

    xor ESI, ESI
stampa_w:
    cmp ESI, l
    jge end
    printw word [w + ESI*2]
    inc ESI
    jmp stampa_w
end:
    exit 0