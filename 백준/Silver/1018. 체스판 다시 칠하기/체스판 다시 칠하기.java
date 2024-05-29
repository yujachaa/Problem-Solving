import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //8~50
		int M = Integer.parseInt(st.nextToken()); //8~50
		char[][] board = new char[N][M];
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = tmp.charAt(j);
			}
		}//입력 완
		
		//다시 칠해야 하는 정사각형 개수의 최솟값
		int min = N * M;
		
		char[][] chess1 = {
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				};
		char[][] chess2 = {
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
				{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
				{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
		};
		
		//체스판 무늬와 비교해서 다시 칠할 개수 세기
		for(int i = 0; i <= N - 8; i++ ) {
			for(int j = 0; j <= M - 8; j++) {
				int cnt1 = 0;
				int cnt2 = 0;
				
				//8*8 체스판 비교
				for(int k = i; k < i + 8; k++) {
					for(int l = j; l < j + 8; l++) {
						if(board[k][l] != chess1[k-i][l-j]) {
							cnt1++;
						}
						if(board[k][l] != chess2[k-i][l-j]) {
							cnt2++;
						}
					}
				}
				//최솟값 갱신
				min = Math.min(min, cnt1);
				min = Math.min(min, cnt2);
			}
		}
		
		System.out.println(min);
	}
}
