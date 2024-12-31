import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//최솟값 출력
	//bfs
	//시간복잡도
	//공간복잡도
	
	//이동 가능 방향 8가지
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2}; //우상2, 우하2, 좌하2, 좌상2
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	
	static int I, min, startR, startC, targetR, targetC;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			I = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			targetR = Integer.parseInt(st.nextToken());
			targetC = Integer.parseInt(st.nextToken());
			
			min = Integer.MAX_VALUE;
			visited = new boolean[I][I]; //0~I-1
			
			bfs();
			
			System.out.println(min);
			
		}//테스트케이스 끝

	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		//시작위치 넣기
		queue.add(new int[] {startR, startC, 0}); //r좌표, c좌표, depth
		
		visited[startR][startC] = true;
		
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			//목표지점에 도착하면 min 갱신
			if(now[0] == targetR && now[1] == targetC) {
				min = now[2];
				return;
			}
			
			//현재 위치에서 갈 수 있는 곳 모두 queue에 넣기
			for(int d = 0; d < 8; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if(nr < 0 || nr >= I || nc < 0 || nc >= I) continue; //범위 밖
				if(visited[nr][nc]) continue; //방문한 곳
				queue.add(new int[] {nr, nc, now[2] + 1});
				visited[nr][nc] = true;
			}
		}
	}
}
