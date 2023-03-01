%include "../lib/utils.nasm"

section .data
    a dw 4,1,0,-3,2,-1,0,8
    n equ ($-a)/2
    k equ 4

section .bss
    ris resb 1

section .text
    extern _proc
    global _start
_start:
    add ESP, 4
    push a
    push dword n
    push dword k
    push ris
    call _proc
    printb byte [ris]
    exit 0