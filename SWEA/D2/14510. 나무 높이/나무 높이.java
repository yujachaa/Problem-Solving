import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("나무높이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 나무개수 2~100
			Integer[] trees = new Integer[N]; // 나무 높이 1~120

			int tallest = 0; // 처음에 가장 높은 키 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				tallest = Math.max(tallest, trees[i]);
			} // 나무높이 입력 완

			// 가장 높은 키와의 차이를 담는 리스트 -> 홀수, 짝수로 나눔!!
			List<Integer> odd = new LinkedList<>();
			List<Integer> even = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				int diff = tallest - trees[i];
				if (diff != 0) {
					if (diff % 2 == 0)
						even.add(diff);
					else
						odd.add(diff);
				}
			} // 키차이를 모두 리스트에 담음

			// 오름차순 정렬
			Collections.sort(even);
			Collections.sort(odd);

			int day = 0;
			// 두개의 리스트가 모두 빌때까지
			while (!even.isEmpty() || !odd.isEmpty()) {
				day++;
				boolean flag = false; // 물 줬는지 안줬는지 표시하는 플래그

				if (day % 2 == 0) { // 짝수 날
					if (!even.isEmpty() && !flag) { // 물을 안 줬다면, 짝수 리스트 먼저 확인
						int tmp = even.get(0) - 2; // 첫번째 요소 꺼내서 2 빼기!
						even.remove(0);

						if (tmp != 0) // 0이아니면 다시 리스트에 넣기 (짝수 - 2 = 짝수)
							even.add(tmp);

						flag = true; // 물 줬으니까 바꾸기
					}
					if (!odd.isEmpty() && !flag) {// 위에서 물을 안줬다면, 홀수 리스트 확인
						// 홀수 리스트 확인 -> 1보다 큰 수가 있으면 거기서 2 빼기!
						for (int i = 0; i < odd.size(); i++) {
							if (odd.get(i) > 1) {
								int tmp = odd.get(i) - 2;
								odd.remove(i);
								if(tmp != 0)
									odd.add(tmp);
								flag = true; // 물 줬으니까 바꾸기
								break;
							}
						}
					}
				} // 짝수날

				else { // 홀수 날
					if (!odd.isEmpty() && !flag) { // 물 안줬으면, 홀수 리스트 먼저 확인
						int tmp = odd.get(0) - 1;
						odd.remove(0);
						if (tmp != 0)
							even.add(tmp); // 홀수에서 1뺐으니 짝수!!
						flag = true; // 물 줬으니까 바꾸기
					}
					if (!even.isEmpty() && !flag) { // 위에서 물 안줬다면, 짝수 리스트 확인
						// 짝수 리스트 확인 -> 짝수가 2개 이상 남았으면 물 줘도 됨!!
						if (even.size() >= 2) {
							int tmp = even.get(0) - 1;
							even.remove(0);
							if (tmp != 0)
								odd.add(tmp); // 짝수에서 1뺐으니 홀수!!
							flag = true; // 물 줬으니까 바꾸기

						} else { //짝수가 1개 남았다 -> 그럼 2 이상일때만 뺄 수 있음
							if(even.get(0) > 2) {
								int tmp = even.get(0) - 1;
								even.remove(0);
								odd.add(tmp); // 짝수에서 1뺐으니 홀수!!
								flag = true;
							}
						}
					}
				} // 홀수날
			} // 모두 비었다!

			System.out.printf("#%d %d\n", tc, day);

		} // tc
	}// main
}
