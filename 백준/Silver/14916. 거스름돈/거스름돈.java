import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // DP
    // Bottom-up 방식
    // 거스름돈 동전의 최소 개수를 출력
    // 거슬러 줄 수 없으면 -1 출력

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1); // 초기화: -1로 설정 (처리 불가능한 상태)

        // 기본 초기값 설정
        dp[0] = 0; // 거슬러 줄 돈이 0일 때는 동전이 필요 없음
        if (N >= 2) dp[2] = 1; 
        if (N >= 4) dp[4] = 2; 
        if (N >= 5) dp[5] = 1; 

        // DP 점화식
        for (int i = 6; i <= N; i++) {
            if (dp[i - 2] != -1) {
                dp[i] = dp[i - 2] + 1;
            }
            if (dp[i - 5] != -1) {
                dp[i] = dp[i] == -1 ? dp[i - 5] + 1 : Math.min(dp[i], dp[i - 5] + 1);
            }
        }

        // 결과 출력
        System.out.println(dp[N]);
    }
}
