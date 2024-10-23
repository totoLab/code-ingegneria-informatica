import tokenization

def evaluate(tokens):
    stack = []
    i = 0

    while i < len(tokens):
        token = tokens[i]

        if token == '(':
            sub_tokens, i = extract_subexpression(tokens, i + 1)
            stack.append(evaluate(sub_tokens))
        elif token == '|':
            stack.append(or_operator(stack.pop(), evaluate(tokens[i + 1:])))
            break
        elif token == '_':
            if len(stack) == 0:
                stack.append(True)
            stack.append(underscore_operator(stack.pop(), evaluate(tokens[i + 1:])))
            break
        else:
            stack.append(float(token))

        i += 1

    return stack[0]

def extract_subexpression(tokens, start):
    count = 1
    sub_tokens = []

    while count > 0:
        token = tokens[start]
        sub_tokens.append(token)

        if token == '(':
            count += 1
        elif token == ')':
            count -= 1

        start += 1

    return sub_tokens[:-1], start

def or_operator(left, right):
    print(left, "|", right)
    return (left * right) / (left + right)

def underscore_operator(left, right):
    print(left, "_", right)
    return left + right

req = "((( 1 _ 2 ) | 4 ) _ 1 _ 3 ) | 1 | 4"
tokens = tokenization.tokenize(req)
print(tokens)
result = evaluate(tokens)
print(result)
print("WROOOOOOOOONG")