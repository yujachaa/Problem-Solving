import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	// 검사 통과하는 최소 약품 투입 횟수 구하라

	static int T, D, W, K;
	static int[][] film;
	static int min;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("보호필름.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 두께 3~13 => 3^13 -> 가능
			W = Integer.parseInt(st.nextToken()); // 가로 1~20
			K = Integer.parseInt(st.nextToken()); // 합격기준 1~D

			film = new int[D][W]; // 0:A특성 , 1:B특성
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 필름 입력 완

			A = new int[W];
			B = new int[W];
			Arrays.fill(B, 1);

			min = Integer.MAX_VALUE;
			injection(0, 0);
			
			System.out.printf("#%d %d\n", tc, min);

		} // tc
	} // main

	// 약품 주입 메서드
	// row: 현재 행, cnt:약물주입횟수
	private static void injection(int row, int cnt) {
		// 기저조건
		if (Pass()) { // 통과 기준 만족하면
			min = Math.min(min, cnt); // 최솟값 갱신하고 끝
			return;
		}

		if (row == D) { // 마지막 행 까지 약품 주입 결정 했으면,
			if (Pass())
				min = Math.min(min, cnt); // 최솟값 갱신하고 끝
			return;
		}

		// 재귀부분
		
		//현재 행을 딥카피해서 저장해놓기
//		int[] tmp = film[row].clone();
		int[] tmp = film[row];
		
		// 안 넣는 경우
		injection(row + 1, cnt);
		// A 약물 주사
		film[row] = A;
		injection(row + 1, cnt + 1);
		// B 약물 주사
		film[row] = B;
		injection(row + 1, cnt + 1);
		
		//원상복구
		film[row] = tmp;
		

	}

	private static boolean Pass() {
		// 열순회해서 모든 열이 조건 만족하면 true
		for (int col = 0; col < W; col++) {
			int cnt = 1; //첫번째 자리는 일치한다고 생각하고 1부터 시작
			for (int row = 1; row < D; row++) {
				if (film[row - 1][col] == film[row][col]) {
					cnt++;

					if (cnt >= K)
						break; // 다음 열 확인
				} else {
					cnt = 1;
				}
			}
			if (cnt < K) // 합격기준 불통이면 false
				return false;
		}
		return true;
	}
}
