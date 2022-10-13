package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_수영장 {

	static int T;
	static int[] price;
	static int[] plan;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			price = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			plan = new int[13]; // 0 dummy
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[13];
			for (int i = 1; i <= 12; i++) {
				int day = dp[i-1] + plan[i]*price[0];
				int month = dp[i-1] + price[1];
				int month3 = i >= 3 ? dp[i-3] + price[2] : Integer.MAX_VALUE;
				int year = i==12 ? price[3] : Integer.MAX_VALUE;
				
				dp[i] = Math.min(day, Math.min(month, Math.min(month3, year)));
			}
			
			System.out.println("#" + t + " " + dp[12]);
		}
	}

}
