import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R, count;
    static List<Integer>[] adjList;
    static boolean[] visited;
    static int[] order;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        } //인접리스트 초기화
        visited = new boolean[N + 1];
        order = new int[N + 1];

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        } //간선 정보 입력 끝

        for(int i = 1; i <= N; i++){
            Collections.sort(adjList[i]);
        }

        count = 1;
        dfs(R);

        for(int i = 1; i <= N; i++) {
            System.out.println(order[i]);
        }
    }

    private static void dfs(int r) {
        visited[r] = true;
        order[r] = count++;

        for(int i = 0; i < adjList[r].size(); i++) {
            int v = adjList[r].get(i);
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
}
