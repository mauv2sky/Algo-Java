import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 참고
 * https://buddev.tistory.com/13
 * */

/* 배운 것
 * 1. 2차원 배열에서 동일한 로직으로 가로 세로를 탐색해야 할 때,
 * 		입력단계에서 원본 배열과 행과열을 바꾼 배열 두 가지를 만들면 하나의 함수로 처리 가능하다.
 * 2. 조건을 잘 나누자 .. 연산이 필요없는 부분도 있다 ..
 * 		오르막 경사를 만날 경우 같은 높이 카운트한 값을 사용, 내리막은 for문으로 탐색 필요
 * */


public class SWEA4014_활주로건설 {

	static int T, N, X, res;
	static int[][] garo;
	static int[][] sero;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			garo = new int[N][N];
			sero = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					garo[i][j] = Integer.parseInt(st.nextToken());
					sero[j][i] = garo[i][j];
				}
			}
			
			res = 0;
			start(garo);
			start(sero);
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void start(int[][] map) {
		for (int i = 0; i < N; i++) {
			boolean able = true;
			int sameCnt = 1;
			
			for (int j = 1; j < N; j++) {
				if(map[i][j-1] != map[i][j]) { // 값이 다르면
					if(map[i][j-1] == map[i][j] + 1) { // 내리막						
						// 경사로를 놓을 수 있는지 확인
						if(check(map, i, j, map[i][j])) {
							j += X-1;
						} else {
							able = false;
							break;
						}
						
						sameCnt = 0; // 경사로의 마지막 인덱스의 높이가 변했기 때문에 0으로 설정
						
					} else if (map[i][j-1] == map[i][j] - 1) { // 오르막
						if(sameCnt < X) {
							able = false;
							break;
						}
						
						sameCnt = 1;
					} else { // 경사로를 놓을 수 없음
						able = false;
						break;
					}
				} else { // 값이 같으면
					sameCnt++;
				}
			}
			if(able) res++;
		}
	}
	
	static boolean check(int[][] map, int x, int y, int s) {
		int cnt = 0;
		for (int i = y; i < y + X; i++) {
			if(i >= N) break;
			
			if(map[x][i] == s) cnt++;
			else break;
		}
		
		if(cnt < X) return false;
		return true;
	}
}
