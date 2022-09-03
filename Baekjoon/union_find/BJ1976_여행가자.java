package union_find;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1976_여행가자 {

	static int N, M, res;
	static int[] parent;
	static int[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) {
					union(i, j);
				}
			}
		}
		
		list = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		
		res = 1;
		for (int i = 0; i < M-1; i++) {
			if(parent[list[i]] != parent[list[i+1]]) {
				res = 0;
				break;
			}
		}
		
		System.out.println(res == 1 ? "YES" : "NO");
	}

	static int findParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a != b) {
			if(a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
	}
	
}
