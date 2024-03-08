
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited;
	static List<List<Integer>> tree;
	static int N;
	static long result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList<>();
		visited = new boolean[N + 1];
		
		//빈 리스트 추가해줌 -> 0번은 더미
		for(int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			//부모자식 구분없이 그냥 서로 연결 -> 어차피 루트 노드는 1번!
			tree.get(v1).add(v2);
			tree.get(v2).add(v1);

		} //간선 정보 입력
		
		//리프노드에서 루트노드까지 이동하는 횟수 카운트
		result = 0;
		
		dfs(1, 0);
		
		//모든 이동 횟수를 다 카운트 했을 때, 이동횟수가 짝수번 -> 성원이 Lose
		if(result % 2 == 0) { //리프노드가 짝수개 -> No
			System.out.println("No");
		} else { //리프노드 홀수개 -> Yes
			System.out.println("Yes");
		}

	}
	//
	private static void dfs(int node, int cnt) { 
		visited[node] = true; //방문처리
		
		//기저조건
		if(node != 1 && tree.get(node).size() == 1) { //리프노드 = 루트노드가 아니고, 연결된게 하나뿐임
			result += cnt;
			return;
		}
		
		//재귀
		//현재 노드 node의 모든 연결된 노드를 순회하면서 카운트
		for(int i = 0; i < tree.get(node).size() ;i++) {
			int linkNode = tree.get(node).get(i);
			
			if(!visited[linkNode]) { //방문한적 없는 노드라면
				visited[linkNode] = true; //방문표시하고 
				dfs(linkNode, cnt + 1); //한단계 깊어짐!!
			}
		}
		
	}
}
