package graph;

import org.junit.Test;

public class BreadFirstSearchTest {
	
	static class MyWorker implements BreadFirstSearch.Worker {

		public void preWork(Graph g, int v) {
			mStringBuilder.append(v + " ");
		}

		@Override
		public String toString() {
			return mStringBuilder.toString();
		}
		
		private StringBuilder mStringBuilder = new StringBuilder();
	}
	
	@Test
	public void testCase1() {
		
		final int[][] TEST_DATA = new int[][] {
				{ 0, 1, 0, 1 },
				{ 0, 0, 1, 1 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 1, 0 }
		};
		
		
		Graph g = new AdjacencyMatrixGraph(TEST_DATA);
		MyWorker w = new MyWorker();
		BreadFirstSearch bfs = new BreadFirstSearch();
		bfs.run(g, 0, w);
		System.out.println(w);
	}

}
