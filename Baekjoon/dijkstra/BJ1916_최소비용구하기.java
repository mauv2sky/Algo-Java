import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1916_최소비용구하기 {

	static int N, M;
	static int INF = 1987654321;
	static List<List<Node>> adjList;
	static int[] cost;
	
	static class Node implements Comparable<Node> {
		int v, w;
		Node (int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList.get(u).add(new Node(v, w));
		}
		
		cost = new int[N+1];
		Arrays.fill(cost, INF);
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		System.out.println(cost[end]);
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1];
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
