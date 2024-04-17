import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int[] result;
	static int[] nums;
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		
		result = new int[M]; //수열을 저장할 배열
		
		visited = new boolean[N]; //중복 확인 배열
		nums = new int[N];
		
		for(int i=0; i<N; i++) {
			nums[i] = i+1;
		}
		
		perm(0);
	}
	
	public static void perm(int idx) {
		//종료 조건
		if (idx == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M; i++) {
				sb.append(result[i] + " ");
			}
			System.out.println(sb);
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			result[idx] = nums[i];
			perm(idx+1);
			visited[i] = false;
		}
	}


}
