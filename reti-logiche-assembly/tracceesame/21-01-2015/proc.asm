section .data
    w equ 20
    n equ 16
    v equ 12
    ris equ 8

section .bss
    ok resb 1

section .text
    extern _proc2
    global _proc
_proc:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + w]      ; w
    mov EBX, [EBP + v]      ; v
    mov EDI, [EBP + n]      ; n
    xor ESI, ESI            ; i
    xor DX, DX              ; result


loop:
    cmp ESI, EDI
    jge end

    mov ECX, [EAX + ESI*2]  ; v[i]
    ; TODO trovare altri registri
    
    inc DX

next:
    jmp loop

end:
    mov EAX, [EBP + ris]    ; get addr of result
    mov [EAX], DX           ; write result to memory

    popad
    mov ESP, EBP
    pop EBP