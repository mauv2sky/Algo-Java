import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9205_맥주마시면서걸어가기 {

	static int T, N;
	static Node home; // 집의 좌표
	static Node[] convi; // 편의점 좌표
	static Node rock; // 락 페스티벌 좌표
	
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
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			home = new Node(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			
			convi = new Node[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				convi[i] = new Node(Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			rock = new Node(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			
			System.out.println(bfs() ? "happy" : "sad");
		}
	}

	static boolean bfs() {
		Queue<Node> qu = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		
		qu.offer(home);
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			// 한 박스 맥주 20병, 50미터마다 한 병 -> 1000미터 마지노선
			// 가지고 있는 맥주로 현재 위치에서 락 페스티벌로 갈 수 있는지 확인
			if(Math.abs(n.x - rock.x) + Math.abs(n.y - rock.y) <= 1000) return true;
			
			for (int i = 0; i < N; i++) {
				if(visit[i]) continue;
				
				// 현재 위치에서 갈 수 있는 편의점이 있는지 확인
				if(Math.abs(n.x - convi[i].x) + Math.abs(n.y - convi[i].y) <= 1000) {
					visit[i] = true;
					qu.offer(new Node(convi[i].x, convi[i].y));
				}
			}
		}
		
		return false;
	}
	
}
