package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadFirstSearch {
	
	public interface Worker {
		void preWork(Graph g, int v);
	}
	
	public void run(Graph graph, int start, Worker worker) {
		if (graph == null || worker == null) {
			throw new IllegalArgumentException("Graph or worker can't be null!");
		}
		
		if (graph.getVertexCount() <= 0) {
			throw new IllegalArgumentException("Invalid graph!");
		}
		
		final int N = graph.getVertexCount();
		
		if (start < 0 || start >= N) {
			throw new IllegalArgumentException("Invalid start point!");
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] marks = new boolean[N];
		int v;

		queue.add(start);
		marks[start] = true;
		while (!queue.isEmpty()) {
			start = queue.poll();
			worker.preWork(graph, start);
			try {
				v = 0;
				v = graph.findEdgedVertex(start, v);
				while (v != -1) {
					if (!marks[v]) {
						queue.add(v);
						marks[v] = true;
					}
					v = graph.findEdgedVertex(start, v + 1);
				}
			} catch (Exception e) {
			}
		}
	}

}
