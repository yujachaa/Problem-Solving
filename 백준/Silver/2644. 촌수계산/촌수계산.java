import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//그래프 탐색
	//dfs
	//찾은 경우에만 answer 갱신
	
	static int answer, A, B, N;
	static boolean[] visited;
	static ArrayList<Integer>[] graph; //인접리스트로 그래프 구현
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 전체 사람의 수
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken()); // 촌수 계산할 번호
		B = Integer.parseInt(st.nextToken()); // 촌수 계산할 번호
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 관계의 개수
		
		graph = new ArrayList[N + 1]; //1~N
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		} //인접리스트 배열 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x].add(y);
			graph[y].add(x); //양방향 그래프
		} //입력 끝
		
		answer = -1; //못 찾은 경우 -1이므로 초기화
		visited = new boolean[N + 1]; //1~N
		
		dfs(A, 0); 
		
		System.out.println(answer);
		
	}

	//now : 현재 위치, count : 촌수 카운트
	private static void dfs(int now, int count) {
		//기저조건
		if(now == B) { //찾은 경우
			answer = count;
			return;
		}
		
		//현재 위치에서 할 일
		for(int next : graph[now]) { //현재 노드에 연결된 곳 탐색
			if(!visited[next]) {
				visited[next] = true;
				dfs(next, count + 1);
			}
		}
	}
}
