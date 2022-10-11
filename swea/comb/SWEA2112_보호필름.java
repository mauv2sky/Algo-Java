import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 약품을 넣기 전에 검사 수행 -> 각 행에 대해 두 가지 약품을 하나씩 넣어봄 -> 약품 넣은 상태로 검사 수행
// 약품을 넣는 방법 => 한 행에 A를 넣거나, B를 넣거나 -> 그 다음 행에서도 A를 넣거나 B를 넣거나 : 조합

public class SWEA2112_보호필름 {

	static int T, D, W, K, res;
	
	static int[][] film, copyFilm;
	static boolean[] able;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			copyFilm = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
					copyFilm[i][j] = film[i][j];
				}
			}
			
			res = Integer.MAX_VALUE;
			
			if(K == 1) {
				System.out.println("#" + t + " 0");				
			} else {
				insert(0, 0);
				System.out.println("#" + t + " " + res);
			}
		}
	}
	
	static void insert(int src, int cnt) {
		if(cnt >= res) return; // 약품처리 횟수가 res보다 크거나 같으면 가지치기
		
		if(test()) {  // 성능검사 통과하면 약품 넣은 횟수 최소값 갱신 후 리턴
			res = Math.min(res, cnt);
			return;
		}
		
		if(src == D) return;
		
		for (int i = src; i < D; i++) {
			
			Arrays.fill(copyFilm[i], 0);  // A로 채우기
			insert(i+1, cnt+1);
			Arrays.fill(copyFilm[i], 1);  // B로 채우기
			insert(i+1, cnt+1);
			
			copy(i);
		}
	}
	
	static boolean test() {
		// 열에 K개 이상의 연속적인 셀이 존재하는지 확인
		able = new boolean[W];
		for (int y = 0; y < W; y++) {
			int sameCnt = 1;
			
			for (int x = 1; x < D; x++) {
				if(copyFilm[x-1][y] == copyFilm[x][y]) sameCnt++;
				else sameCnt = 1;
				
				if(sameCnt == K) {
					able[y] = true;
					break;
				}
			}
		}
		
		for (int i = 0; i < W; i++) {
			if(!able[i]) return false;
		}
		return true;
	}
	
	static void copy(int x) {
		for (int j = 0; j < W; j++) {
			copyFilm[x][j] = film[x][j];
		}
	}
}
