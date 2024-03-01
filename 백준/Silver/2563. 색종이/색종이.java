import java.util.Scanner;

public class Main {
	/*Q. 색종이
	 * #문제요약
	 * 	100*100 흰색도화지에 10*10 검은색종이 평행하게 붙임
	 *  색종이 여러장 붙인 후 검은 영역 넓이를 구하기
	 *  
	 *  #메인접근법
	 *  	1. 색종이 올릴때 그 영역에 1 채우기
	 *  	2. 흰색도화지 완전탐색해서 1인 부분 넒이 카운트
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] board = new int[100][100];
		
		int N = sc.nextInt();
		
		int count = 0;

		//N개의 색종이 받으면서 색종이 영역 1로 채움
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int j = x; j < x + 10; j++) {
				for(int k = y; k < y + 10; k++) {
					board[j][k] = 1;
				}
			}
			
		}
		
		//검은색 영역 카운트
		for(int j = 0; j < 100; j++) {
			for(int k = 0; k < 100; k++) {
				if(board[j][k] == 1) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

}
