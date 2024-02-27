import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//햄버거의 선호도 = 조합된 재료들의 맛에 대한 점수의 합
	//입력 : 재료수, 제한 칼로리
	//재료 맛점수, 재료 칼로리
	//출력 : 제한 칼로리 이하 조합 중에서 가장 맛 점수가 높은 햄버거의 점수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//재료수 N, 제한칼로리 L
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			//재료 N개 입력
			int[] score = new int[N];
			int[] cal = new int[N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			//최고 점수 저장
			int best = 0;
			
			//재료들의 조합의 맛점수 합을 만들어 배열에 저장
			//재료가 N개 -> 1 << N 가지 경우의 수
			int[] combiScore = new int[1<<N];
			int[] combiCal = new int[1<<N];
						
			//비트마스킹으로 만들자 2^N개 확인
			for(int i = 0; i < (1 << N); i++) {
				for(int j = 0; j < N; j++) { //N개의 재료 검사!!
					if((i & (1 << j)) > 0) {
						//비트마스킹 -> 들어있는 재료만 1
						combiScore[i] += score[j];
						combiCal[i] += cal[j];
					}
				}
				//칼로리 제한 이내이면서, 맛점수 최댓값인 것 찾기
				if(combiCal[i] <= L) {
					best = Math.max(best, combiScore[i]);
				}
			}
			//출력
			System.out.printf("#%d %d\n", tc, best);
		}//테스트케이스 끝
	}
}