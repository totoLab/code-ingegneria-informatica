import sys

class TokenLiterals:
    OPEN_PAREN: "("
    CLOSED_PAREN: ")"
    SERIES: "_"
    PARALLEL: "|"

    @classmethod
    def get_attr(cls):
        attr_dict = [value for value in vars(TokenLiterals).values() if type(value) == dict][0]
        return attr_dict

def divide_tokens(content, token_types):
    spaces = [" ", "\s", "\n"]
    content = content.strip()
    token = ""
    while len(content) > 0 and content[0] not in spaces:
        token += content[0]
        content = content[1:]
        if token in list(token_types.values()):
            return token, content
            """ for key in token_types:
                if token_types[key] == token:
                    return key, content
            else:
                RuntimeError("Found literal but can't match it") """

    return token, content

def tokenize(content):
    token_types = TokenLiterals.get_attr()

    token_list = []
    content = content.strip()
    while len(content) > 0:
        token, content = divide_tokens(content, token_types)
        token_list.append(token)
    return token_list
