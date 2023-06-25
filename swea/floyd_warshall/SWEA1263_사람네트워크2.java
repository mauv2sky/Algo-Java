import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1263_사람네트워크2 {

	static int T, N;
	static final int INF = 999999;
	static int[][] C; // dp
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			C = new int[N][N];
			// 입력으로부터 인접행렬 생성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 0 && i != j) C[i][j] = INF;
					else C[i][j] = tmp;
				}
			}
			
			// 플로이드워샬 적용
			// C[][]를 계속 갱신 : 3중 for문, 경유지 k
			// a -> b 는 이미 주어져 있다.
			// a -> b 로 가는 비용을 a -> k -> b (a -> k 비용 + k -> b 비용)
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						C[i][j] = Math.min(C[i][k] + C[k][j], C[i][j]);
					}
				}
			}
			
			// 최종적으로 matrix 완성되면
			// a -> b, a -> c, a -> d 를 모두 합치고,
			// b -> a, b -> c, b -> d 를 모두 합치고,
			// ... 이 중 가장 적은 비용을 선택
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += C[i][j];
				}
				min = Math.min(min, sum);
			}
			
			System.out.println("#" + t + " " + min);
		}

	}

}
