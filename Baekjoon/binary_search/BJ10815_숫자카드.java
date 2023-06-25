import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10815_숫자카드 {
	
	static int N, M;
	static int[] card;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		card = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(card);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(findCard(num))
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
	}
	
	static boolean findCard(int num) {
		
		int lo = 0;
		int hi = N-1;
		
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			
			if(card[mid] == num) return true;
			else if(card[mid] < num) lo = mid+1;
			else if(card[mid] > num) hi = mid-1;
		}
		
		return false;
	}
}
