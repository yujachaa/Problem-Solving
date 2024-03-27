import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	// 무리의 개수 -> 집합의 개수...

	static int T, N, M;
	static int[][] edges;
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수
			M = Integer.parseInt(st.nextToken()); // 간선 개수
			edges = new int[M][2]; // 0:시작정점, 1:끝정점 (가중치 X)
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				edges[i][0] = Integer.parseInt(st.nextToken());
				edges[i][1] = Integer.parseInt(st.nextToken());
			} // 간선입력 끝

			p = new int[N + 1]; // 정점마다 하나의 집합이다. 1번~N번까지
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			// 간선 연결정보 모두 유니온 해서 서로소집합 만들기
			for (int i = 0; i < M; i++) {
				int pa = findset(edges[i][0]);
				int pb = findset(edges[i][1]);
				
				if(pa != pb) { //대표가 다르면 유니온
					union(pa, pb);
				}
			}
			// 대표배열 스캔해서 대표 찾아서 셋에 넣기 (중복제거)
			Set<Integer> set = new HashSet<>();
			for(int i = 1; i < p.length; i++) {
				set.add(findset(p[i]));
			}
			
			// 셋 길이 = 집합 개수
			int answer = set.size();
			
			System.out.printf("#%d %d\n", tc, answer);
		} // 테스트케이스 끝
	}

	static void union(int pa, int pb) {
		//대표를 입력하니까 a에 b집합 냅다 넣기
		p[pb] = pa;
	}

	static int findset(int a) {
		//패스 컴프레션
		if(p[a] != a)
			p[a] = findset(p[a]);
		return p[a];
	}
}
