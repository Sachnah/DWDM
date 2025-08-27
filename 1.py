import time
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

# Step 1: Generate 10,000 random 2D points (range 0-200)
data = np.random.rand(10000, 2) * 200

# Step 2: Apply KMeans clustering (5 clusters, like your original code)
kmeans = KMeans(n_clusters=4, init="random", random_state=42)

# Measure time for clustering
t0 = time.process_time()
kmeans.fit(data)
t1 = time.process_time()

# Calculate total time
print("Total Time:", t1 - t0)

# Step 3: Get cluster centers and labels
centers = kmeans.cluster_centers_
labels = kmeans.labels_

# Print cluster centers
print("Cluster Centers:\n", centers)

# Step 4: Plot clusters using color map
plt.figure(figsize=(8, 6))
plt.scatter(data[:, 0], data[:, 1], c=labels, cmap='viridis', s=10)
plt.scatter(centers[:, 0], centers[:, 1], c='red', marker='X', s=200, label="Centroids")

# Add title and labels
plt.title("K-Means Clustering (4 Clusters)")
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.legend()
plt.show()
