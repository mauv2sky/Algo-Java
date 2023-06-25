import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2 3 4 2 5 1 7 3 2 9 10 3 12 8 21 37 12 35 40

public class SWEA3307_최장증가부분순열 {

	static int T, N, max;
	static int[] nums;
	static int[] LIS;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			nums = new int[N];
			LIS = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			max = 0;
			for (int i = 0; i < N; i++) {
				LIS[i] = 1;
				
				for (int j = 0; j < i; j++) {
					if(nums[j] < nums[i] && LIS[i] < LIS[j] + 1)
						LIS[i] = LIS[j] + 1;
				}
				
				max = Math.max(max, LIS[i]);
			}
			
			System.out.println("#" + t + " " + max);
		}
	}
}
