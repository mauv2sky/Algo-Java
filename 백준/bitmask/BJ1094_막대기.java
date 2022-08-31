package bitmask;

import java.util.Scanner;

public class BJ1094_막대기 {
	
	static int X, N, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		cnt = 0;
		
		for (int i = 0; i <= 6; i++) {
			if((X & (1<<i)) != 0) cnt++;
		}
		System.out.println(cnt);
	}

}
