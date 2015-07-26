package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SingleSourceShortestPathQuestion {
	
	public SingleSourceShortestPathQuestion(WeightedGraph graph, int vertex) {
		if (vertex < 0 || vertex > graph.getVertexCount()) {
			throw new IllegalArgumentException("Invalid vertex!");
		}
		
		mGraph = graph;
		mVertex = vertex;
	}
	
	public boolean solve() {
		
		final int N = mGraph.getVertexCount();
		
		mMarks = new int[N];
		mMarkCount = 0;
		mShortestPathes = new int[N];
		
		if (N <= 1) {
			return true;
		}
		
		for (int i = 0; i != N; ++i) {
			mShortestPathes[i] = Integer.MAX_VALUE;
			mMarks[i] = -1;
		}

		mQueue = new PriorityQueue<Integer>(N, new Comparator<Integer>() {

			public int compare(Integer arg0, Integer arg1) {
				return mShortestPathes[arg0] - mShortestPathes[arg1];
			}
			
		});
		
		mShortestPathes[mVertex] = 0;
		
		int v = mVertex;
		mMarks[v] = 0;
		++mMarkCount;
		
		while (v != -1) {
			try {
				int w;
				
				do {
					w = mGraph.findEdgedVertex(v, mMarks[v]);
					if (w == -1) {
						break;
					}
					
					/*
					if (mMarks[v] == N) {
						continue;
					}
					*/
					
					int sp = mShortestPathes[v] + mGraph.distance(v, w);
					if (sp < mShortestPathes[w]) {
						mShortestPathes[w] = sp;
						if (mQueue.contains(w)) {
							mQueue.remove(w);
						}
						mQueue.add(w);
					}
					
					mMarks[v] = w + 1;					
				} while (true);
			} catch (Exception e) {
			}
			
			mMarks[v] = N;
			
			v = -1;
			if (!mQueue.isEmpty()) {
				v = mQueue.remove();
				mMarks[v] = 0;
				++mMarkCount;
			}
		}
		
		return true;
	}
	
	public int[] result() {
		return mShortestPathes;
	}
	
	private final WeightedGraph mGraph;
	private final int mVertex;
	
	private int[] mMarks;
	@SuppressWarnings("unused")
	private int mMarkCount;
	private int[] mShortestPathes;
	
	PriorityQueue<Integer> mQueue;

}
