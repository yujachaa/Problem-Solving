import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N = 1000000; // 백만
	static int[] nums = new int[N];
	static int[] tmp = new int[N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 숫자 백만개 입력
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

//		mergeSort(0, nums.length - 1);
		quickSort(0, nums.length - 1);

		System.out.println(nums[500000]);
	}
	
	//퀵정렬
	private static void quickSort(int left, int right) {
		if(left < right) {
			int pivot = partition(left,right);
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
	}
	//피봇 정하기! - 호어 파티션
	static int partition(int left, int right) {
		//일단 피봇을 제일 왼쪽 값으로 정함!
		int pivot = nums[left];
		int L = left + 1;
		int R = right;

		//LR이 역전되지 않은 동안 반복
		while(L <= R) {
			//L : 피봇보다 큰 값 찾기
			while(L <= R && nums[L] <= pivot) {
				L++;
			}
			//R : 피봇보다 작은 값 찾기
			while(nums[R] > pivot) {
				R--;
			}
			//LR 비교해서 R이 더 크면 스왑
			if(L < R) {
				int tmp = nums[L];
				nums[L] = nums[R];
				nums[R] = tmp;
			}
		}
		
		//R위치 = 피봇 위치
		nums[left] = nums[R];
		nums[R] = pivot;
		
		return R;
	}

	// 병합정렬
	static void mergeSort(int left, int right) {
		// left : 구간 왼쪽끝 / right : 구간 오른쪽끝
		if (left < right) { // 반 나눠서 각각 정렬하고, 병합
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}

	// 왼쪽반, 오른쪽반 정렬되어 있는 것을 합친다!
	static void merge(int left, int mid, int right) {
		int L = left; // 왼쪽 구간에서 넣을 숫자 가리키는 포인터
		int R = mid + 1; // 오른쪽 구간에서 넣을 숫자 가리키는 포인터
		int idx = left; // 비교해서 담을 원소를 원래 배열에 넣는 자리

		while (L <= mid && R <= right) {// 두 포인터가 모두 범위 안에 있는 동안,
			if (nums[L] <= nums[R]) { // 더 작은것 비교
				tmp[idx++] = nums[L++];
			} else {
				tmp[idx++] = nums[R++];
			}
		}
		// 왼쪽에 남은게 있다
		if (L <= mid) { // 남은거 다털기
			for (int i = L; i <= mid; i++) {
				tmp[idx++] = nums[i];
			}
		}
		// 오른쪽에 남은게 있다
		else { // 남은거 다털기
			for (int i = R; i <= right; i++) {
				tmp[idx++] = nums[i];
			}
		}

		// 원본배열에 덮어쓰기
		for (int i = left; i <= right; i++) {
			nums[i] = tmp[i];
		}
	}
}
