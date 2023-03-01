section .data
    v equ 20
    n equ 16
    cont equ 12
    k equ 8

section .text
    global _proc
_proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + v]
    mov EDI, [EBP + n]
    sub EDI, 1
    mov EBX, 2
    shl EBX, [EBP + k]
    xor ESI, ESI
    xor DX, DX

    mov ECX, [EAX]                  ; v[0]
    add ECX, [EAX + EDI*2]          ; v[n-1]

    and ECX, EBX
    cmp ECX, 0
    jg next

loop:
    cmp ESI, EDI
    jge end

    mov ECX, [EAX + ESI*2]          ; v[i]
    add ECX, [EAX + ESI*2 + 1]      ; v[i+1]
    and ECX, EBX                    ; multiplo di 2^k
    cmp ECX, 0
    jle next
    inc DX
next:
    inc ESI
    jmp loop
end:
    mov EAX, [EBP + cont]
    mov [EAX], DX

    popad
    mov ESP, EBP
    pop EBP
    ret 12