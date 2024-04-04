import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int T, N;

	static int[] coin = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 }; // 돈의 종류 저장
	static int[] coinCnt;

	// 최소 개수로 거슬러줄 때, 각 종류의 돈이 몇개씩 필요한지 출력

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); //거슬러 줄 돈의 금액
			
			coinCnt = new int[coin.length];
			for(int i = 0; i < coin.length; i++) {
				if(N >= coin[i]) {
					coinCnt[i] = N / coin[i];
					N = N % coin[i];
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int x : coinCnt) {
				sb.append(x + " ");
			}
			
			System.out.printf("#%d\n%s\n", tc, sb);
			
		}//테스트케이스 끝
	}

}
