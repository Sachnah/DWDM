import numpy as np
import pandas as pd

def standard_scaler_array(data_array):
    # Convert to NumPy array if it's not already
    data_array = np.array(data_array)

    # Compute mean and standard deviation
    mean = np.mean(data_array)
    std = np.std(data_array)

    # Apply standard scaling
    if std == 0:
        return np.zeros_like(data_array)  # Avoid division by zero
    scaled = (data_array - mean) / std

    return scaled

# Example usage
data = np.array([1200, 180, 360, 30, 5])  # NumPy array
scaled = standard_scaler_array(data)

print("Original Data:", data)
print("Standard Scaled Data:", scaled)
