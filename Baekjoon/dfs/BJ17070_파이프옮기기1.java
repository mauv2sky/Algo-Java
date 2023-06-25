package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070_파이프옮기기1 {
	
	static int N, cnt;
	static int[][] map;
	static int[] dx = {1, 0, 1}; // 대각, 우, 하
	static int[] dy = {1, 1, 0};
	static int[][] move = { {0, 1, 2}, {0, 1}, {0, 2} }; // 0: 대각, 1: 우, 2: 하
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1, 2, 1); // (1,2) 좌표, 오른쪽방향
		
		System.out.println(cnt);
	}
	
	static void dfs(int x, int y, int d) {
		if(x == N && y == N) {
			cnt++;
			return;
		}
		
		
		int[] dir = move[d]; // d일 때 이동할 수 있는 방향
		for (int i = 0; i < dir.length; i++) {
			int tx = x + dx[dir[i]];
			int ty = y + dy[dir[i]];
			
			if(tx > N || ty > N || map[tx][ty] == 1) continue;
			if(dir[i] == 0 && (map[tx][ty-1] == 1 || map[tx-1][ty] == 1)) continue;
			
//			System.out.println("x, y : " + x + " " + y + " " + d );
//			System.out.println("tx, ty : " + tx + " " + ty );
			
			dfs(tx, ty, dir[i]);
		}
	}
}