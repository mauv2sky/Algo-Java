package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_수영장 {

	static int T, min;
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
			
			plan = new int[12]; // 0 dummy
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			min = price[3];
			dfs(0, 0); // idx, sum
			
			System.out.println("#" + t + " " + min);
		}
	}
	
	static void dfs(int idx, int sum) {
		if(idx >= 12) {
			min = Math.min(min, sum);
			return;
		}
		
		dfs(idx+1, sum + plan[idx] * price[0]);
		dfs(idx+1, sum + price[1]);
		dfs(idx+3, sum + price[2]);
	}

}
