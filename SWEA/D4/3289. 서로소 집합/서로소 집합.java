import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// union
	// isSame -> findset 해서 비교
	// findset

	static int T, N, M;
	static int[] p; // 대표를 저장하는 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			p = new int[N + 1];
			// makeset - 자기자신을 대표로 채워넣기
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				int pa = findset(a);
				int pb = findset(b);

				if (command == 0) { // 합집합					
					if(pa != pb) {
						union(pa, pb);
					}
				} else { // 같은집합인지 확인 - 같은집합이면 1, 아니면 0
					if(pa == pb)
						sb.append("1");
					else
						sb.append("0");
				}
			}
			System.out.printf("#%d %s\n", tc, sb);
		} // 테스트케이스 끝
	}

	// a집합 b집합 합하는 메서드
	static void union(int pa, int pb) {
		//대표가 입력으로 들어오니까
			p[pb] = pa; //냅다 b를 a에 넣어버리기
	}

	// a가 속해있는 집합의 대표를 찾는 메서드
	static int findset(int a) {
		if(a != p[a]) //내가 대표가 아니면 갱신
			p[a] = findset(p[a]); 
		return p[a];
	}
}
