package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_연구소 {

	static int N, M, max;
	static int[][] map;
	static int[][] copyMap;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = Integer.MIN_VALUE;
		
		dfs(0);
		
		System.out.println(max);
	}

	static void dfs(int cnt) {
		if(cnt == 3) { // 벽 3개 세우기 완료 -> 바이러스 퍼트리기
			bfs();
			return;
		}
		
		for (int i = 0; i < N; i++) { // 0인 곳에 1로 벽 세우기
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) continue;
				map[i][j] = 1;
				dfs(cnt + 1);
				map[i][j] = 0;
			}
		}
	}
	
	static void bfs() {
		// 기존 배열 복사 + 바이러스 위치 큐에 담기
		copyMap = new int[N][M];
		Queue<Node> qu = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
				if(map[i][j] == 2) qu.offer(new Node(i, j));
			}
		}
		
		// 바이러스 퍼트리기
		while(!qu.isEmpty()) {
			Node v = qu.poll();
			
			for (int d = 0; d < 4; d++) {
				int tx = v.x + dx[d];
				int ty = v.y + dy[d];
				
				if(tx < 0 || ty <0 || tx >= N || ty >= M || copyMap[tx][ty] != 0) continue;
				
				copyMap[tx][ty] = 2;  // 0인 곳을 2로 바이러스 확산
				qu.offer(new Node(tx, ty));
			}
		}
		
		// 안전영역 갯수 세기
		cntSafeArea();
		return;
	}
	
	static void cntSafeArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		
		max = Math.max(max, cnt);
	}
}
