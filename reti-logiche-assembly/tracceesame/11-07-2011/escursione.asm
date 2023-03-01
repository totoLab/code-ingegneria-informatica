%include "../lib/utils.nasm"

section .data
    c equ 20
    n equ 16
    max equ 12
    i equ 8

section .text
    global _esc
_esc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + c] ; temps
    mov EDI, [EBP + n] ; n
    xor ESI, ESI       ; i
    xor DX, DX         ; max

loop:
    cmp ESI, EDI
    jge end

    mov CX, [EAX + ESI*2 + 2]
    sub CX, [EAX + ESI*2]

    cmp CX, DX
    jle next

    mov DX, CX
    mov EBX, ESI

next:
    add ESI, 2
    jmp loop

end:
    mov EAX, [EBP + max]
    mov [EAX], DX

    mov EAX, [EBP + i]
    mov [EAX], EBX

    popad
    mov ESP, EBP
    pop EBP
    ret 16