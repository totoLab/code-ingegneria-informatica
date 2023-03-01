section .data
	v equ 20
	n equ 16
	h equ 12
	w equ 8

section .text
	global _proc
_proc:
	push EBP
	mov EBP, ESP
	pushad
	
	mov EBX, [EBP + v]
	mov ECX, [EBP + w]
	mov EDI, [EBP + n]
	xor ESI, ESI
loop:
	cmp ESI, EDI
	jge end
	
	mov AX, [EBX + ESI*2]
	cwd
	div word [EBP + h] 
	
	movzx EDX, DX
	add [ECX + EDX*2], word 1	

	inc ESI
	jmp loop	
end:
	popad
	mov ESP, EBP
	pop EBP
	ret 16	
	
