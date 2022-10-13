import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 출력 : 이동 완료 시간 = 모든 사람들이 계단을 내려가 아래층으로 이동을 완료한 시간
// 1. 두개의 계단에 대해 모든 사람의 부분집합 구하기
// 2. 각 사람들과 계단과의 거리 구해서 우선순위 큐에 각각 기록 - 대기 큐
// 3. 계단 입구에 도착했을 때 이동완료시간을 정할 수 있음 -> 도착한 시간 + 계단길이 + 1
// 4. 모든 인원이 도착완료 했을 때 최소값 갱신

public class SWEA2383_점심식사시간 {

	static int T, N, P, res;
	static List<Node> person;
	static Node[] stair;
	
	static boolean[] select;	// 부분집합
	static PriorityQueue<Node> pq1, pq2; // 각 계단에 대한 대기 큐
	static int[] down1, down2; // 내려가는 시간
	
	static class Node {
		int x, y;
		int c;	// 계단의 길이
		int d;	// 계단과 사람의 거리
		
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
			
			person = new ArrayList<>();
			stair = new Node[2];
			
			int idx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp == 0) continue;
					if(tmp == 1) person.add(new Node(i, j));
					else {
						stair[idx] = new Node(i, j);
						stair[idx++].c = tmp;
					}
				}
			}
			
			res = Integer.MAX_VALUE;
			P = person.size();
			select = new boolean[P];
			
			down1 = new int[3];
			down2 = new int[3];
			
			choice(0);
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void choice(int cnt) {
		if(cnt == P) {
			waiting();
			return;
		}
		
		select[cnt] = true;
		choice(cnt + 1);
		
		select[cnt] = false;
		choice(cnt + 1);
	}
	
	static void waiting() {
		pq1 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.d - o2.d;  // 도착하는 시간(거리) 오름차순 정렬
			}
		});
		
		// select T -> 1번 계단으로 가는 사람
		for (int i = 0; i < P; i++) {
			if(select[i]) {
				Node p = person.get(i);
				int d = Math.abs(stair[0].x - p.x) + Math.abs(stair[0].y - p.y);
				p.d = d;
				pq1.offer(p);
			}
		}
		
		pq2 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.d - o2.d;  // 도착하는 시간(거리) 오름차순 정렬
			}
		});
		// select F -> 2번 계단으로 가는 사람
		for (int i = 0; i < P; i++) {
			if(!select[i]) {
				Node p = person.get(i);
				int d = Math.abs(stair[1].x - p.x) + Math.abs(stair[1].y - p.y);
				p.d = d;
				pq2.offer(p);
			}
		}
		
		downStair();
	}
	
	static void downStair() {
		int time = 1;
		int cnt = P;
		
		while(true) {
			
			if(cnt == 0) { // 모든 사람이 내려왔고, 계단에 아무도 없는지 확인
				boolean flag = true;
				for (int i = 0; i < 3; i++) {
					if(down1[i] != 0) {
						flag = false;
						break;
					}
					if(down2[i] != 0) {
						flag = false;
						break;
					}
				}
				
				if(flag) break;
			}
			
			// 계단에는 최대 3명이 내려갈 수 있음
			for (int i = 0; i < 3; i++) {
				if(down1[i] == 0) { // 비어있는 계단이 있으면 한명 내려가기 시작
					if(!pq1.isEmpty()) {
						if(pq1.peek().d <= time) {
							cnt--;
							down1[i] = stair[0].c;
							pq1.poll();
						}
					}
				} else {  // 비어있는 계단이 아니면
					down1[i]--; // 시간을 1 감소한다.
					if(down1[i] == 0) { // 감소했더니 사람이 다 내려갔다면 한명 내려가기 시작
						if(!pq1.isEmpty()) {
							if(pq1.peek().d <= time) {
								down1[i] = stair[0].c;
								cnt--;
								pq1.poll();
							}
						}
					}
				}
				
				if(down2[i] == 0) {
					if(!pq2.isEmpty()) {
						if(pq2.peek().d <= time) {
							cnt--;
							down2[i] = stair[1].c;
							pq2.poll();
						}
					}
				} else {
					down2[i]--;
					if(down2[i] == 0) {
						if(!pq2.isEmpty()) {
							if(pq2.peek().d <= time) {
								down2[i] = stair[1].c;
								cnt--;
								pq2.poll();
							}
						}
					}
				}
			}
			
			time++;
		}
		res = Math.min(res, time);
	}
}
