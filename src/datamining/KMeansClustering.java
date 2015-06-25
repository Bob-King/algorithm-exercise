package datamining;

import java.io.PrintStream;
import java.util.ArrayList;

public class KMeansClustering {

	public static abstract class DataPoint {

		public abstract String getId();

		public abstract float distance(DataPoint dataPoint);
	}

	public KMeansClustering(PrintStream out, int logLevel) {
		mLogOutput = out;
		mLogLevel = logLevel;
	}

	public int[][] exec(DataPoint[] dataset, int[] centroids) {
		if (dataset == null || dataset.length == 0) {
			throw new IllegalArgumentException(
					"Data set can't be null or empty!");
		}

		if (centroids == null || centroids.length <= 1) {
			throw new IllegalArgumentException(
					"Centroids can't be null or size of less than 1!");
		}

		if (centroids.length >= dataset.length) {
			throw new IllegalArgumentException(
					"Centroids can't be more than data set!");
		}

		boolean stop = false;
		int[] set = new int[dataset.length];
		float[] distances = new float[dataset.length];
		float distance;

		do {
			for (int i = 0; i != set.length; ++i) {
				set[i] = -1;
			}

			for (int i = 0; i != centroids.length; ++i) {
				set[centroids[i]] = i;
			}

			// Assign each data point to the closest centroid
			for (int i = 0; i != set.length; ++i) {
				if (set[i] != -1) {
					distances[i] = 0;
					continue;
				}
				distances[i] = dataset[i].distance(dataset[centroids[0]]);
				set[i] = 0;
				for (int j = 1; j != centroids.length; ++j) {
					distance = dataset[i].distance(dataset[centroids[j]]);
					if (distance < distances[i]) {
						distances[i] = distance;
						set[i] = j;
					}
				}
			}
			
			if (mLogLevel > 0) {
				mLogOutput.println("--- After assigning data points to centroids ---");
				printCluster(dataset, set, centroids);
			}

			// re-compute the centroids using the current cluster memberships
			int centroidsChanged = 0;
			for (int i = 0; i != centroids.length; ++i) {
				ArrayList<Integer> array = new ArrayList<Integer>();
				distance = 0;
				array.add(centroids[i]);
				for (int j = 0; j != set.length; ++j) {
					if (set[j] == i && j != centroids[i]) {
						array.add(j);
						distance += distances[j];
					}
				}

				if (array.size() <= 2) {
					continue;
				}

				float distance2;

				for (int j = 1; j != array.size(); ++j) {
					distance2 = 0;
					for (int k = 0; k != array.size(); ++k) {
						distance2 += dataset[array.get(k)]
								.distance(dataset[array.get(j)]);
					}
					if (distance2 < distance) {
						++centroidsChanged;
						centroids[i] = array.get(j);
						distance = distance2;
					}
				}
			}
			
			if (mLogLevel > 0) {
				mLogOutput.println("--- After re-computing centroids ---");
				printCluster(dataset, set, centroids);
			}

			// check if stop
			if (centroidsChanged == 0) {
				stop = true;
			}

		} while (!stop);

		int[][] result = new int[centroids.length][];
		for (int i = 0; i != result.length; ++i) {
			ArrayList<Integer> array = new ArrayList<Integer>();
			array.add(centroids[i]);
			for (int j = 0; j != set.length; ++j) {
				if (set[j] == i && j != centroids[i]) {
					array.add(j);
				}
			}
			result[i] = new int[array.size()];
			for (int j = 0; j != array.size(); ++j) {
				result[i][j] = array.get(j);
			}
		}

		return result;
	}
	
	private void printCluster(DataPoint[] dataset, int[] set, int[] centroids) {
		for (int i = 0; i != centroids.length; ++i) {
			mLogOutput.print(dataset[centroids[i]]);
			for (int j = 0; j != set.length; ++j) {
				if (set[j] == i && j != centroids[i]) {
					mLogOutput.print("\t" + dataset[j]);
				}
			}
			mLogOutput.println();
		}
	}

	final PrintStream mLogOutput;
	final int mLogLevel;
}
