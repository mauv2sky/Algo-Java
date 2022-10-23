import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753_최단경로 {
	
	static int V, E, K;
	static final int INF = 987654321;
	static List<List<Node>> adjList;
	static int[] cost;
	
	static class Node implements Comparable<Node>{
		int v, w;
		Node(int no, int w) {
			this.v = no;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		cost = new int[V+1];
		Arrays.fill(cost, INF);
		
		adjList = new ArrayList<>();
		for (int i = 0; i < V+1; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList.get(u).add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for (int i = 1; i <= V; i++) {
			if(cost[i] >= INF) System.out.println("INF");
			else System.out.println(cost[i]);
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[V+1];
		pq.offer(new Node(start, 0));
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			if(visit[n.v]) continue;
			visit[n.v] = true;
			
			for (Node node : adjList.get(n.v)) {
				if(cost[node.v] > cost[n.v] + node.w) {
					cost[node.v] = cost[n.v] + node.w;
					pq.offer(new Node(node.v, cost[node.v]));
				}
			}
		}
	}
	
}
