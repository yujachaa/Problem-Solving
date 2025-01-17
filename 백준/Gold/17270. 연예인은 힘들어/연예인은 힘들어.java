import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	//그래프 최단 거리 찾기
	//양방향 그래프
	//음수가중치 x -> 다익스트라
	
	static class Node implements Comparable<Node> {
		int index;
		int cost;
		
		public Node (int index, int cost){
			this.index = index;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int V, M, J, S;
	static int[] distJ, distS;
	static List<Node>[] adjList;
	static boolean[] visited;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); //정점 개수 ~100
		M = Integer.parseInt(st.nextToken()); //간선 개수 ~1000
		
		adjList = new ArrayList[V + 1]; // 1~V
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()); //1~10000
			
			adjList[a].add(new Node(b, c)); 
			adjList[b].add(new Node(a, c)); //양방향 그래프
		}
		
		st = new StringTokenizer(br.readLine());
		J = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken()); //입력 끝
		
		distJ = new int[V + 1]; // 1~V
		distS = new int[V + 1];
		Arrays.fill(distJ, INF);
		Arrays.fill(distS, INF);
		
		dijkstra(J, distJ);
		dijkstra(S, distS);
		
		int sumMin = INF; //최단거리의 합 구하기
		List<Integer> answer = new ArrayList<>();
		
		for(int i = 1; i <= V; i++) {
			if(i != J && i != S && distJ[i] + distS[i] < sumMin) {
				sumMin = distJ[i] + distS[i];
			}
		} // 조건 1
		for(int i = 1; i <= V; i++) {
			if(i != J && i != S && distJ[i] + distS[i] == sumMin) {
				answer.add(i);
			}
		} // 조건 2
		
		if(answer.isEmpty()) {
			System.out.println(-1);
			return;
		}
		
		// 조건 3
		answer.removeIf(i -> distS[i] < distJ[i]);
		
		int minJ = INF;
		int answerIdx = -1;
		
		for(int i : answer) {
			if(distJ[i] < minJ) {
				minJ = distJ[i];
				answerIdx = i;
			}
		} // 조건 4
		
		System.out.println(answerIdx);
		
	}
	
	static void dijkstra(int start, int[] dist) {
		visited = new boolean[V + 1];
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.index]) continue;

			visited[now.index] = true; //방문체크
			
			//인접한 정점으로 가는 비용 확인
			for(Node next : adjList[now.index]) {
				if(dist[next.index] > dist[now.index] + next.cost) { 
					//현재 정점 거쳐서 가는게 더 빠른 경우 갱신
					dist[next.index] = dist[now.index] + next.cost;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}
}
