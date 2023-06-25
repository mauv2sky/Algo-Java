package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17143_낚시왕 {

	static int R, C, M, sum;
	static Shark[][] map;
	static List<Shark> list;
	
	static int[] dx = {-1, 1, 0, 0}; // 상우하좌
	static int[] dy = {0, 0, 1, -1};
	
	static class Shark {
		int r, c, s, d, z;
		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R+1][C+1];
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(r, c, s, d, z);
			list.add(shark);
			map[r][c] = shark;
		}
		
		for (int i = 1; i <= C; i++) { // 낚시왕이 이동하는 열의 범위
			// 상어 잡기
			catchShark(i);
			// 상어 이동
			moveShark();
			// 중복 제거
			killShark();
		}
		
		System.out.println(sum);
	}
	
	static void catchShark(int y) {
		for (int x = 1; x <= R; x++) {
			if(map[x][y] != null) {
				sum += map[x][y].z;
				list.remove(map[x][y]);
				break;
			}
		}
	}

	static void moveShark() {
		for (Shark s : list) {
			
			int speed;
			
			switch(s.d) {
			case 0: // 상하
			case 1:
				speed = s.s % (R*2 - 2);
				for(int i=0; i<speed; i++) {
					if(s.r == 1) s.d = 1;
					else if(s.r == R) s.d = 0;
					s.r += dx[s.d];
				}
				break;
			case 2: // 좌우
			case 3:
				speed = s.s % (C*2 - 2);
				for(int i=0; i<speed; i++) {
					if(s.c == 1) s.d = 2;
					else if(s.c == C) s.d = 3;
					s.c += dy[s.d];
				}
				break;
			}
		}
	}
	
	static void killShark() {
		// 기존 맵 초기화
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = null;
			}
		}
		
		// 리스트의 값 꺼내서 맵에 적용
		int size = list.size();
		for (int i = size-1; i >= 0; i--) {
			Shark s = list.get(i);
			if(map[s.r][s.c] == null) {
				map[s.r][s.c] = s;
			} else {
				if(map[s.r][s.c].z < s.z) {
					list.remove(map[s.r][s.c]);
					map[s.r][s.c] = s;
				} else {
					list.remove(i);
				}
			}
		}
	}
	
}
