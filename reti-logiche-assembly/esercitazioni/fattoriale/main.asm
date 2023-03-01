%include "../lib/utils.nasm"

section .data
    a equ 3

section .bss
    fatt resd 1

section .text
    extern _fatt
    global _start
_start:
    push a
    call _fatt
    printd dword [fatt]
    exit 0