import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4012_요리사 {

	static int T, N, res;
	static int[][] food;
	static boolean[] select;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			food = new int[N][N];
			select = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					food[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = Integer.MAX_VALUE;
			comb(0, 0);
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void comb(int idx, int cnt) {
		if(cnt == N/2) {
			calc();
			return;
		}
		
		for (int i = idx; i < N; i++) {
			select[i] = true;
			comb(i+1, cnt+1);
			select[i] = false;
		}
	}
	
	static void calc() {
		
		int sumA = 0;
		int sumB = 0;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i; j < N; j++) {
				if(i == j) continue;
				
				if(select[i] && select[j])
					sumA += food[i][j] + food[j][i];
				else if(!select[i] && !select[j])
					sumB += food[i][j] + food[j][i];
			}
		}
		
		res = Math.min(res, Math.abs(sumA - sumB));
	}
}
