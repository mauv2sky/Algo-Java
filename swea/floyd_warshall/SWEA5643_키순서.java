import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5643_키순서 {

	static int T, N, M, res;
	static final int BIG = 1000;
	static int[][] C;
	static int[] connect;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			C = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(C[i], BIG);				
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int lt = Integer.parseInt(st.nextToken()) - 1;
				int gt = Integer.parseInt(st.nextToken()) - 1;
				
				C[lt][gt] = 1; // 행이 열보다 작다
			}
			
			// k 학생을 통해서 i, j 학생이 이어지는지 확인
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if(i == k) continue;
					for (int j = 0; j < N; j++) {
						if(i == j || k == j) continue;
						if(C[i][j] > C[i][k] + C[k][j])
							C[i][j] = C[i][k] + C[k][j];
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(C[i]));
			}
			
			// 자신과 키순서로 이어질 수 있는 학생들의 수 세기
			connect = new int[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(C[i][j] != BIG) {
						connect[i]++;
						connect[j]++;
					}
				}
			}
			
			// 순서대로 선 학생들의 수(자신보다 작은 학생 + 자신보다 큰 학생)가 N-1이면 자신의 순서를 알 수 있음
			res = 0;
			for (int i = 0; i < N; i++) {
				System.out.println(connect[i]);
				if(connect[i] == N-1) res++;
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

}