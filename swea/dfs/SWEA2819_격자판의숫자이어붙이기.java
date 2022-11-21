import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA2819_격자판의숫자이어붙이기 {

	static int T, N=4;
	static String[][] map;
	static HashSet<String> hashSet;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			map = new String[N][N];
			hashSet = new HashSet<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken();
				}
			} // 입력 끝
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 1, map[i][j]);
				}
			}
			
			System.out.println("#" + t + " " + hashSet.size());
		}
	}

	static void dfs(int x, int y, int cnt, String num) {
		
		if(cnt == 7) {
			hashSet.add(num);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N) continue;
			
			dfs(tx, ty, cnt + 1, num + map[tx][ty]);
		}
	}
}
