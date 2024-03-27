
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	/*
	 * Q.수영장
	 */
	static int T, day, month, season, year, min;
	static int[] plan;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			season = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			plan = new int[12]; //이용계획
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}//입력 끝
			
			min = Integer.MAX_VALUE;
			
			dfs(0, 0);
			
			System.out.printf("#%d %d\n", tc, min);
		}//테스트케이스 끝
	}
	
	//idx: 현재 달, total: 요금
	static void dfs(int idx, int total) {
		//기저조건
		if(idx == 12) {
			min = Math.min(total, min); //최솟값 갱신
			return;
		}
		
		//현재 달에서 할 것
		//1일 이용권으로 계산
		dfs(idx+1, total + plan[idx] * day); 
		//1달 이용권으로 계산
		dfs(idx + 1,  total + month);
		//3달 이용권으로 계산
		if(idx + 3 > 12) {
			dfs(12, total + season);
		} else {
			dfs(idx + 3, total + season);
		}
		//1년 이용권으로 계산
		dfs(12, total + year);
	}
}
