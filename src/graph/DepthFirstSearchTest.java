package graph;

import java.util.Stack;
import org.junit.Test;

public class DepthFirstSearchTest {

	static class MyWorker implements DepthFirstSearch.Worker {

		public void newRoot(Graph g, int v) {
		}

		public void preWork(Graph g, int v) {
			if (mVertex == null) {
				mVertex = new int[g.getVertexCount()];
			}

			mVertex[v] = mDFS++;
		}

		public void postWork(Graph g, int v, int w) {
			System.out.println("postWork(v = " + v + ", w = " + w + ")");
		}

		@Override
		public String toString() {
			if (mVertex.length > 0) {
				StringBuilder sb = new StringBuilder(mVertex.length << 2);
				sb.append(mVertex[0]);
				for (int i = 1; i != mVertex.length; ++i) {
					sb.append("-" + mVertex[i]);
				}

				return sb.toString();
			} else {
				return "";
			}
		}

		private int[] mVertex;
		private int mDFS = 0;
	}

	@Test
	public void testCase1() {
		final int[][] MATRIX = new int[][] { { 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 1, 0 },
				{ 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0 } };

		AdjacencyMatrixGraph mg = new AdjacencyMatrixGraph(MATRIX);
		DepthFirstSearch search = new DepthFirstSearch();
		MyWorker w = new MyWorker();
		search.run(mg, 0, w);

		System.out.println(w);
	}
	
	static class DetermineCycleWorker implements DepthFirstSearch.Worker {

		public void newRoot(Graph g, int v) {
			
		}

		public void preWork(Graph g, int v) {
			mStack.push(v);
		}

		public void postWork(Graph g, int v, int w) {
			if (v != mStack.peek()) {
				mStack.pop();
			}
			if (mStack.contains(w)) {
				System.out.print("There is a cycle: (");
				boolean print = false;
				for (int i = 0; i != mStack.size(); ++i) {
					if (!print) {
						if (w == mStack.elementAt(i)) {
							print = true;
						}
					}
					if (print) {
						System.out.print(mStack.elementAt(i) + ", ");
					}
				}
				if (print) {
					System.out.println(w + ").");
				}
				
			}
			
		}
		
		Stack<Integer> mStack = new Stack<Integer>();		
	}
	
	@Test
	public void testCase2() {

		
		final int[][] TEST_DATA = new int[][] {
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 1 },
				{ 0, 0, 0, 0 },
				{ 1, 0, 1, 0 }
		};		

		Graph mg = new AdjacencyMatrixGraph(TEST_DATA);
		DepthFirstSearch search = new DepthFirstSearch();
		DetermineCycleWorker w = new DetermineCycleWorker();
		search.run(mg, 0, w);
	}

}
