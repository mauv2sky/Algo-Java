import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 순열
public class SWEA4008_숫자만들기 {

	static int T, N, C, min, max;
	static int[] ops; // 0: +, 1: -, 2: *, 3: /
	static int[] nums;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			ops = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			tgt = new int[N-1];
			
			dfs(0);
			
			System.out.println("#" + t + " " + (max - min));
		}
	}

	
	static void dfs(int cnt) {
		if(cnt == N-1) {
			calc();
//			System.out.println(Arrays.toString(tgt));
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(ops[i] > 0) {
				ops[i]--;
				tgt[cnt] = i;
				dfs(cnt+1);
				ops[i]++;
			}
		}
	}
	
	static void calc() {
		int result = nums[0];
		
		for (int i = 0; i < N-1; i++) {
			if(tgt[i] == 0) {			// '+'
				result += nums[i+1];
			} else if (tgt[i] == 1) {	// '-'
				result -= nums[i+1];
			} else if (tgt[i] == 2) {	// '*'
				result *= nums[i+1];
			} else if (tgt[i] == 3) {	// '/'
				result /= nums[i+1];
			}
		}
		
		min = Math.min(min, result);
		max = Math.max(max, result);
	}
}
