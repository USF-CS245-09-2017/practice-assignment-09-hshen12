
public class GraphAdjMatrix implements Graph {

	int[][] list;
	int size;
	
	public GraphAdjMatrix(int size) {
		this.size = size;
		list = new int[size][size];
	}

	@Override
	public void addEdge(int v1, int v2, int weight) {
		list[v2][v1] = weight;
		list[v1][v2] = weight;

	}

	@Override
	public int getEdge(int v1, int v2) {
		return list[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		
		int[] cost = prim();
		int totalcost = 0;
		for(int i = 0; i < cost.length; i++) {
			totalcost += cost[i];
		}
		return totalcost;
	}
	
	private int[] prim() {
		int[] path = new int[size];
		int[] cost = new int[size];
		boolean[] known = new boolean[size];
		
		for(int i = 0; i < size; i++) {
			cost[i] = Integer.MAX_VALUE;
			known[i] = false;
		}
		
		cost[0] = 0;
		path[0] = -1;
		for(int i = 0; i < size-1; i++) {
			int u = minVertex(known, cost);
			known[u] = true;
			for(int v = 0; v < size; v++) {
				if(list[u][v] != 0 && known[v] == false && list[u][v] < cost[v]) {
					path[v] = u;
					cost[v] = list[u][v];
				}
			}
		}
		return cost;
	}

	private int minVertex(boolean[] known, int[] cost) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for(int v = 0; v < known.length; v++) {
			if(known[v] == false && cost[v] < min) {
				min = cost[v];
				min_index = v;
			}
		}
		return min_index;
	}
}
