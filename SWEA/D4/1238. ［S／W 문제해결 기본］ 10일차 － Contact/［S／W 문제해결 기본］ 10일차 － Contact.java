import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int num, depth;

	public Node(int num, int depth) {
		super();
		this.num = num;
		this.depth = depth;
	}
}
public class Solution {

	static int N, start, result;
	static List<Integer>[] adjList;
	static boolean[] visited;
	

	public static void main(String[] args) throws Exception {
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 입력데이터 길이
			start = Integer.parseInt(st.nextToken()); // 연락 시작점
			
			// 인접 리스트 -> 셋으로 구현해서 중복 제거
			adjList = new ArrayList[101]; // 1부터 100까지
			for(int i = 1; i < 101; i++) {
				adjList[i] = new ArrayList<>(); //초기화
			}
			visited = new boolean[101];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adjList[A].add(B); // 유방향 그래프
			}
			
			result = 0;
			bfs(start);
			
			System.out.printf("#%d %d\n", tc, result);

		} // 테스트케이스 끝
	}

	// 시작 노드부터~~
	private static void bfs(int start) {
		Queue<Node> queue = new LinkedList<>();

		queue.add(new Node(start, 0));
		
		int maxDepth = 0;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
//			if(visited[now.num])continue;
			visited[now.num] = true;
			
			if(now.depth > maxDepth) {
				maxDepth = now.depth;
				result = now.num;
			} else if (now.depth == maxDepth) {
				result = Math.max(result, now.num);
			}
			
			for(int i = 0; i < adjList[now.num].size(); i++) {
				Node next = new Node(adjList[now.num].get(i), now.depth + 1);
				
				if(visited[next.num]) continue;
				visited[next.num] = true;
				queue.add(next);
			}
		}
	}
}
