import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//단방향 그래프
	//bfs
	//팬클럽 정점 번호 리스트로 저장
	//bfs 할 때 찾아서 리스트에서 제거
	//리스트 길이 0 -> Yes / 아니면 yes 출력 => 예제 2번 틀림
	//수정) 마지막정점(연결된 간선이 없는 정점) 에 올때까지 팬클럽을 만나지 않는 경우 체크
	//dfs로도 풀이 가능
	
	static int N, M, S;
	static boolean[] visited;
	static List<Integer>[] adjList; //인접리스트
	static List<Integer> fanList; 
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //정점의 개수 ~ 100000
		M = Integer.parseInt(st.nextToken()); //간선의 개수 ~ 100000
		
		adjList = new ArrayList[N + 1]; // 1~N
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adjList[u].add(v); //단방향 그래프
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); //팬클럽의 개수 ~ 100000(N)
		
		fanList = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < S; i++) {
			fanList.add(Integer.parseInt(st.nextToken())); //팬클럽 정점 추가
		} //입력 끝
		
		visited = new boolean[N + 1]; //1~N
		flag = false; //팬을 만나지 않고 도달할 수 있는지 체크하는 플래그
//		bfs(1);
		dfs(1);
		
		if(!flag) {
			System.out.println("Yes");
		}else {
			System.out.println("yes");
		}
		
	}

	private static void dfs(int now) {
		//기저조건
		if(fanList.contains(now)) return; //팬이 있는 길이면 리턴
		
		if(adjList[now].isEmpty()) { //마지막 정점 도달
			//여기까지 팬을 만나지 않은 것이므로 플래그 체크
			flag = true;
			return;
		}
		
		//현재 위치에서 할 일
		visited[now] = true;
		for(int next : adjList[now]) {
			if(!visited[next]) {
				dfs(next);
			}
		}
		
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(fanList.contains(now)) continue; //팬이 있는 길은 건너뛴다
			
			if(adjList[now].isEmpty()) { //마지막 정점에 도달
				//여기까지 팬을 만나지 않은 것이므로 플래그 체크
				flag = true;
				return;
			}
			
			for(int next : adjList[now]) {
				if(!visited[next]) {
					queue.add(next);
					visited[next] = true;
				}
			}
		}
	}
}
