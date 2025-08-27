import numpy as np
import matplotlib.pyplot as plt

# Generate input values
x = np.linspace(-10, 10, 100)

# Fixed weight
w = 1  

# Try different biases
bias_values = [-5, 0, 5]

# Create the plot
plt.figure(figsize=(8, 5))
for b in bias_values:
    y = w * x + b
    plt.plot(x, y, label=f"b = {b}")

# Add labels, legend, and grid
plt.title("Effect of Bias on a Neuron (y = wx + b)")
plt.xlabel("Input x")
plt.ylabel("Output y")
plt.axhline(0, color="black", linewidth=0.5)
plt.axvline(0, color="black", linewidth=0.5)
plt.grid(True)
plt.legend()

# Save to file
plt.savefig("bias_plot.png")
print("✅ Plot saved as bias_plot.png")
