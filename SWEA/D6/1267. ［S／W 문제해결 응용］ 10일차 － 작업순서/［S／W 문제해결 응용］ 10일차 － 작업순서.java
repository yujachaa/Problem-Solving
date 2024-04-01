import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T = 10;
	static int V, E;
	static int[][] adj; // 인접 배열
	static int[] in_degree; // 진입차수
	static Queue<Integer> queue; // 정렬된 정답을 담을 스택

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수

			adj = new int[V + 1][V + 1]; // 정점 번호 1~V
			in_degree = new int[V + 1];
			queue = new LinkedList<>(); // 큐

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) { // E개의 간선정보 입력
				int A = Integer.parseInt(st.nextToken()); // 진출
				int B = Integer.parseInt(st.nextToken()); // 진입
				adj[A][B] = 1;
				in_degree[B]++;
			}

			// 1. 진입차수가 0인 노드를 다 큐에 넣기
			for (int i = 1; i < V + 1; i++) {
				if (in_degree[i] == 0)
					queue.add(i);
			}

			StringBuilder sb = new StringBuilder();

			// 2. 큐가 빌때까지 한개꺼내서
			while (!queue.isEmpty()) {
				int v = queue.poll();
				sb.append(v + " ");
				// 3. 인접 노드의 연결 간선 1개 지우기
				for (int i = 1; i < V + 1; i++) {
					if (adj[v][i] == 1) {
						in_degree[i]--;
						
						// 4. 진입차수 0개된게 있으면 큐에 넣기
						if (in_degree[i] == 0) {
							queue.add(i);
						}
					}
				}
			}

			System.out.printf("#%d %s\n", tc, sb);
		} // 테스트케이스 끝
	}

}
