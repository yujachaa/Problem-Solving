import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//3차원격자
	//위, 아래, 왼, 오, 앞, 뒤 6방향에 익은 토마토 있으면 익는다
	//토마토들이 모두 익는 최소 일수 출력
	//저장될 때 부터 모든 토마토가 익어있는 상태 : 0 출력
	//토마토가 모두 익지 못하는 상황 : -1 출력
	//M,N,H ~100
	
	//익은 토마토로부터 뻗어나가서 최소 일수를 알아내는 것 -> bfs
	//익은 토마토 좌표를 큐에 추가
	
	static int M, N, H;
	static int[][][] box;
	static Queue<int[]> queue;
	
	//상, 하, 좌, 우, 앞, 뒤
	static int[] dr = {0, 0, 0, 0, -1, 1};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {1, -1, 0, 0, 0, 0};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[N][M][H]; // row, col, height 
		queue = new LinkedList<>();
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					int tomato = Integer.parseInt(st.nextToken());
					//1:익은 토마토, 0:익지 않은 토마토, -1:빈칸
					if(tomato == 1) {
						//익은 토마토 큐에 추가
						queue.add(new int[] {j, k, i}); // row, col, height
					}
					box[j][k][i] = tomato;
				}
			}
		}//입력 완
		
		bfs();
		System.out.println(checkDay());
		
	}
	
	private static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int row = now[0];
			int col = now[1];
			int height = now[2];
			
			//이 토마토 주변에 익지 않은 토마토가 있으면 큐에 추가하고, 현재값 + 1로 갱신
			for(int d = 0; d < 6; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				int nh = height + dh[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H) continue;
				if(box[nr][nc][nh] == 0) { //익지 않은 토마토
					queue.add(new int[] {nr, nc, nh});
					box[nr][nc][nh] = box[row][col][height] + 1;
				}
			}
		}
	} //bfs 끝
	
	private static int checkDay() {
		int maxDay = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(box[j][k][i] == 0) { //아직 익지 않은 토마토가 있는 경우 -> -1 출력
						return -1;
					}
					maxDay = Math.max(maxDay, box[j][k][i]);
				}
			}
		}
		if(maxDay == 1) { //모든 토마토가 처음부터 익어있는 경우 -> 0 출력
			return 0;
		} else {
			return maxDay - 1;
		}
	}


	//익지않은 토마토 주변에 모두 빈칸인지 확인 -> 모두 빈칸이면 true
	private static boolean checkTomato(int[] tomato) {
		
		return true;
	}

}
