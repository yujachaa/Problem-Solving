import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//이진탐색?
	//수를 정렬
	//첫번째 수는 제외 (더 작은 수가 없어서 더해서 만들 수 없음)
	//두번째 수~N번째 수를 제외한 리스트를 만들고
	//리스트의 첫번째 수(x) + {N번째 수 - (리스트의 첫번째 수)}(y) = N번째 수 가 되는 y를 찾기 (이진탐색)

	static int N;
	static int[] num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		int count = 0;
		
		for(int i = N - 1; i >= 0; i--) {
			int target = num[i]; // 만들려는 수
			boolean find = false;
			
			for(int j = N - 1; j >= 0; j--) { // 첫 번 째 더할 수
				if(j == i) continue; // 만들려는 수는 제외
				
				int x = num[j];
				// 이진 탐색 범위를 제한
				if(binarySearch(i, j, 0, j - 1, target - x)) {
					find = true;
					break;
				}
			}
			if(find) {
				count++;
			}
		}
		
		System.out.println(count);
		
	}

	//tIdx : 만들려는 수의 인덱스, xIdx : 이미 고른 수의 인덱스
	private static boolean binarySearch(int tIdx, int xIdx, int start, int end, int target) {
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(num[mid] > target) {
				end = mid - 1;
			} else if (num[mid] == target && mid != tIdx && mid != xIdx) {
				return true;
			} else {
				start = mid + 1;
			}
		}
		return false; //못 찾은 경우
	}

}
