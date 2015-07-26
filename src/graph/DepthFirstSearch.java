package graph;

import java.util.Stack;

public class DepthFirstSearch {

	public interface Worker {
		void newRoot(Graph g, int v);

		void preWork(Graph g, int v);

		void postWork(Graph g, int v, int w);
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

		int marked = 0;
		Stack<Integer> stack = new Stack<Integer>();

		int[] marks = new int[N];
		for (int i = 0; i != marks.length; ++i) {
			marks[i] = -1;
		}

		stack.push(start);
		worker.newRoot(graph, start);

		while (marked != N) {
			if (stack.empty()) {
				for (int i = 0; i != marks.length; ++i) {
					if (marks[i] >= 0) {
						continue;
					}

					stack.push(i);
					worker.newRoot(graph, i);
					break;
				}
			} else {
				int v = stack.peek();
				if (marks[v] == -1) {
					marks[v] = 0;
					++marked;
					worker.preWork(graph, v);
				}

				int w = 0;

				try {
					w = graph.findEdgedVertex(v, marks[v]);
					while (w >= 0) {
						if (marks[w] == -1) {
							stack.push(w);
							break;
						}

						worker.postWork(graph, v, w);

						marks[v] = w + 1;
						w = graph.findEdgedVertex(v, marks[v]);
					}
				} catch (Exception e) {
					w = -1;
				}

				if (w >= 0) {
					continue;
				}

				marks[v] = N;
				stack.pop();
			}
		}
	}

}
