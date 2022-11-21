import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_전기버스2 {

	static int T, N, res;
	static int[] charger;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			charger = new int[N-1];
			for (int i = 0; i < N-1; i++) {
				charger[i] = Integer.parseInt(st.nextToken());
			} // 입력 끝
			
			res = Integer.MAX_VALUE;
			dfs(charger[0], 1, 0);
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int fuel, int idx, int changeCnt) {
		if(changeCnt > res) return;
		if(fuel == 0) return;
		
		if(idx == N-1) {
			res = Math.min(res, changeCnt);
			return;
		}
		
		
		dfs(fuel - 1, idx + 1, changeCnt);
		dfs(fuel - 1 + charger[idx], idx + 1, changeCnt + 1);
	}
}
