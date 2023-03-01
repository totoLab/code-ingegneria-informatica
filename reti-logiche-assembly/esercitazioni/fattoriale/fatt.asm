%include "../lib/utils.nasm"

section .data
    a equ 12
    ris equ 8

section .text
    global _fatt
_fatt:
    push EBP
    mov EBP, ESP
    pushad

    mov EBX, [EBP + a]

loop:
    cmp ESI, 1
    jge end

    mov EBX, ESI
    imul EAX, EBX ; TODO make it work

    dec ESI
    jmp loop
end:
    mov EAX, [EBP + ris]
    mov [EAX], EDX

    popad
    mov ESP, EBP
    pop EBP
    ret 8