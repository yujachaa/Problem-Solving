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
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] graph = new ArrayList[N+1];
		int[] times = new int[N+1]; //걸리는 시간 배열
		int[] degree = new int[N+1]; //선행해야 하는 작업의 개수
		int[] result = new int[N+1]; //각 작업의 최소 완료 시간
		
		for(int n=1; n<=N; n++) {
			graph[n] = new ArrayList<>();
		}
		
		for(int n=1; n<=N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[n] = time; //걸리는 시간 저장
			
			int cnt = Integer.parseInt(st.nextToken());
			if (cnt != 0) { //선행해야 하는 작업이 있으면
				for (int c=0; c<cnt; c++) {
					int first = Integer.parseInt(st.nextToken());
					graph[first].add(n); //선행해야 하는 작업에 해당 노드 저장
					degree[n]++; //선행해야 하는 작업의 수 증가
				}
			}
			
		}
		result = times.clone(); //시간 배열 복사하기
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int n=1; n<=N; n++) {
			if(degree[n] == 0) queue.offer(n); //선행해야 하는 작업이 없는 노드 바로 큐에 넣기
		}
        
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			for (int i=0; i<graph[tmp].size(); i++) {
				int next = graph[tmp].get(i);
				degree[next]--;
				//next 작업의 현재 최소 완료 시간(result[next])과 
				//tmp 작업 후에 next 작업을 시작했을 때의 완료 시간(result[tmp] + times[next]) 중 
				//더 큰 값을 선택 
				//왜? -> next 작업은 그것에 선행하는 모든 작업들이 완료된 후에만 시작할 수 있기 때문에, 
				//선행 작업들 중 가장 늦게 완료되는 시간에 맞추어 next 작업의 완료 시간 계산
				result[next] = Math.max(result[next], result[tmp] + times[next]);
				if(degree[next] == 0) queue.offer(next);
			}
		}
		int minTime = 0;
		for(int i=0; i<result.length; i++) {
			minTime = Math.max(minTime, result[i]);
		}
		System.out.println(minTime);
	}
}