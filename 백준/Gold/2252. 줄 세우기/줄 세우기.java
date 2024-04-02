
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adj = new ArrayList[N+1]; //리스트형 배열 생성
		for(int n=1; n<=N; n++) {
			adj[n] = new ArrayList<>();
		}
		int[] degree = new int[N+1]; //진입차수 배열 생성
		
		for(int m=0; m<M; m++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st2.nextToken());
			int B = Integer.parseInt(st2.nextToken());
			adj[A].add(B);
			degree[B]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if (degree[i] == 0) queue.offer(i); //진입차수가 0인 노드 큐에 넣기
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr + " ");
			
			for(int i=0; i<adj[curr].size(); i++) {
				degree[adj[curr].get(i)]--;
				if(degree[adj[curr].get(i)]== 0) queue.offer(adj[curr].get(i));
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
