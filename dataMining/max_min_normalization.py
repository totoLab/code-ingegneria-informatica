def max_min_normalization(x, old_min, old_max, new_min, new_max):
    numerator = x - old_min
    denominator = old_max - old_min
    scaled = numerator / denominator
    transformed = scaled * (new_max - new_min) + new_min
    return transformed

def z_score_normalization(x, mean, std_dev):
    return (x - mean) / std_dev

x = 5
mm_scaled = max_min_normalization(
    x,
    0, 10,
    0, 1
)

mean = 3
std_dev = 1
z_score = z_score_normalization(x, mean, std_dev)

print(f"{x = } -> {mm_scaled = }, {z_score = }")
