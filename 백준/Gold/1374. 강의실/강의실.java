import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	//필요한 최소 강의실의 수를 출력
	
	//N ~100000
	
	//시작시간, 종료 시간 ~10억
	
	//강의 리스트 시작시간 순서로 정렬 (시작시간이 같은경우 종료시간 순)
	//우선순위 큐에 시작시간 순서대로 종료 시간 push (우선순위 큐는 push 시 정렬이 된다)
	//우선순위 큐 peek 해서 다음 강의 시작시간보다 작으면 pop 하고 종료시간 push
	//다음 강의 시작시간보다 크면 push
	//우선순위 큐 사이즈의 max 값 = 필요한 강의실 수
	
	static class Lecture implements Comparable<Lecture> {
		int number;
		int start;
		int end;
		
		public Lecture(int number, int start, int end) {
			this.number = number;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Lecture o) {
			if(this.start == o.start)
				return this.end - o.end; //시작시간이 같은 경우 종료 시간 오름차순 정렬
			return this.start - o.start; //시작시간 오름차순 정렬
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Lecture[] input = new Lecture[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			input[i] = new Lecture(num, start, end);
		}
		
		//강의 시작시간 기준으로 오름차순 정렬
		Arrays.sort(input);
		
		//강의실 종료 시간 리스트
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int max = 1;
		for(int i = 0; i < N; i++) {
			if(pq.isEmpty()) {
				pq.offer(input[i].end);
			}else if(pq.peek() <= input[i].start) {
				pq.poll();
				pq.offer(input[i].end);
			} else {
				pq.offer(input[i].end);
			}
			max = Math.max(max, pq.size());
		}

		System.out.println(max);
	}

}