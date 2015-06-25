package datamining;

import org.junit.Test;

import datamining.KMeansClustering.DataPoint;

public class KMeansClusteringTest {

	@Test
	public void test1() {
		
		final MyDataPoint[] DATASET = new MyDataPoint[] {
			new MyDataPoint("A1", 2, 10),
			new MyDataPoint("A2", 2, 5),
			new MyDataPoint("A3", 8, 4),
			new MyDataPoint("B1", 5, 8),
			new MyDataPoint("B2", 7, 5),
			new MyDataPoint("B3", 6, 4),
			new MyDataPoint("C1", 1, 2),
			new MyDataPoint("C2", 4, 9)
		};
		
		final int[] CENTROIDS = new int[] {0, 3, 6};
		
		KMeansClustering clustering = new KMeansClustering(System.out, 7);
		
		int[][] cluster = clustering.exec(DATASET, CENTROIDS);
		
		System.out.println("===========================================");
		for (int r = 0; r != cluster.length; ++r) {
			for (int c = 0; c != cluster[r].length; ++c) {
				System.out.print(DATASET[cluster[r][c]] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	static final class MyDataPoint extends KMeansClustering.DataPoint {

		public MyDataPoint(String id, int x, int y) {
			mId = id;
			mX = x;
			mY = y;
		}

		@Override
		public String getId() {
			return mId;
		}

		@Override
		public float distance(DataPoint dataPoint) {
			if (!(dataPoint instanceof MyDataPoint)) {
				throw new IllegalArgumentException("Type not match!");
			}
			
			if (this == dataPoint) {
				return 0;
			}

			MyDataPoint o = (MyDataPoint) dataPoint;
			final int dx = mX - o.mX;
			final int dy = mY - o.mY;

			return (float) Math.sqrt(dx * dx + dy * dy);
		}

		@Override
		public String toString() {
			return mId + "(" + mX + ", " + mY + ")";
		}

		private final String mId;
		private final int mX;
		private final int mY;
	}

}
