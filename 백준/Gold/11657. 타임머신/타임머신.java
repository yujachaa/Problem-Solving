import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Bus {
	int start;
	int end;
	int time;
	
	public Bus(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}

public class Main {
	//1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간 출력
	//어떤 도시로 가는 과정에서 
	//시간을 무한히 되돌릴 수 있다면 -1 출력
	//아니라면 1번 도시 출발 2~N번 도시로 가는 가장 빠른 시간 한줄씩 출력
	//해당 도시로 가는 경로가 없다면 -1 출력
	
	//음수 가중치 -> 벨만 포드
	//음수 순환 확인

	static int N, M;
	static Bus[] edge;
	static long[] dist;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //도시의 개수 ~500
		M = Integer.parseInt(st.nextToken()); //버스 노선 개수 ~6000
		
		edge = new Bus[M];
		dist = new long[N + 1];
		Arrays.fill(dist, INF); //최단 거리 테이블 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edge[i] = new Bus(a, b, c);
		}
		
		if(bellmanFord(1)) { //음수 순환 존재하면 -1 출력
			System.out.println(-1);
			return;
		}
		
		// 2~N번 도시로 가는 최단 경로 출력
		for(int i = 2; i <= N; i++) {
			if(dist[i] == INF) { //도달할 수 없는 경우 -1 출력
				System.out.println(-1);
			}else {
				System.out.println(dist[i]);
			}
		}
	}

	private static boolean bellmanFord(int start) {
		dist[start] = 0;
		
		//N번 반복
		for(int i = 1; i < N + 1; i++) {
			// 매 반복마다 모든 간선 확인
			for(int j = 0; j < M; j++) {
				Bus now = edge[j];
				
				if(dist[now.start] == INF) continue;
				//현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우
				if(dist[now.end] > dist[now.start] + now.time) {
					dist[now.end] = dist[now.start] + now.time;
					
					//N번째 라운드에서 값이 갱신된다면 음수 순환 존재
					if(i == N)
						return true;
				}
			}
		}
		return false;
	}

}
