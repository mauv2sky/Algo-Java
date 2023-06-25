import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ2239_스도쿠 {
	
	static int N=9, S;
	static int[][] map;
	static List<Node> list;
	
	static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = ch[j] - '0';
				if(map[i][j] == 0) list.add(new Node(i, j));
			}
		}
		
		S = list.size();
		dfs(0);
	}
	
	static void dfs(int idx) {
		// 스도쿠가 만들어지면 출력하고 종료
		if(idx == S) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		// list에서 0인 값 하나 꺼내, 될 수 없는 값들 체크하기
		Node n = list.get(idx);
		boolean[] notOk = new boolean[N+1]; // 0 dummy
		
		// 가로
		for (int y = 0; y < N; y++) {
			if(map[n.x][y] != 0) notOk[map[n.x][y]] = true;
		}
		
		// 세로
		for (int x = 0; x < N; x++) {
			if(map[x][n.y] != 0) notOk[map[x][n.y]] = true;
		}
		
		// 3*3 배열
		int startX = (n.x)/3 * 3;
		int startY = (n.y)/3 * 3;
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if(map[i][j] != 0) notOk[map[i][j]] = true;
			}
		}
		
		// check가 안된 값에 대해 하나씩 대입
		for (int i = 1; i < N+1; i++) {
			if(notOk[i]) continue;
			map[n.x][n.y] = i;
			dfs(idx+1);
			map[n.x][n.y] = 0;
		}
	}
	
}
