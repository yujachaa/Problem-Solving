import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//daldi -> 5 dal -> 6 go -> 8
	//daldida -> 1 n -> 2
	//10회는 고정값
	//N < 2^k 가 되는 최소 k를 구해서 k - 1을 더함

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int k = 0;
		while(Math.pow(2, k) <= N) {
			k++;
		}
		System.out.println(9 + k);
	}
}
