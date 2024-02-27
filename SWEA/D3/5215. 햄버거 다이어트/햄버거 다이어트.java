import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//햄버거의 선호도 = 조합된 재료들의 맛에 대한 점수의 합
	//입력 : 재료수, 제한 칼로리
	//재료 맛점수, 재료 칼로리
	//출력 : 제한 칼로리 이하 조합 중에서 가장 맛 점수가 높은 햄버거의 점수
	
	static int N; //재료 개수
	static boolean[] sel; //재료의 사용 유무 넣/ 안넣
	static int[] score; //맛점수
	static int[] cal; //칼로리
	static int best; //제한 칼로리 이하에서 맛점수 가장 높은 것
	static int L; //제한 칼로리
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//재료수 N, 제한칼로리 L
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			sel = new boolean[N];
			score = new int[N];
			cal = new int[N];
			
			//N개 재료의 맛점수, 칼로리 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			
			//최고 점수 저장
			best = 0;
			//부분집합 만들어서 맛점수, 칼로리 계산해서 best 구하기
			powerset(0);


			//출력
			System.out.printf("#%d %d\n", tc, best);
		}//테스트케이스 끝
		
	}
	//부분집합 -> 넣는다 / 안넣는다 두가지 경우
	public static void powerset(int idx) {
		//base case
		if(idx == N) { //마지막까지 고려 완
			int scoreSum = 0;
			int calSum = 0;
			for(int i = 0; i < N; i++) {
				if(sel[i]) { //선택된 재료
					scoreSum += score[i];
					calSum += cal[i];
				}
			}
			//제한 칼로리 이하면, 맛점수 best 갱신
			if(calSum <= L) {
				best = Math.max(best, scoreSum);
			}
			return;
		}
		
		//recursive case
		//골랐다
		sel[idx] = true;
		powerset(idx + 1);
			
		//안골랐다
		sel[idx] = false;
		powerset(idx + 1);
		
	}

}
