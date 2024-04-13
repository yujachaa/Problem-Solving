import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 손해보지 않으면서 가장 많은 집들에게 제공하는 영역을 찾고, 그 때의 집들의 수를 구하라

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("홈방범.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 도시 크기 5~20
			int M = Integer.parseInt(st.nextToken()); // 한집 지불비용 1~10

			int[][] map = new int[N][N];
			int total_cnt = 0; // 전체 집 개수 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						total_cnt++;
				}
			} // 도시정보 입력 완

			int K = 0;
			int k_cost = 0;
			int max = 0;
			
			
			//현재 맵에서 최대로 받을 수 있는 비용은 
			//전체 집 개수 * M 이므로
			//서비스 영역은 이 비용을 넘지 않는 동안만 커질 수 있다
			while(k_cost <= total_cnt*M) { //최대 비용 이내에서,
				K++;
				k_cost = K * K + (K - 1) * (K - 1);
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						//r,c를 중심으로 서비스 영역 탐색해서 집 개수 세기
						int cnt = 0;
						for(int i = 0; i < N; i++) {
							for(int j = 0; j < N; j++) {
								//거리가 K 미안인 곳 탐색
								if(Math.abs(r-i) + Math.abs(c-j) < K) {
									if(map[i][j] == 1)
										cnt++;
								}
							}
						}//마름모 탐색 완
						int cost = cnt * M - k_cost;
						
						if(cost >= 0)
							max = Math.max(max, cnt); //최대 집개수 갱신
					}
				}//완탐 끝
			} //모든 가능한 K 확인 완
			
			System.out.printf("#%d %d\n", tc, max);
			
			
		} // tc

	} // main

}
