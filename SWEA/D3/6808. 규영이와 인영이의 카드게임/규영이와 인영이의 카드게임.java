import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	//규영이 카드 9장 주어지고, 카드 내는 순서 고정
	//인영이 카드 내는 9!가지 경우 중 규영이가 이기는 경우의 수, 지는 경우의 수 출력
	//362880
	
	static int[] gyuCard;
	static int[] inCard;
	static int[] drawCard;
	static boolean[] visited;
	static int winCount, loseCount;
	static int N = 9;
	static int total = 362880;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스 
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			boolean[] cards = new boolean[N * 2 + 1];
			gyuCard = new int[N];
			inCard = new int[N];
			drawCard = new int[N];
			visited = new boolean[N];
			winCount = 0;
			loseCount = 0;
			
			//규영이 카드 입력
			for(int i = 0; i < N; i++) {
				gyuCard[i] = Integer.parseInt(st.nextToken());
				cards[gyuCard[i]] = true;
			}
			
			//인영이 카드 목록
			for(int i = 1, idx = 0; i <= N * 2; i++) {
				if(!cards[i]) inCard[idx++] = i;
			}
			
			cardPerm(0);
			
			System.out.println("#" + tc +" "+  winCount +" "+ loseCount);
		} //테스트케이스
		
	}
	//인영이 카드 내는 순서 뽑기
	//idx : 현재 카드 내는 순서
	public static void cardPerm(int idx) {
		if(idx == N) { //다 뽑았어
			//승패확인하고 카운트 하고 종료
			int gyuSum = 0;
			int inSum = 0;
			
			for(int i = 0; i < N; i++) {
				if(gyuCard[i] > drawCard[i]) { //높은 카드 나오면 합 점수
					gyuSum += gyuCard[i] + drawCard[i];
				} else {
					inSum += gyuCard[i] + drawCard[i];
				}
			}
			
			if(gyuSum > inSum) {
				winCount++;
			} else if (gyuSum < inSum) {
				loseCount++;
			}
			return;
		}
		
		//N개 카드 돌면서 냈는지 확인
		for(int i = 0; i < N; i++) {
			//뽑았던 카드~ 스킵
			if(visited[i]) continue;
			
			//안뽑은 카드에용
			visited[i] = true;
			drawCard[idx] = inCard[i];
			cardPerm(idx + 1);
			visited[i] = false;
		}
		
	}
}
