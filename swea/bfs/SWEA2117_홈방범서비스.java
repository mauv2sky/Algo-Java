import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// '서비스영역 크기 K = bfs의 깊이'고, K 크기마다 운영비용은 정해져 있음
// 손해없이 최대 영역으로 운영할 수 있는 위치는 모든 지점에 설치해봐야 알 수 있음
// => 모든 영역에 대해 K의 크기를 1씩 늘려가는 동시에 이익이 난다면 집수 최댓값 갱신

public class SWEA2117_홈방범서비스 {

	static int T, N, M, K, res;
	
	static int[][] map;
	static boolean[][] visit;
	static Queue<Node> qu;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node {
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
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
	
	static void bfs(int x, int y) {
		qu = new ArrayDeque<>();
		visit = new boolean[N][N];
		
		qu.offer(new Node(x, y));
		visit[x][y] = true;
		
		K = 1;
		int house = map[x][y] == 1 ? 1 : 0;
		
		if(getOperCost() <= house * M)
			res = Math.max(res, K);
		
		while(!qu.isEmpty()) {
			K++;
			int size = qu.size();
			
			for (int i = 0; i < size; i++) {
				Node n = qu.poll();
				
				for (int d = 0; d < 4; d++) {
					int tx = n.x + dx[d];
					int ty = n.y + dy[d];
					
					if(tx < 0 || ty < 0 || tx >= N || ty >= N || visit[tx][ty]) continue;
					
					if(map[tx][ty] == 1) house++;
					
					visit[tx][ty] = true;
					qu.offer(new Node(tx, ty));
				}
			}
			
			if(getOperCost() <= house * M)
				res = Math.max(res, house);
		}
	}
	
	static int getOperCost() {
		return K * K + (K-1) * (K-1);
	}
}
