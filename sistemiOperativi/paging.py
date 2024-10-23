import math

def get_padding(size):
    return len(f"{2 ** size}")

def dec_2_bin(num, size):
    return "{:0{}b}".format(num, size)

def bin_2_dec(num):
    return int(num, 2)

def logic_2_physical(addr, logic_m, physical_m, n, page_table):
    logical_page_idx = logic_m - n

    bin_addr = dec_2_bin(addr, logic_m)
    
    page_number, offset = bin_addr[:logical_page_idx], bin_addr[logical_page_idx:]

    physical_page_idx = bin_2_dec(page_number)
    segment = page_table[physical_page_idx]
    segment_addr = dec_2_bin(segment, physical_m)

    print(f"{addr:>{get_padding(logic_m)}}: {page_number}|{offset} -> {segment_addr}|{offset}")

def parameters(memory_size, page_size, program_max_allocation):
    def log2(num) -> int:
        return int(math.log(num, 2))

    logic_m = log2(program_max_allocation)
    physical_m = log2(memory_size)
    n = log2(page_size)
    return logic_m, physical_m, n

def main():
    table = [12, 1, 17, 62, 11, 16, 61, 12]
    addrs = [0, 2, 4, 9, 19, 11, 22, 30, 27, 23, 14]
    logic_m, physical_m, n = parameters(256, 4, 64)
    for addr in sorted(addrs):
        logic_2_physical(addr, logic_m, physical_m, n, table)

if __name__ == "__main__":
    main()