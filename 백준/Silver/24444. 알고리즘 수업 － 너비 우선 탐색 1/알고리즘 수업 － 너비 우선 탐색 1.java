import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	//정점의 수 ~100000
	//인접리스트로 하자
	static int N,M,R;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int[] order; //각 정점의 방문 순서를 저장하는 배열
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //정점의 수
		M = Integer.parseInt(st.nextToken()); //간선의 수
		R = Integer.parseInt(st.nextToken()); //시작 정점
		
		//간선정보 입력
		adjList = new ArrayList[N + 1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u); //간선추가
		}
		
		visited = new boolean[N + 1];
		order = new int[N + 1];
		for(int i = 1;i <= N; i++) {
			Collections.sort(adjList[i]);
		}
		bfs(R);
		
		for(int i = 1; i<=N; i++) {
			System.out.println(order[i]);
		}
	}

	//start : 시작 정점
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int num = 1; 
		//시작정점 방문 표시
		visited[start] = true;
		queue.add(start); //시작정점 큐에 넣기
		
		while(!queue.isEmpty()){
			int now = queue.poll();
			order[now] = num++;
			for(int next : adjList[now]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		
	}

}
