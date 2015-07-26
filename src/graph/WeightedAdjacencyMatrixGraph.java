package graph;

final class WeightedAdjacencyMatrixGraph extends WeightedGraph {
	
	public WeightedAdjacencyMatrixGraph(int[][] matrix) {
		if (matrix == null) {
			throw new IllegalArgumentException("Matrix can't be null!");
		}

		mMatrix = matrix;
	}

	@Override
	public int distance(int v, int w) {
		return mMatrix[v][w];
	}

	@Override
	public int getVertexCount() {
		return mMatrix.length;
	}

	@Override
	public boolean isEdge(int v, int w) {
		return mMatrix[v][w] != 0;
	}

	@Override
	public int findEdgedVertex(int v, int w) {
		if (v < 0 || v >= mMatrix.length || w < 0 || w >= mMatrix.length) {
			throw new IllegalArgumentException("Invalid vertex (v = " + v
					+ ", w = " + w + ")!");
		}

		for (; w != mMatrix[v].length; ++w) {
			if (mMatrix[v][w] != 0) {
				return w;
			}
		}

		return -1;
	}
	
	private final int[][] mMatrix;

}
