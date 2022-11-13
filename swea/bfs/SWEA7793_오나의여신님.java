import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7793_오나의여신님 {

	static int T, N, M, res;
	static char[][] map;
	static int[] jieun;		// 지은의 좌표
	static Queue<int[]> suyeon;	// 수연의 초기좌표
	static Queue<int[]> devil;	// 악마의 좌표들을 담을 큐
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			devil = new ArrayDeque<>();
			suyeon = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*') devil.offer(new int[] {i, j});
					else if(map[i][j] == 'S') suyeon.offer(new int[] {i, j});
					else if(map[i][j] == 'D') jieun = new int[] {i, j};
					
				}
			}
			
			res = 0;
			boolean save = true;
			while(true) {
				if(map[jieun[0]][jieun[1]] == 'S') break;

				// 악마의 손아귀 확산
				devilSpread();
				
				// 수연의 이동
				if(!moveSuyeon()) {
					save = false;
					break;
				}
				
				res++;
			}
			
			if(save) {
				System.out.println("#" + t + " " + res);				
			} else {
				System.out.println("#" + t + " GAME OVER");
			}
		}
	}

	static boolean moveSuyeon() {
		int size = suyeon.size(); // 1초동안 이동할 수 있는 거리들
		boolean moved = false;
		
		while(size-- > 0) {
			int[] su = suyeon.poll();
			
			for (int d = 0; d < 4; d++) {
				int tx = su[0] + dx[d];
				int ty = su[1] + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
				if(map[tx][ty] == '.' || map[tx][ty] == 'D') { // 빈칸이거나 지은이가 있는 곳으로만 이동가능
					map[tx][ty] = 'S';
					suyeon.offer(new int[] {tx, ty});
					moved = true;
				}
			}
		}
		
		return moved;
	}
	
	static void devilSpread() {
		int size = devil.size();
		
		while(size-- > 0) {
			int[] dv = devil.poll();
			
			for (int d = 0; d < 4; d++) {
				int tx = dv[0] + dx[d];
				int ty = dv[1] + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
				if(map[tx][ty] == '.' || map[tx][ty] == 'S') { // 빈칸이거나 수연이가 있는 곳으로만 이동가능
					map[tx][ty] = '*';
					devil.offer(new int[] {tx, ty});
				}
			}
			
		}
	}
	
}
