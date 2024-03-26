
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String line = br.readLine();

			int M = N / 4; // 숫자 하나의 길이

			Set<Integer> numSet = new TreeSet<>(Comparator.reverseOrder()); // 만들어진 숫자를 저장할 셋(중복제거, 내림차순 정렬)
			for (int i = 0; i < M; i++) {
				for (int j = i; j < N; j = j + M) { // M개씩 잘라서 숫자로 만들기
					String num16 = "";
					for (int k = j; k < j + M; k++) { 
						num16 += line.charAt(k % N);
					}
					int num10 = Integer.parseInt(num16,16); //16진수 숫자를 10진수로 변환
					numSet.add(num10);
				}
			}
			ArrayList<Integer> numList = new ArrayList<>(numSet); //중간에 있는 숫자를 출력하기위해 list로 변환
			int result = numList.get(K - 1); //K번째로 큰 수 뽑기

			System.out.printf("#%d %d\n", tc, result);

		} // 테스트케이스
	}
}
