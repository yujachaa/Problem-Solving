import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, max; // 한변의 길이
	static int[][] map;
	static boolean[] dessert; // 담은 디저트의 숫자 체크
	static boolean[][] visited; // 방문체크
	static boolean flag; // 한바퀴 돌고 되돌아온건지 체크

	// 이동방향 델타배열
	// 우하, 좌하, 좌상, 우상
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 디저트 정보 입력 끝

			max = -1;

			for (int r = 0; r < N - 2; r++) { // 범위 내에서 탐색
				for (int c = 1; c < N - 1; c++) {
					dessert = new boolean[101]; //1~100
					visited = new boolean[N][N];
					dessert[map[r][c]] = true; // 현재위치 숫자 체크
					visited[r][c] = true; //현재위치 방문처리
					dfs(r, c, r, c, 1, 0);
				}
			} // 모든 위치에서 확인 끝

			System.out.printf("#%d %d\n", tc, max);
		} // tc

	}// main

	// sr, sc:시작위치, row, col:현재위치, dir:이동방향
	private static void dfs(int sr, int sc, int row, int col, int cnt, int dir) {

		// 재귀 부분
		for (int d = dir; d < 4; d++) {
			// 같은방향으로 가거나 다음 방향으로 가기
			int nr = row + dr[d];
			int nc = col + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue; // 범위 벗어나면 스킵
			
			if(cnt >= 4 && nr == sr && nc == sc) {
				// 다음 위치가 한바퀴 돌고 다시 시작위치로 돌아온 거라면, 최댓값 갱신
				max = Math.max(max, cnt);
				return;
			}
			
			if(!visited[nr][nc] && !dessert[map[nr][nc]]) {
				//방문하지 않은 곳이고, 담지 않은 숫자면 dfs
				visited[nr][nc] = true;
				dessert[map[nr][nc]] = true;
				dfs(sr, sc, nr, nc, cnt + 1, d);
				//다음방향도 탐색해야되니까 안간척하기
				dessert[map[nr][nc]] = false;
				visited[nr][nc] = false;
			}
		}
	}

}
