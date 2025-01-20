import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	//그래프 최단 경로
	//양의 가중치 -> 다익스트라
	//1->v1->v2->N
	//1->v2->v1->N
	
	static class Edge implements Comparable<Edge>{
		int index;
		int val;
		
		public Edge(int index, int val) {
			this.index = index;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
	
	static List<Edge>[] graph;
	static int N, E, v1, v2;
	static boolean[] visited;
	static int[] dist;
	static int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //정점 개수 ~800
		E = Integer.parseInt(st.nextToken()); //간선 개수 ~200000
		
		graph = new ArrayList[N + 1]; //1~N
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()); //가중치 ~1000
			
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c)); //양방향 그래프
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken()); //입력 끝
		
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		int result1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		int result2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		
		System.out.println((result1 >= INF && result2 >= INF) ? -1 : Math.min(result1, result2));
	}
	
	static int dijkstra(int start, int end) {
		Arrays.fill(visited, false);
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(!visited[now.index]) {
				visited[now.index] = true;
			}
			
			//현재 정점에 연결된 곳 확인
			for(Edge next : graph[now.index]) {
				if(dist[next.index] > dist[now.index] + next.val) {
					dist[next.index] = dist[now.index] + next.val;
					pq.offer(new Edge(next.index, dist[next.index]));
				}
			}
		}
		return dist[end];
	}
}
