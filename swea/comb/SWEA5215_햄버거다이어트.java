import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_햄버거다이어트 {

	static int T, N, L, res;
	static Food[] list;
	
	static class Food {
		int t, k;
		Food(int t, int k) {
			this.t = t;
			this.k = k;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			list = new Food[N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int T = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				
				list[j] = new Food(T, K);
			} // 입력 끝
			
			res = Integer.MIN_VALUE;
			dfs(0, 0, 0);
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int idx, int score, int cal) {
		if(cal > L) return;
		
		if(idx == N) {
			res = Math.max(res, score);
			return;
		}
		
		dfs(idx + 1, score + list[idx].t, cal + list[idx].k);
		dfs(idx + 1, score, cal);
	}
}
