import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# Generate sample data
np.random.seed(42)
mean_values = np.linspace(40, 60, 100)
std_dev_fixed = 10
data_continuous = [np.random.normal(mean, std_dev_fixed, 100) for mean in mean_values]

# Calculate statistical measures for continuous data
def calculate_measures(data):
    range_data = [np.ptp(d) for d in data]
    variance_data = [np.var(d) for d in data]
    std_dev_data = [np.std(d) for d in data]
    absolute_avg_dev_data = [np.mean(np.abs(d - np.mean(d))) for d in data]
    median_absolute_dev_data = [np.median(np.abs(d - np.median(d))) for d in data]
    iqr_data = [np.percentile(d, 75) - np.percentile(d, 25) for d in data]
    return range_data, variance_data, std_dev_data, absolute_avg_dev_data, median_absolute_dev_data, iqr_data

measures = calculate_measures(data_continuous)

# Plotting all measures and data distribution in a single plot
fig, ax1 = plt.subplots(figsize=(12, 8))

# Plot statistical measures
ax1.plot(mean_values, measures[0], label='Range', color='blue')
ax1.plot(mean_values, measures[1], label='Variance', color='orange')
ax1.plot(mean_values, measures[2], label='Standard Deviation', color='green')
ax1.plot(mean_values, measures[3], label='Absolute Average Deviation', color='red')
ax1.plot(mean_values, measures[4], label='Median Absolute Deviation', color='purple')
ax1.plot(mean_values, measures[5], label='Interquartile Range', color='brown')

ax1.set_xlabel('Mean Value')
ax1.set_ylabel('Value (Statistical Measures)')
ax1.set_title('Statistical Measures and Data Distribution')
ax1.legend(loc='upper left')

# Overlay data distribution using KDE plot
ax2 = ax1.twinx()
sns.kdeplot(data_continuous[0], ax=ax2, color='gray', label='Data Distribution')

ax2.set_ylabel('Density (Data Distribution)')
ax2.legend(loc='upper right')

plt.tight_layout()
plt.show()
