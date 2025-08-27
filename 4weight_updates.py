import numpy as np
import matplotlib.pyplot as plt

# Generate input values
x = np.linspace(-10, 10, 100)

# Fixed bias
b = 0

# Try different weights (slopes)
weight_values = [-2, -1, 0, 1, 2]

# Create the plot
plt.figure(figsize=(8, 5))
for w in weight_values:
    y = w * x + b
    plt.plot(x, y, label=f"w = {w}")

# Add labels, legend, and grid
plt.title("Effect of Weight on a Neuron (y = wx + b)")
plt.xlabel("Input x")
plt.ylabel("Output y")
plt.axhline(0, color="black", linewidth=0.5)
plt.axvline(0, color="black", linewidth=0.5)
plt.grid(True)
plt.legend()

# Save to file
plt.savefig("weight_tilt_plot.png")
print("✅ Plot saved as weight_tilt_plot.png")
