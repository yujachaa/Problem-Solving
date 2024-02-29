import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	// N*N 보드의 i번째 행에 퀸을 놓는다
	// 그 퀸을 기준으로 못놓는 곳을 표시한다
	// 다음 행에 퀸을 놓는다 -> 못놓는 곳인지 확인
	// 못놓는 곳이면 -> 다른 자리 찾기
	// 놓는 곳이면 놓고, 다음행으로
	static int N;
	static int Count;
	static int qCount;
	static int[][] board; // 퀸을 놓는 보드 -1 : 못놓는 곳 / 0 : 비어있음 / 1 : 퀸 놓음
	static boolean[][] visited; // 놓았던 곳인지 표시
	// 델타배열 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			board = new int[N][N];
			visited = new boolean[N][N];
			Count = 0;
			qCount = 0;

			queen(0, board);

			System.out.printf("#%d %d\n", tc, Count);
		} // 테스트케이스 끝

	}

	// row : 현재 퀸을 놓는 행 번호 / board : 안되는 위치 표시한 맵
	public static void queen(int row, int[][] board) {
		// 한 행에 퀸을
		// 마지막 열까지 확인했다!
		if (row == N) {
			if(qCount == N)
				Count++; // 퀸 N개 모두 놓았으면 경우의수 카운트
			return;
		}
		
		//N개의 열을 돌면서 놨었는지 확인
		for(int c = 0; c < N; c++) {
			//여기 퀸 놨었어!!!! - 스킵
			if(visited[row][c]) continue;
			//여기 퀸 못놓는 곳이야!! - 스킵
			if(board[row][c] == -1) continue;
			
			//처음 놓아봐요...
			visited[row][c] = true;
			//안되는 위치 적은 보드 만들기 - 깊은복사!!!!!!!!!!!!
			int[][] nboard = new int[N][N];
			nboard = Arrays.copyOf(board, N);
			for(int i = 0; i < N; i++) {
				nboard[i] = Arrays.copyOf(board[i], N);
			}
//			for(int[] tmp : nboard) {
//				System.out.println(Arrays.toString(tmp));
//			}
			qCount++;
			// 현재 위치 기준, 8방향 못놓는다고 표시
			for (int d = 0; d < 8; d++) {
				int nr = row + dr[d];
				int nc = c + dc[d];
				while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if(nboard[nr][nc] == 0)
						nboard[nr][nc] = -1;
					nr = nr + dr[d];
					nc = nc + dc[d];
				}
			}
			nboard[row][c] = 1;
//			for(int[] tmp : nboard) {
//				System.out.println(Arrays.toString(tmp));
//			}
			// 다음 행에 퀸을 놓아요..
			queen(row + 1, nboard);
			// 안놓았다고 표시하기
			visited[row][c] = false;
			qCount--;
		}
		
	}
}
