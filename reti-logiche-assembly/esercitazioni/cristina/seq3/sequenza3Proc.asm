%include '../lib/utils.nasm'
section .data
	V EQU 14
	N EQU 10
	RIS EQU 8
section .text
	global proc
proc:
	PUSH EBP
	MOV EBP,ESP
	PUSHAD
	MOV EAX, [EBP + V]
	xor esi,esi
	MOV ESI, [EBP + N]
	XOR EDI,EDI
	printd EDI
	printd ESI
C:	CMP EDI,ESI
	JGE ESCI2
	printw word 0001
	MOV CX, [EAX + EDI*2]
	printw CX
	INC EDI
	MOV DX, [EAX + EDI*2]
	printw DX
	CMP CX,DX
	JNE AVANTI
	INC EDI
	MOV BX, [EAX + EDI*2]
	printw BX
	CMP DX,BX
	JNE AVANTI
	xor edx,edx
	LEA EDX, [EAX + EDI*2 - 4]
	JMP ESCIRET
AVANTI:
	CMP EDI,ESI
	JNE C
	JMP ESCI2
ESCI2:
	MOV ECX, DWORD -1
	JMP ESCIRET
ESCIRET:
	POPAD
	POP EBP
	RET 6