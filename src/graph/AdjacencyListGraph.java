package graph;

class AdjacencyListGraph extends Graph {
	
	public AdjacencyListGraph(int[][] list) {
		if (list == null) {
			throw new IllegalArgumentException("Matrix can't be null!");
		}
		
		mList = list;
	}

	@Override
	public int getVertexCount() {
		return mList.length;
	}

	@Override
	public boolean isEdge(int v, int w) {
		if (v < 0 || v >= mList.length || w < 0 || w >= mList.length) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		
		for (int i = 0; i != mList[v].length; ++i) {
			if (mList[v][i] == w) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int findEdgedVertex(int v, int w) {
		if (v < 0 || v >= mList.length || w < 0 || w >= mList.length) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		
		for (int i = 0; i != mList[v].length; ++i) {
			if (mList[v][i] >= w) {
				return mList[v][i];
			}
		}
		
		return -1;
	}
	
	final int[][] mList;

}
