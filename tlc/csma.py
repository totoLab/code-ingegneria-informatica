# %%
import matplotlib.pyplot as plt
import numpy as np
import math
from scipy.stats import binom


# %%


def slotted_aloha(arrival_times: np.ndarray):
    count_arrivals = np.zeros(math.ceil(arrival_times[-1]) + 1)
    for val in arrival_times:
        count_arrivals[math.ceil(val)] += 1
    return np.sum(1 * (count_arrivals == 1))


def pure_aloha(inter_arrival_time: np.ndarray):
    success = 0
    for j in range(len(inter_arrival_time)):
        if j - 1 >= 0 and inter_arrival_time[j - 1] >= 1 and j + 1 < N and inter_arrival_time[j + 1] >= 1:
            success += 1
    return success


def csma_generalized(arrival_times: np.ndarray, p, cd: bool = False):
    beta = .1
    success = 0
    transmission_ends = 0
    collision_detect_possible = 0
    successfully_sending = False
    pending_packets = 0
    for j, time in enumerate(arrival_times):
        if time < collision_detect_possible:
            # channel is busy, but detected as free due to beta delay in detection of ongoing transmission
            if cd:
                transmission_ends = min(transmission_ends, time + beta)
            else:
                transmission_ends = time + 1
            if successfully_sending:
                success -= 1
                successfully_sending = False
        elif time < transmission_ends:
            # channel is busy, do nothing
            pending_packets += 1
            if (j + 1 < len(arrival_times) and arrival_times[j + 1] >= transmission_ends) \
                    or j + 1 == len(arrival_times):
                if p == 1:
                    x = pending_packets
                elif p == 0:
                    x = 0
                else:
                    x = binom(pending_packets, p).rvs()
                if x == 1:
                    success += 1
                    successfully_sending = True
                    collision_detect_possible = transmission_ends + beta
                    transmission_ends = transmission_ends + 1
                elif x > 1:
                    collision_detect_possible = transmission_ends + beta
                    transmission_ends = transmission_ends + 1
                pending_packets = 0
        else:
            # channel is free
            successfully_sending = True
            success += 1
            collision_detect_possible = time + beta
            transmission_ends = time + 1
    return success


# %%
N = int(1e4)
numG = 20
rng = np.random.default_rng()
throughput_pure = np.zeros(numG)
throughput_slotted = np.zeros(numG)
throughput_csma_non_persistent = np.zeros(numG)
throughput_1persistent = np.zeros(numG)
throughput_pt1persistent = np.zeros(numG)
throughput_pt01persistent = np.zeros(numG)
throughput_pt5persistent = np.zeros(numG)
throughput_csma_cd = np.zeros(numG)
G = np.logspace(-1.5, .8, numG)
# %%
for i, lam in enumerate(G):
    inter_arrival_time = rng.exponential(1 / lam, N)
    arrival_times = np.array(inter_arrival_time)
    for j in range(1, len(arrival_times)):
        arrival_times[j] += arrival_times[j - 1]
    throughput_pure[i] = pure_aloha(inter_arrival_time) / math.ceil(arrival_times[-1])
    throughput_slotted[i] = slotted_aloha(arrival_times) / math.ceil(arrival_times[-1])
    throughput_csma_non_persistent[i] = csma_generalized(arrival_times, 0) / math.ceil(arrival_times[-1])
    throughput_1persistent[i] = csma_generalized(arrival_times, 1) / math.ceil(arrival_times[-1])
    throughput_pt1persistent[i] = csma_generalized(arrival_times, .1) / math.ceil(arrival_times[-1])
    throughput_pt01persistent[i] = csma_generalized(arrival_times, .01) / math.ceil(arrival_times[-1])
    throughput_pt5persistent[i] = csma_generalized(arrival_times, .5) / math.ceil(arrival_times[-1])
    throughput_csma_cd[i] = csma_generalized(arrival_times, 1, True) / math.ceil(arrival_times[-1])
# %%
fig, ax = plt.subplots()
ax.plot(G, throughput_pure, marker='.', label='Pure Aloha')
ax.plot(G, throughput_slotted, marker='.', label='Slotted Aloha')
ax.plot(G, throughput_csma_non_persistent, marker='.', label="CSMA non-persistent")
ax.plot(G, throughput_1persistent, marker='.', label="CSMA 1-persistent")
ax.plot(G, throughput_pt1persistent, marker='.', label="CSMA .1-persistent")
ax.plot(G, throughput_pt5persistent, marker='.', label="CSMA .5-persistent")
ax.plot(G, throughput_pt01persistent, marker='.', label="CSMA .01-persistent")
ax.plot(G, throughput_csma_cd, marker='.', label="CSMA CD")
ax.set_xlabel('G (attempts rate)')
ax.set_ylabel('S (throughput per frame time)')
ax.legend()
fig.savefig("plotV3.jpeg")
fig.show()
