import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

# Step 1: Generate 1000 random 2D data points in range 0-200
data = np.random.randint(0, 200, (1000, 2))

# Step 2: Apply KMeans++ clustering (4 clusters)
kmeans = KMeans(n_clusters=4, init="k-means++", random_state=42)
kmeans.fit(data)

# Step 3: Get cluster centers and labels
centers = kmeans.cluster_centers_
labels = kmeans.labels_

# Print cluster centers
print("Cluster Centers:\n", centers)

# Step 4: Plot the clusters
plt.figure(figsize=(8, 6))
plt.scatter(data[:, 0], data[:, 1], c=labels, cmap='viridis', s=20)
plt.scatter(centers[:, 0], centers[:, 1], c='black', marker='X', s=200, label="Centroids")

plt.title("K-Means++ Clustering (4 Clusters)")
plt.xlabel("X-axis")
plt.ylabel("Y-axis")
plt.legend()
plt.show()
