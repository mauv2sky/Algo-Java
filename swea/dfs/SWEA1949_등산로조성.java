import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SWEA1949_등산로조성 {

	static int T, N, K, maxHigh, res;
	static boolean flag;
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maxHigh = res = Integer.MIN_VALUE;
			
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHigh = Math.max(maxHigh, map[i][j]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == maxHigh) {
						visit[i][j] = true;
						dfs(i, j, maxHigh, 1, true);
						visit[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
	
	static void dfs(int x, int y, int height, int length, boolean flag) {
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N || visit[tx][ty]) continue;
			if(height <= map[tx][ty]) {
				if(flag) {
					if(height > map[tx][ty] - K) { // 최대 K만큼 깎았을 때 현재 height보다 작아지면 이동 가능
						visit[tx][ty] = true;
						dfs(tx, ty, height-1, length+1, !flag); // height보다 작아지기만 하면 되니까 -1만 깎기
						visit[tx][ty] = false;
					}					
				}
			} else {
				visit[tx][ty] = true;
				dfs(tx, ty, map[tx][ty], length+1, flag);
				visit[tx][ty] = false;
			}
		}
		
		res = Math.max(res, length);
	}
	
}
