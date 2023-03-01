section .data
    a equ 16
    n equ 12
    ks equ 8

section .text
    global _salto
_salto:
    push EBP
    mov EBP, ESP
    pushad

    mov EAX, [EBP + a]  ; v
    mov EDI, [EBP + n]  ; n, i decreasing
    sub EDI, 2
    xor DX, DX          ; cont
    
    mov EBX, [EAX + EDI*2]  ; v[n - 1]
    cmp EBX, 0              ; positive?
    jge next                ; true: impossible to check -> skip
    jmp negative            ; false -> check v[n-1] 

loop:
    cmp EDI, 0
    jl end
    
    mov EBX, [EAX + EDI*2]  ; v[i]
    cmp EBX, 0              ; positive?
    jge positive
negative:
    add EDI, 1

    mov ECX, [EAX + EDI*2]  ; v[i+1]
    cmp ECX, 0              ; positive?
    jl next
    
    ; a < 0, b > 0
    ; | a - b | > 2
    sub EBX, ECX
    cmp EBX, 0              ; v[i] - v[i+1] positive?
    jge g2
    neg EBX                 ; | ... |
g2:
    cmp EBX, 2              ; | ... | > 2
    jle restorePosG2
    inc DX

restorePosG2:
    sub EDI, 1
    jmp next
positive:
    add EDI, 2

    mov ECX, [EAX + EDI*2]
    cmp ECX, 0              ; negative?
    jge next
    
    ; a > 0, b < 0
    ; | a - b | > 4
    sub EBX, ECX
    cmp EBX, 0              ; v[i] - v[i+1] positive?
    jge g4
    neg EBX                 ; | ... |
g4:
    cmp EBX, 4              ; | ... | > 4
    jle restorePosG4
    inc DX
    
restorePosG4:
    sub EDI, 2
next:
    dec EDI
    jmp loop
end:
    mov EAX, [EBP + ks]
    mov [EAX], DX

    popad
    mov ESP, EBP
    pop EBP
    ret 12