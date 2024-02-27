import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	// N개의 식재료
	// N/2개로 나누어 두개의 요리
	// A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 배분
	// i번째 재료+ j번째 재료 => 시너지 Sij
	// 음식의 맛 = 식재료들로부터 발생하는 시너지들의 합

	// 재료들을 N/2로 나누는 경우
	static int N;
	static int R;
	static List<int[]> foodCombi;
	static int[] sel;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 식재료 개수 N
			N = Integer.parseInt(br.readLine());
			R = N / 2;
			foodCombi = new ArrayList<>();
			sel = new int[R];

			int[][] synergy = new int[N][N];

			// 시너지값 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}


			combi(0, 0); // 재료 반씩 나누는 조합 찾음
			
			//최솟값 저장
			int min = Integer.MAX_VALUE;

			// 모든 조합 확인
			for (int c = 0; c < foodCombi.size() / 2; c++) {
				//A재료목록, B재료목록
				int[] AList = foodCombi.get(c);
				int[] BList = foodCombi.get(foodCombi.size() - 1 - c);
				
				//A시너지합 구하기
				int ASum = 0;
				for(int i = 0; i < AList.length - 1; i++) {
					for(int j = i + 1; j < AList.length; j++) {
						ASum += synergy[AList[i]][AList[j]] + synergy[AList[j]][AList[i]];
					}
				}
				//B시너지합 구하기
				int BSum = 0;
				for(int i = 0; i < BList.length - 1; i++) {
					for(int j = i + 1; j < BList.length; j++) {
						BSum += synergy[BList[i]][BList[j]] + synergy[BList[j]][BList[i]];
					}
				}
				//차이 절댓값
				int diff = Math.abs(ASum - BSum);
				min = Math.min(min, diff);
			}
			System.out.printf("#%d %d\n", tc, min);

		} // 테스트케이스 끝
	}

	// nCr 뽑는 메서드
	public static void combi(int idx, int sidx) {
		// base case
		if (sidx == R) {// 다뽑으면 저장하고 리턴
			//deepcopy
			int[] tmp = new int[R];
			for(int i = 0; i < R; i++) {
				tmp[i] = sel[i];
			}
			foodCombi.add(tmp);
			return;
		}
		if (idx == N) // 모두 고려해봤으면 리턴
			return;

		// recursive case
		// 뽑는 경우
		sel[sidx] = idx;
		combi(idx + 1, sidx + 1);
		// 안뽑는 경우
		combi(idx + 1, sidx);
	}

}
