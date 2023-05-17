import os
import sys

def getFileContent(path):
    path = path # TODO add exec dir
    with open(path, "r") as f:
        return f.read()

def parse(content):
    define, equations = content.split("#")
    definitions = getVariablesDictionary(define)
    
    results = {}
    for equation in equations.split("\n"):
        if len(equation.strip()) != 0:
            name, expression = equation.split("=")
            results[name.strip()] = evaluate_expression(expression, definitions)

    return results

def getVariablesDictionary(expressions):
    results = {}

    for expression in expressions.strip().split("\n"):
        name, value = expression.split("=")
        results[name.strip()] = int(value.strip())
    
    return results

def parallelo(r1, r2):
    return (r1 * r2) / (r1 + r2)

def serie(r1, r2):
    return r1 + r2

def evaluate_expression(expression, definitions):
    return evaluate_sub_expression(
        expression,
        definitions
    )

def evaluate_sub_expression(expression, definitions):
    print("Not yet implemented evaluation") # TODO
    sys.exit()

    expression = expression.strip()

    if len(expression) == 1:
        return definitions[expression]

    # ...
    

def main(path):
    content = getFileContent(path)
    results = parse(content)
    for result in results:
        print(f"{result}: {results[result]}")

if __name__ == "__main__":
    Testing = True
    if Testing:
        main("test.om")
    else:
        args = sys.argv
        if len(args) > 1:
            path = args[1]
            main(path)
        else:
            print("Not enough arguments.")