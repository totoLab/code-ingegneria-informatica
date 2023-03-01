%include "../lib/utils.nasm"

section .data
    v dw 2, 6, 9, 4, 10, 9, 1, 2, 4, 11
    w dw 8, 3, 6, 5, 8, 5, 3, 2, 10, 2
    n equ ($-w)/2

section .bss
    t resw 2*n

section .text
    extern proc
    global _start
_start:
    push dword n
    push t
    push w
    push v
    call proc

    xor ESI, ESI
make_t:
    cmp ESI, 2*n
    jge end
    mov AX, [t + 2*ESI]
    printw AX
    inc ESI
    jmp make_t
end:
    exit 0