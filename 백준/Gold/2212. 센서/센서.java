import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	//제일 간격이 큰 구간부터 제외하기
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		//K >= N 인 경우 최솟값 0 출력
		if(K >= N) {
			System.out.println(0);
			return;
		}
		
		int[] sensor = new int[N]; //센서 좌표 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		//센서 좌표 오름차순 정렬
		Arrays.sort(sensor);
		int[] diff = new int[N - 1]; // 좌표끼리 차이값 배열
		for(int i = 0; i < N - 1; i++) {
			diff[i] = Math.abs(sensor[i] - sensor[i + 1]);
		}
		
		//차이값 오름차순 정렬
		Arrays.sort(diff);
		
		//차이값 큰 것 부터 K - 1 개 제외하고 총합 구함
		int answer = 0;
		for(int i = 0; i < N - K; i++) {
			answer += diff[i];
		}
		
		System.out.println(answer);
		
	}
}
