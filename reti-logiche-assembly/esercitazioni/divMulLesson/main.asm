%include "../lib/utils.nasm"

section .data
	x dw 10,3,-7,12,-2
	y dw -2,5,6,4,15
	n equ ($-y)/2

section .bss
	q resw n ; q[i] = x[i] // y[i]
	r resw n ; r[i] = x[i] % y[i]

section .text
	global _start
_start:
	xor ESI, ESI
loop:
	cmp ESI, n
	jge end1
	
	mov AX, [x + ESI*2]
	mov BX, [y + ESI*2]
	
	cwd
	idiv BX
	mov [r + ESI*2], DX 
	mov [q + ESI*2], AX	
	
	inc ESI
	jmp loop
end1:
	xor ESI, ESI
print:
	cmp ESI, n
	jge end2
	printw word [x + ESI*2]
	printw word [y + ESI*2]
	printw word [q + ESI*2]
	printw word [r + ESI*2]

	inc ESI
	jmp print
end2:
	exit 0
