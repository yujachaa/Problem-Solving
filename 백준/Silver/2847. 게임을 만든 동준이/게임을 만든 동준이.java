import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//N번째 레벨 점수부터 역순으로 탐색해서
	//다음레벨 점수보다 큰 경우에 더 작아지게 줄인다
	//그리디? 완전탐색?
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N];
		
		for(int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		for(int i = N - 1; i >= 1; i--) {
			if(scores[i] <= scores[i - 1]) {
				answer += scores[i - 1] - scores[i] + 1;
				scores[i - 1] -=  scores[i - 1] - scores[i] + 1;
			}
		}
		System.out.println(answer);
	}
}
