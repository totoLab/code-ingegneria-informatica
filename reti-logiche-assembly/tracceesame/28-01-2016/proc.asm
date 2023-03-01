section .data
    a equ 20
    n equ 16
    k equ 12
    ris equ 8

section .text
    global _proc
_proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + a]  ; v
    mov EDI, [EBP + n]  ; n
    sub EDI, 3          ; n - 3 
    xor ESI, ESI        ; i
    xor DL, DL
    xor EBX, EBX

loop:
    cmp ESI, EDI
    jge end

    xor ECX, ECX

    mov EBX, [EAX + ESI*2]
    cmp EBX, 0
    jl next
    add ECX, EBX

    mov EBX, [EAX + ESI*2 + 2]
    cmp EBX, 0
    jl next
    add ECX, EBX

    mov EBX, [EAX + ESI*2 + 4]
    cmp EBX, 0
    jl next
    add ECX, EBX

    cmp ECX, [EBP + k]  ; k
    jg ok

next:
    inc ESI
    jmp loop

ok:
    mov DL, 1

end:
    mov EAX, [EBP + ris]
    mov [EAX], DL

    popad
    mov ESP, EBP
    pop EBP
    ret 16