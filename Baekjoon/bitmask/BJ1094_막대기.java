package bitmask;

import java.util.Scanner;

public class BJ1094_막대기 {
	
	static int X, N, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		cnt = 0;
		
		for (int i = 0; i <= 6; i++) { // 64의 이진수 길이만큼 반복
			if((X & (1<<i)) != 0) cnt++; // 이진수로 변환한 X의 1의 갯수 세기
		}
		System.out.println(cnt);
	}

}
