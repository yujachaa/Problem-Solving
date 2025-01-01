import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//단방향 그래프
	//시간복잡도
	//공간복잡도
	//최단 거리가 K인 모든 도시들의 번호를 오름차순 출력
	//출발 도시로 가는 최단 거리는 항상 0
	//최단 거리 -> bfs
	//찾으면 리스트에 추가
	//리스트 길이가 0이면 -1 출력
	//시간초과 -> bfs 코드 수정
	
	static int N, M, K, X;
	static List<Integer> minList;
	static List<Integer>[] adjList;
	static boolean[] visited;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //도시의 개수 ~300000
		M = Integer.parseInt(st.nextToken()); //도로의 개수 ~1000000
		K = Integer.parseInt(st.nextToken()); //거리정보 ~300000
		X = Integer.parseInt(st.nextToken()); //출발도시 ~N
		
		minList = new ArrayList<>(); //마지막에 오름차순 정렬
		adjList = new ArrayList[N + 1]; //1~N
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adjList[A].add(B); //단방향 그래프
		}
		

		visited = new boolean[N + 1]; //1~N
		bfs();
		
		if(minList.size() == 0) {
			System.out.println(-1);
		}else {
			Collections.sort(minList);
			for(int num : minList) {
				System.out.println(num);
			}
		}
		
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		//시작위치, depth 넣기
		queue.add(new int[] {X, 0});
		visited[X] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			if(now[1] == K) {
				minList.add(now[0]);
			}
			if(now[1] > K) {
				return;
			}
			
			for(int next : adjList[now[0]]) {
				if(!visited[next]) {
					queue.add(new int[] {next, now[1] + 1});
					visited[next] = true;
				}
			}
		}
	}

}
