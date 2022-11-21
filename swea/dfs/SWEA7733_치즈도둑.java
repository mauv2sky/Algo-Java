import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA7733_치즈도둑 {

	static int T, N, maxDay, res;
	static boolean[][] map;
	static boolean[][] visit;
	static List<List<Node>> list;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node{
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new boolean[N][N];
			
			list = new ArrayList<>();
			for (int i = 0; i <= 100; i++) {
				list.add(new ArrayList<>());
			}
			
			maxDay = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int idx = Integer.parseInt(st.nextToken());
					list.get(idx).add(new Node(i, j));
					maxDay = Math.max(maxDay, idx);
				}
			} // 입력 끝
			
			res = Integer.MIN_VALUE;
			for (int i = 0; i <= maxDay; i++) {
				// 치즈 없애기
				int size = list.get(i).size();
				for (int j = 0; j < size; j++) {
					Node n = list.get(i).get(j);
					map[n.x][n.y] = true;
				}
				
				// 덩어리 갯수 세기
				int cnt = 0;
				visit = new boolean[N][N];
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if(map[r][c] || visit[r][c]) continue;
						visit[r][c] = true;
						dfs(r, c);
						cnt++;
					}
				}
				
				res = Math.max(res, cnt);
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int x, int y) {
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] || visit[tx][ty]) continue;
			
			visit[tx][ty] = true;
			dfs(tx, ty);
		}
	}
}
