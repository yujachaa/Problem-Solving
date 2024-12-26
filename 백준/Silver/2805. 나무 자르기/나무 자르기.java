import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	//가져가려는 나무길이 M ~20억
	//나무의 수 N ~1천만
	//나무 높이의 합은 M보다 크거나 같다 -> long 타입
	//한 나무의 높이 ~10억
	//적어도 M미터의 나무를 집에 가져가기 위해 설정할 수 있는 높이의 최댓값
	//1. 나무 높이 내림차순 정렬 2. 제일 높은 나무 - 1 부터 자르면서 나무높이 합 구해서 최댓값 구하기
	//시간초과 -> 이분탐색
	//나무 높이 합이 M보다 클 수 있다!

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //나무의 수
		int M = Integer.parseInt(st.nextToken()); //가져가려는 나무의 길이
		
		List<Integer> trees = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			trees.add(Integer.parseInt(st.nextToken()));
		}//입력 완
		
		Collections.sort(trees); //오름차순 정렬
		
		//이분탐색
		int answer = binarySearch(0, trees.get(N-1), trees, M);
		
		System.out.println(answer);	
	}

	private static int binarySearch(int L, int R, List<Integer> trees, int M) {
		int answer = 0; //가능한 최대 높이 저장
		
		while (L <= R) {
			int mid = L + (R - L) / 2;
			long sum = 0;
			
			for(int t : trees) {
				if(t > mid) {
					sum+= t - mid;
				}
			}
			
			if(sum >= M) {
				answer = mid; //조건을 만족하면 현재 높이를 저장
				L = mid + 1; // 더 큰 높이 탐색
			} else {
				R = mid - 1; //더 낮은 높이 탐색
			}
		}
		
		return answer;
	}

}
