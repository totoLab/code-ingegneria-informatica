section .data
    v equ 16
    n equ 12
    ris equ 8

section .text
    global _max
_max:
    push EBP
    mov EBP, ESP
    pushad
    
    mov EAX, [EBP + v]  ; access v address on stack
    mov ESI, [EBP + n]  ; access n address on stack
    xor EDI, EDI        ; init i
    xor DX, DX          ; init max
loop:
    cmp EDI, ESI    ; i >= n
    jge end 
    mov CX, [EAX + EDI * 2] ; current element
    cmp DX, CX              ; max >= current
    jge next
    mov DX, CX              ; max = current
next:
    inc EDI
    jmp loop
end:
    mov EAX, [EBP + ris]    ; get reserved memory addr of result
    mov [EAX], DX           ; result set

    popad
    mov ESP, EBP
    pop EBP
    ret 12                  ; 3 parameters * 4