import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

# Step 1: Define the given data points
data = np.array([
    [2, 10], [2, 5], [8, 4], [5, 8],
    [7, 5], [6, 4], [1, 2], [4, 9]
])

# Step 2: Apply KMeans clustering (Choose 3 clusters for this example)
kmeans = KMeans(n_clusters=3, init="random", random_state=42)
kmeans.fit(data)

# Step 3: Get cluster centers and labels
centers = kmeans.cluster_centers_
labels = kmeans.labels_

# Print results
print("Cluster Centers:\n", centers)
print("Cluster Labels:", labels)

# Step 4: Plot the clusters
plt.figure(figsize=(6, 5))
plt.scatter(data[:, 0], data[:, 1], c=labels, cmap='viridis', s=100, edgecolor='k')
plt.scatter(centers[:, 0], centers[:, 1], c='black', marker='X', s=200, label="Centroids")

# Add labels to points for clarity
for i, point in enumerate(data):
    plt.text(point[0]+0.1, point[1]+0.1, f"P{i+1}", fontsize=9)

# Add title and axis labels
plt.title("K-Means Clustering on Given Data Points")
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.legend()
plt.grid(True)
plt.show()
