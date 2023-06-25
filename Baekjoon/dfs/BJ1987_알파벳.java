package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1987_알파벳 {

	static int R, C, max;
	static char[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[] visit = new boolean[26];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		max = Integer.MIN_VALUE;
		visit[map[0][0]-'A'] = true;
		
		dfs(0, 0, 1);

		System.out.println(max);
	}

	static void dfs(int x, int y, int dist) {
		
		for(int d=0; d<4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= R || ty >= C || visit[map[tx][ty]-'A']) continue;
			visit[map[tx][ty]-'A'] = true;
			dfs(tx, ty, dist+1);
			visit[map[tx][ty]-'A'] = false;
		}
		max = Math.max(max, dist);
	}
	
}
