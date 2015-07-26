package graph;

public abstract class Graph {
	
	public abstract int getVertexCount();
	
	public abstract boolean isEdge(int v, int w);
	
	/***
	 * find the next vertex that makes edge with specified vertex
	 * @param v - the specified vertex
	 * @param w - find edge starting from w
	 * @return the vertex makes edge with the specified vertex
	 */
	public abstract int findEdgedVertex(int v, int w);

}
