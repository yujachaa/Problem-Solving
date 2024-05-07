import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//뒤에서부터 확인해서 최댓값 갱신
	//최댓값보다 작으면 구매
	//최대 이익 출력
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] prices = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			long answer = 0;
			for(int i = N - 1; i >= 0; i--) { //뒤에서부터 탐색
				if(max < prices[i]) {
					max = prices[i];
				} else {
					answer += max - prices[i];
				}
			}
			
			
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

}
