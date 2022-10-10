import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* 참고
 * https://velog.io/@hyeon930/SWEA-5653-%EC%A4%84%EA%B8%B0%EC%84%B8%ED%8F%AC%EB%B0%B0%EC%96%91-Java
 * */

/* 규칙
 * 1. 줄기세포는 초기상태에서 비활성상태.
 * 2. 생명력 수치가 n인 줄기세포는 n시간동안 비활성 상태이다가 n시간이 지나는 순간 활성상태가 된다.
 * 3. 줄기세포가 활성화되면 n시간동안 살아있을 수 있고 n시간이 지나면 세포는 죽게된다.
 * 4. 활성화된 줄기세포는 첫 1시간동안 사방으로 동시에 번식한다. -> 번식된 줄기세포는 비활성상태
 * 5. 두 개 이상의 줄기세포가 한 셀에 동시 번식하려고 할 경우, 생명력 수치가 높은 줄기세포가 차지
 * 
 * 고려
 * 1. x시간동안 비활성 상태 -> x시간이 지나는 순간 활성 -> x시간 살아있다가 죽음
 * 		=> 번식된 시점에 활성되는 시간과 죽는 시간을 현재 시간 기준으로 저장
 * 2. 배양용기의 크기 무한, 초기상태의 줄기세포 분포영역 최대 50, 배양시간 최대 300
 * 		=> 초기 분포영역에 적당한 크기로 패딩 주기
 * */

public class SWEA5653_줄기세포배양 {
	
	static int T, N, M, K, time;
	
	static final int MAP_SIZE = 1000;
	static final int PADDING = 400;
	
	static boolean[][] visit;
	static Queue<Cell> qu;          // 비활성화된 줄기세포를 담을 큐
	static PriorityQueue<Cell> pq;  // 활성화된 줄기세포를 담을 우선순위 큐
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Cell implements Comparable<Cell> {
		int x, y, p, active, dead;
		Cell(int x, int y, int p, int active, int dead) {
			this.x = x;
			this.y = y;
			this.p = p;
			this.active = active;  // 활성화될 시간
			this.dead = dead;      // 죽을 시간
		}
		
		@Override
		public int compareTo(Cell o) {
			return o.p - this.p;   // 생명력 내림차순 정렬
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
			K = Integer.parseInt(st.nextToken());
			
			visit = new boolean[MAP_SIZE][MAP_SIZE];
			qu = new ArrayDeque<>();
			pq = new PriorityQueue<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int p = Integer.parseInt(st.nextToken());
					if(p != 0) {
						visit[i+PADDING][j+PADDING] = true;
						qu.offer(new Cell(i+PADDING, j+PADDING, p, p, p*2));
					}
				}
			}
			
			time = 0;
			while(time++ < K) {
				check();  // qu에 있는 세포의 상태 확인
				spread(); // 번식
			}
			
			System.out.println("#" + t + " " + qu.size());
		}
	}
	
	static void check() {
		int size = qu.size();
		
		for (int i = 0; i < size; i++) {
			Cell c = qu.poll();
			
			if(time <= c.active) qu.offer(c); // 활성화되기 전이면 qu에 삽입
			else if(time == c.active + 1) pq.offer(c); // 활성화되면 pq에 삽입
			else if(c.active < time && time < c.dead) qu.offer(c); // 죽기 전이면 qu에 삽입
		}
	}
	
	static void spread() {
		while(!pq.isEmpty()) {
			Cell c = pq.poll();
			
			if(time < c.dead) qu.offer(c);
			
			for (int d = 0; d < 4; d++) {
				int tx = c.x + dx[d];
				int ty = c.y + dy[d];
				
				if(visit[tx][ty]) continue;
				
				visit[tx][ty] = true;
				qu.offer(new Cell(tx, ty, c.p, time + c.p, time + c.p*2));
			}
		}
	}
}
