import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int result = pow(N, M);
//			int result = pow2(N, M);
//			int result = pow3(N, M);

			System.out.printf("#%d %d\n", T, result);
		}
	}

	static int pow(int n, int m) {
		// 기저조건
		if (m == 0)
			return 1;

		// 재귀
		int tmp = pow(n, m / 2);

		// 홀수 짝수 나눠서
		if (m % 2 == 0)
			return tmp * tmp;
		else
			return tmp * tmp * n;
	}

	static int pow2(int n, int m) {
		// 기저조건
		if (m == 0)
			return 1;

		// 재귀
		return n * pow2(n, m - 1);
	}

	static int pow3(int n, int m) {
		// 기저조건
		if (m == 0)
			return 1;

		// 재귀
		int tmp;

		// 홀수 짝수 나눠서
		if (m % 2 == 0) {
			tmp = pow3(n, m / 2);
			return tmp * tmp;
		} else {
			tmp = pow3(n, (m - 1) / 2);
			return tmp * tmp * n;
		}
	}
}
