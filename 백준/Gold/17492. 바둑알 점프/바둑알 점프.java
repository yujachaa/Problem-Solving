import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * Q. 바둑알 점프
	 *  가로, 세로, 대각선으로 인접한 바둑알을 점프할 수 있다
	 *  점프했을 때 뛰어넘은 바둑알은 없어진다
	 *  더이상 뛰어넘을 바둑알이 없으면 움직일 수 없다
	 *  0:빈칸, 1:벽, 2:바둑알
	 *  벽은 뛰어넘을 수 없다
	 *  
	 *  바둑알 점프를 하여 바둑알을 1개만 남길 수 있다면 Possible
	 * 	불가능하면 Impossible 출력
	 * 
	 * 1. 바둑판 돌면서 8방 탐색
	 * -> 못발견 : 못움직이는 바둑돌 카운트
	 * 2. 발견 : 같은 방향으로 한칸 더 가서 갈 수 있는지 확인
	 * -> 못간다 -> 다시 1번으로 돌아가서 마저 8방 탐색
	 * 3. 갈 수 있다 : 그자리로 옮기고, 뛰어넘은 돌, 원래 돌 없앰
	 * 4. 옮긴 자리에서 또 8방 탐색~ => 8방탐색에 돌X or 
	 * 5. 바둑판 다 돌고 나면 한번 더 돌면서 
	 */
	static int N;
//	static int[][] board;
	
	//8방 델타배열 : 우, 우하, 하, 좌하, 좌, 좌상, 상, 우상
	static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
	
//	static int ; //주변 바둑돌 찾았을 때 그 방향 저장
//	static int count; //주변에 돌 없는 경우 카운트
	static int stone;//현재 바둑돌개수
	static boolean possible;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		stone = 0;
		possible = false;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) {//바둑돌 개수 세기
					stone++;
				}
			}
		}
		
		for(int r = 1; r < N - 1; r++) {
			for(int c = 1; c < N - 1; c++) {
				if(board[r][c] == 2) {
					//바둑알 찾으면, 바둑판 깊은복사해서 넣기
					int[][] tmp = new int[N][N];
					for(int i = 0; i < N; i++) {
						tmp[i] = board[i].clone();
					}
					jump(r,c, tmp, stone);
				}
			}
		}
		if(possible) {
			System.out.println("Possible");
		} else {
			System.out.println("Impossible");			
		}
	}
	//r,c:현재위치, dir:탐색,이동방향
	private static void jump(int r, int c, int[][] board, int stone) {
		//기저조건
//		boolean findNear = false;
		if(stone == 1) {
			possible = true;
			return;
		}
		//System.out.println(stone);
		//지금위치에서 할 일
		//주변 바둑돌 있는지 8방 탐색
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			//벽이거나, 빈칸이면 skip
			if(board[nr][nc] != 2) continue;
			
			//바둑돌 찾으면, 그 방향으로 한번 더 찾기
//			findNear = true;
			int nnr = nr + dr[d];
			int nnc = nc + dc[d];
			
			if(board[nnr][nnc] == 0) { //점프할 수 있으면, 돌 옮기기
				board[nnr][nnc] = 2;
				board[nr][nc] = 0;
				board[r][c] = 0;
				
				for(int i = 1; i < N - 1; i++) {
					for(int j = 1; j < N - 1; j++) {
						if(board[i][j] == 2) {
							jump(i, j, board, stone - 1);							
						}
					}
				}
				
				board[nnr][nnc] = 0;
				board[nr][nc] = 2;
				board[r][c] = 2;
			}
		}
//		if(!findNear) {
//			//인접한 돌이 없는 경우
//			count++;
//		}
	}
}
