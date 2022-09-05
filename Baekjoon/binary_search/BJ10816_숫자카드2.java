package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10816_숫자카드2 {

	static int N, M;
	static int[] card;
	
	static int mid;
	static int[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		card = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card);  // 이진탐색을 위한 정렬
		mid = card.length/2;
		
		M = Integer.parseInt(br.readLine());
		result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			sb.append(upper_bound(num) - lower_bound(num)).append(' ');
		}
		System.out.println(sb.toString());
	}

	// 찾는 값이 있는 범위의 시작점(하한) 찾기
	static int lower_bound(int num) {
		int low = 0;
		int high = card.length;
		
		while(low < high) {
			int mid = (low + high)/2;
			
			// 중앙 인덱스의 값이 찾는 값보다 작거나 같으면
			// 상한선을 중간 인덱스로 내린다.
			if(num <= card[mid]) {
				high = mid;
			} else {
				// 중앙인덱스의 값이 찾는 값보다 크다면
				// 하한선을 중앙인덱스+1로 올린다.
				low = mid + 1;
			}
		}
		return low;
	}
	
	// 찾는 값이 있는 범위 바로 직후에 있는 큰 수(상한) 찾기
	static int upper_bound(int num) {
		int low = 0;
		int high = card.length;
		
		while(low < high) {
			int mid = (low + high)/2;
			
			// 중앙인덱스의 값이 찾는 값보다 작으면
			// 상한선을 중앙 인덱스로 올린다.
			// (하한선에서 같은 값을 체크했기 때문에 제외)
			if(num < card[mid]) {
				high = mid;
			} else {
				low = mid+1;
			}
		}
		return low;
	}
	
}
