import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	//어떤 말을 움직일지 완전탐색
	//백트래킹
	//얻을 수 있는 점수의 최댓값 출력
	
	static int[][] map = { //윷놀이 판 경로 4개
			{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
			{10,13,16,19,25,30,35,40},
			{0, 20,22,24,25,30,35,40},
			{30,28,27,26,25,30,35,40}
	};
//	static int[][] horse; //말 번호, index(i,j)
	static int[] dice; //주사위 숫자 10개
	static int answer;
	static boolean[] finish;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		dice = new int[10];
		for(int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		} //입력 끝
		
		int[][] tmp = new int[4][2]; //말 위치 초기화
		finish = new boolean[4]; //말이 도착지에 도착했는지 체크하는 배열
		answer = Integer.MIN_VALUE;
		backTracking(0, 0, tmp);
		
		System.out.println(answer);
		
	}
	
	public static void backTracking(int count, int total, int[][] horse) {
		//주사위 10개 턴 모두 끝냄
		if(count == 10) {
			answer = Math.max(answer, total);
			return;
		}
		
		//4개의 말 중 하나 이동
		for(int i = 0; i < 4; i++) {
			if(finish[i]) continue; //도착지에 도착한 말은 스킵
			//말 하나 선택
			int[] now = horse[i]; //지금 위치
			int nowRoute = now[0];
			int nowIdx = now[1];
			int nextRoute = now[0];
			int nextIdx = now[1];
			nextIdx += dice[count]; //다음 위치로
			if(nextRoute == 0) { //파란 칸에 도착한 경우 경로 바꿔줌
				if(nextIdx == 5) {
					nextRoute = 1;
					nextIdx = 0;
				} else if (nextIdx == 10) {
					nextRoute = 2;
					nextIdx = 1;
				} else if (nextIdx == 15) {
					nextRoute = 3;
					nextIdx = 0;
				}
			}
			//무조건 갈 수 있는 경우 : 도착지에 도착하는 경우 
			if(nextIdx >= map[nextRoute].length) {
				finish[i] = true;
				horse[i][0] = -1;
				horse[i][1] = -1;
				backTracking(count + 1, total, horse);
				finish[i] = false;
				horse[i][0] = nowRoute;
				horse[i][1] = nowIdx; //되돌리기
				continue;
			}
			
			//갈 수 없는 경우: 가려는 곳에 말이 있음
			if(confirm(i, nextRoute, nextIdx, horse)) { //갈 수 있는 곳이면 말 놓기
				horse[i][0] = nextRoute;
				horse[i][1] = nextIdx;
				backTracking(count + 1, total + map[nextRoute][nextIdx], horse);
				horse[i][0] = nowRoute;
				horse[i][1] = nowIdx; //되돌리기
			}
		}
	}
	
	public static boolean confirm(int horseNum, int route, int idx, int[][] horse) {
		//다른 말과 위치가 같은지 확인
		//파란색 루트인 경우 map1,2,3과 겹치는지 확인해야함
		for(int i = 0; i < 4; i++) {
			if(i == horseNum || finish[i]) continue; //현재 말 또는 도착지에 도착한 말
			int[] other = horse[i];
			int otherRoute = other[0];
			int otherIdx = other[1];
			
			//같은 루트
			if(route == otherRoute) {
				if(idx == otherIdx) return false; 
			}
			//둘 다 파란색 루트
			else if (route != 0 && otherRoute != 0) {
				//인덱스 4 이상 (25, 30, 35, 40) 겹치는지 확인
				if(idx >= 4 && otherIdx >= 4) {
					if(idx == otherIdx) return false;
				}
			}
			//40인 경우
			if (map[route][idx] == 40 && map[otherRoute][otherIdx] == 40) return false;
		}
		return true;
	}

}
