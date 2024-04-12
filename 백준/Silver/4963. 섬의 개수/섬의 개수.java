import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, result;
	static int[][] map;
	static boolean[][] check;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			result = 0;
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[m][n];
			check = new boolean[m][n];

			if (n == 0 && m == 0) {
				break;
			}

			if (n == 1 && m == 1) {
				sb.append(Integer.parseInt(br.readLine()) + "\n");
			} 
			else {
				for (int r = 0; r < m; r++) {
					st = new StringTokenizer(br.readLine());
					for (int c = 0; c < n; c++) {
						map[r][c] = Integer.parseInt(st.nextToken());
					}
				}
				for (int r = 0; r < m; r++) {
					for (int c = 0; c < n; c++) {
						if (!check[r][c] && map[r][c] == 1) {
							BFS(r , c);
							result++;
						}
					}
				}
				sb.append(result + "\n");
			}
		}
		System.out.println(sb.toString().trim());
	}

	private static void BFS(int r, int c) {
		check[r][c] = true;
		
		queue.offer(new int[] {r ,c});
		while (!queue.isEmpty()) {

			int[] curr = queue.poll();

			for (int i = 0; i < 8; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				if (nr >= 0 && nr < m && nc >= 0 && nc < n && !check[nr][nc] && map[nr][nc]== 1) {
					check[nr][nc] = true;
					queue.offer(new int[] { nr, nc });
				}
			}
		}
	}
}
