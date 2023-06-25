package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class SWEA1249_보급로 {

	static int T, N, min;
	static int[][] map;
	static boolean[][] visit;
	static int[][] cost;
	static int[] dx = {1, 0, -1, 0}; // 하우상좌
	static int[] dy = {0, 1, 0, -1};
	
	static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String[] str = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			
			min = Integer.MAX_VALUE;
			
			bfs();
			
			System.out.println("#" + t + " " + min);
		}
	}
	
	static void bfs() {
		Queue<Node> qu = new ArrayDeque<>();
		qu.offer(new Node(0, 0));
		visit[0][0] = true;
		cost[0][0] = 0;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			if(n.x == N-1 && n.y == N-1)
				min = Math.min(min, cost[N-1][N-1]);
			
			if(min <= cost[n.x][n.y]) continue;
			
			for (int i = 0; i < 4; i++) {
				int tx = n.x + dx[i];
				int ty = n.y + dy[i];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N ) continue;
				// 아직 방문하지 않은 노드라면, 현재 비용을 무조건 적용해 줘야 하므로 or처리
				if(!visit[tx][ty] || cost[tx][ty] > cost[n.x][n.y] + map[tx][ty]) {
					cost[tx][ty] = cost[n.x][n.y] + map[tx][ty];
					visit[tx][ty] = true;
					qu.offer(new Node(tx, ty));
				}
			}
		}
	}
}
