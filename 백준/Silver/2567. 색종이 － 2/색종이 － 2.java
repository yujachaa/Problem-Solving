import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[102][102];
		
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			for(int j = r; j < r + 10; j++) {
				for(int k = c; k < c + 10; k++) {
					board[j + 1][k + 1] = 1; //도화지 영역 1로 채우기
				}
			}
		} //도화지 N개 끝
		
		//4방 탐색 델타배열 상하좌우
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		//둘레길이 저장하는 변수
		int length = 0;
		
		
		//도화지가 놓이는 영역 모두 순회하면서 1찾으면 4방 탐색
		for(int r = 1; r <= 100; r++) {
			for(int c = 1; c <= 100; c++) {
				if(board[r][c] == 1) {
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(board[nr][nc] == 0) {
							length++;
						}
					}
				}
			}
		}
		
		System.out.println(length);
		
	}
}
