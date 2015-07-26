package graph;

import org.junit.Test;

import utils.Utils;

public final class ShortestPathQuestionTest {
	
	@Test
	public void testCase1() {
		WeightedGraph g = new WeightedAdjacencyMatrixGraph(MATRIX1);
		
		SingleSourceShortestPathQuestion q = new SingleSourceShortestPathQuestion(g, 0);
		
		if (q.solve()) {
			Utils.printArray(System.out, q.result());
		}
	}
	
	private static final int[][] MATRIX1 = new int[][] {
		{ 0, 10, 3, 20, 0 },
		{ 0, 0, 0, 5, 0 },
		{ 0, 2, 0, 0, 15 },
		{ 0, 0, 0, 0, 11 },
		{ 0, 0, 0, 0, 0 }
	};

}
