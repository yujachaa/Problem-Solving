import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//N ~1000000000000 -> int 범위 넘음 ->long
	//생성 : 1마리 생성
	//복제 : 0~k 마리 추가 (현재 고양이 수 k)
	//정확히 N마리 만들도록 하는 최소 횟수
	//복제는 최대 2배 가능 -> 2^k으로 증가
	//2^k >= N 이 되는 k의 최솟값 구하기
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		int answer = 0;
		
		while(Math.pow(2, answer) < N) {
			answer++;
		}
		
		if(N == 0) {
			System.out.println(0);
		} else {
			System.out.println(answer + 1);
		}
	}
}
