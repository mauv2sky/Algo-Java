import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BJ1941_소문난칠공주 {

	static int N=5, res;
	static char[][] map;
	static boolean[][] princess;
	static boolean[][] visit;
	static int[] tgt;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		res = 0;
		map = new char[N][];
		tgt = new int[7];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		} // 입력 끝
		
		comb(0, 0); // 25C7의 조합 구하기
		
		System.out.println(res);
	}

	static void comb(int src, int idx) {
		if(idx == 7) {
//			System.out.println(Arrays.toString(tgt));
			makePrincess(); // 7공주 좌표 찍기
			return;
		}
		
		for (int i = src; i < 25; i++) {
			tgt[idx] = i;
			comb(i + 1, idx + 1);
		}
	}
	
	static void makePrincess() {
		princess = new boolean[N][N];
		int x = 0, y = 0;
		
		for (int i = 0; i < 7; i++) {
			x = tgt[i] / 5;
			y = tgt[i] % 5;
			
			princess[x][y] = true;
		}
		
		check(x, y); // 7공주가 모두 인접해 있는지 확인
	}
	
	static void check(int x, int y) {
		Queue<int[]> qu = new ArrayDeque<>();
		visit = new boolean[N][N];
		qu.offer(new int[] {x, y});
		visit[x][y] = true;
		
		int total = 1; // 총 인원
		int cnt = 0;   // 이다솜파 인원
		
		while(!qu.isEmpty()) {
			int[] n = qu.poll();
			
			if(map[n[0]][n[1]] == 'S') cnt++;

			for (int d = 0; d < 4; d++) {
				int tx = n[0] + dx[d];
				int ty = n[1] + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || visit[tx][ty]) continue;
				if(princess[tx][ty] == true) {
					qu.offer(new int[] {tx, ty});
					total++;
				}
				visit[tx][ty] = true;
			}
		}
		
		if(total == 7 && cnt >= 4) res++;
	}
}
