import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 접수 창구번호 1-N, 정비 창구번호 1-M
// 

public class SWEA2477_차량정비소 {
	
	static int T, N, M, K, A, B;
	static int[] time1; 	// 접수창구의 처리시간
	static int[] time2;		// 정비창구의 처리시간
	
	
	static PriorityQueue<Customer> waitingW1;	// 접수창구에 대기중인 고객
	static PriorityQueue<Customer> waitingW2;	// 정비창구에 대기중인 고객
	
	static Customer[] w1;	// 접수창구의 고객
	static Customer[] w2;	// 정비창구의 고객
	
	static class Customer {
		int k, tk;
		int w1, endW1; // 접수창구 번호와 종료시간
		int w2, endW2; // 정비창구 번호와 종료시간
		Customer(int k, int tk) {
			this.k = k;
			this.tk = tk;
		}
		@Override
		public String toString() {
			return "Customer [k=" + k + ", tk=" + tk + ", w1=" + w1 + ", endW1=" + endW1 + ", w2=" + w2 + ", endW2="
					+ endW2 + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			/* 초기화 */
			int time = 0;
			int res = 0;
			int cnt = 0;	// 정비까지 마친 고객의 수
			
			waitingW1 = new PriorityQueue<Customer>((c1, c2) -> c1.k - c2.k); // 번호 순
			waitingW2 = new PriorityQueue<>(new Comparator<Customer>() { // 대기순 -> 접수창구번호 순
				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.endW1 == o2.endW1) {
						return o1.w1 - o2.w1;
					} else {
						return o1.endW1 - o2.endW1;
					}
				}
			});
			
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			time1 = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time1[i] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.println(Arrays.toString(time1));
			
			time2 = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				time2[i] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.println(Arrays.toString(time2));
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				int tk = Integer.parseInt(st.nextToken());
				waitingW1.offer(new Customer(i, tk));
			}
			
			w1 = new Customer[N+1];
			w2 = new Customer[M+1];
			
			// 1초동안의 체크
			while(true) {
//				System.out.println("time : " + time);
				
				// 접수창구 비우기
				for (int i = 1; i <= N; ++i) {
					if(w1[i] == null) continue;
					if(w1[i].endW1 == time) {
						Customer c = w1[i];
						w1[i] = null;
						waitingW2.offer(c);
//						System.out.println("W1 Out : " + c);
					}
				}
				
				// 접수창구 채우기
				for (int i = 1; i <= N; i++) {
					if(w1[i] == null) {
						if(!waitingW1.isEmpty()) {
							if(waitingW1.peek().tk <= time) {
								w1[i] = waitingW1.poll();
								w1[i].w1 = i;
								w1[i].endW1 = time + time1[i];
//								System.out.println("W1 In : " + w1[i]);
							}
						}
					}
				}
				
				// 정비창구 비우기
				for (int i = 1; i <= M; i++) {
					if(w2[i] == null) continue;
					if(w2[i].endW2 == time) {
						Customer c = w2[i];
						w2[i] = null;
						if(c.w1 == A && c.w2 == B) res += c.k;
						cnt++;
//						System.out.println("W2 Out : " + c);
					}
				}
				
				// 정비창구 채우기
				for (int i = 1; i <= M; i++) {
					if(w2[i] == null) {
						if(!waitingW2.isEmpty()) {
							if(waitingW2.peek().tk <= time) {
								w2[i] = waitingW2.poll();
								w2[i].w2 = i;
								w2[i].endW2 = time + time2[i];
//								System.out.println("W2 In : " + w2[i]);
							}
						}
					}
				}
				
				if(cnt == K) break;
				
				time++;
			}
			
			if(res == 0) res = -1;
			System.out.println("#" + t + " " + res);
		}
	}
}
