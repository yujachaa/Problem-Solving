import java.io.*;
import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[] copyInfo;
    static int[][] copyEdges;
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        copyInfo = info;
        copyEdges = edges;
        
        //dfs + 백트래킹
        //노드번호, 양의 수, 늑대의 수 확인
        //양의 수 <= 늑대 수 return
        //양의 수 max 갱신
        //꼭 현재 노드의 자식만 확인하는 것이 아니라
        //모든 노드 중 부모는 방문하고, 방문하지 않은 자식노드들을 모두 확인하면 된다
        //말단에서 뻗어나가는 느낌
        
        boolean[] initVisited = new boolean[info.length];
        initVisited[0] = true;
        answer = 0;
        dfs(1, 0, initVisited);
        
        return answer;
    }
    
    public static void dfs(int sheep, int wolf, boolean[] visited){
        //늑대가 잡아먹는 경우
        if(sheep <= wolf) return;
        
        //양의 수 최댓값 갱신
        answer = Math.max(answer, sheep);
        
        //방문한 부모의 방문하지 않은 자식들을 모두 확인
        for(int[] edge : copyEdges){
            int parent = edge[0];
            int child = edge[1];
            
            if(visited[parent] && !visited[child]){
                visited[child] = true;
                if(copyInfo[child] == 1){ //늑대
                    dfs(sheep, wolf + 1, visited);
                } else{ //양
                    dfs(sheep + 1, wolf, visited);
                }
                visited[child] = false;
            }
        }
    }
}