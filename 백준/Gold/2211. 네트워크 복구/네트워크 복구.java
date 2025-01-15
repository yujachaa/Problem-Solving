import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//양방향 그래프
	//시간초과 -> 다익스트라 알고리즘으로 수정
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int val;
		
		public Edge(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
	
	static int N, M;
	static List<Edge>[] adjList;
	static List<Edge> answer;
	static boolean[] visited;
	static int[] dist;
	static int INF = Integer.MAX_VALUE;
	
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수 ~1000
    	M = Integer.parseInt(st.nextToken()); // 회선 개수
    	
    	adjList = new ArrayList[N + 1]; // 1~N
    	for(int i = 1; i <= N; i++) {
    		adjList[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		
    		adjList[a].add(new Edge(a,b,c));
    		adjList[b].add(new Edge(b,a,c)); //양방향 그래프
    	} //입력 완
    	
    	dist = new int[N + 1]; //최단거리 저장
    	Arrays.fill(dist, INF); //최댓값으로 초기화
    	
    	visited = new boolean[N + 1];
    	answer = new ArrayList<>();
    	dijkstra(1);
    	
    	System.out.println(answer.size());
    	for(Edge e : answer) {
    		System.out.println(e.from +" "+e.to);
    	}
  
    }
    
    public static void dijkstra(int start) {
    	dist[start] = 0; //출발지 거리 
    	visited[start] = true;
    	
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.offer(new Edge(start, start, 0));
    	
    	while(!pq.isEmpty()) {
    		Edge now = pq.poll();
    		
    		if(!visited[now.to]) { //방문체크
    			visited[now.to] = true;
    			answer.add(now);
    		}
    		
    		for(Edge next : adjList[now.to]) { //현재 위치에서 갈 수 있는 경로 확인
    			if(dist[next.to] > dist[now.to] + next.val) { //현재 경로 통해서 가는게 더 작으면 갱신
    				dist[next.to] = dist[now.to] + next.val;
    				pq.offer(new Edge(next.from, next.to, dist[next.to])); //출발지에서 정점 next.to까지 가는 거리 추가
    			}
    		}
    	}
    }
}
