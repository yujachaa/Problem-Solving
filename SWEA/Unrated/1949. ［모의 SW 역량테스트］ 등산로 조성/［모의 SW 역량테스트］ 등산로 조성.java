
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    // 가장 높은 봉우리에서 시작
    // 높은곳 -> 낮은 곳으로 가로 or 세로로 연결
    // 높이 같은곳 X, 대각선 X
    // 딱 한 곳 정해서 최대 K만큼 깎을 수 있다

    // 가장 긴 등산로 찾아서 "길이" 출력
    static int T, N, K;
    static int[][] map;
    static int max;
    static List<int[]> startList;
    static boolean[][] visited;
    static boolean flag;

    // 상하좌우 델타배열
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 3~8
            K = Integer.parseInt(st.nextToken()); // 1~5

            map = new int[N][N];
            int highest = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 가장 높은 봉우리 찾기
                    if (map[i][j] > highest)
                        highest = map[i][j];
                }
            } // 지도정보 입력 완

            startList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (highest == map[i][j])
                        startList.add(new int[] { i, j });
                }
            } // 가장 높은 봉우리 좌표 저장
            
            max = 0;
            // 가장 높은 봉우리를 시작으로 dfs
            for (int i = 0; i < startList.size(); i++) {
                visited = new boolean[N][N];
                visited[startList.get(i)[0]][startList.get(i)[1]] = true;
                flag = true; // 깎을 수 있는 기회 있음!
                
                dfs(startList.get(i)[0], startList.get(i)[1], 1);
            }
            
            System.out.printf("#%d %d\n", tc, max);

        } // tc
    }// main

    // row,col:현재위치, len:등산로길이
    private static void dfs(int row, int col, int len) {
        
        if(len > max)
            max = len; //max값 갱신
        
        for(int d = 0; d < 4; d++) {
            int nr = row + dr[d];
            int nc = col + dc[d];
            
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            
            if(!visited[nr][nc]) { //가보지 않은 곳 중에서,
            	int now = map[row][col];
            	int next = map[nr][nc];
            	
            	if(next < now) { //지금 높이보다 낮은 곳으로 이동
            		visited[nr][nc] = true;
            		dfs(nr, nc, len + 1);
            		visited[nr][nc] = false;            		
            	} else if (flag) {
            		//깎을 수 있는 상태일 때
            		for(int k = 1; k <= K; k++) {
            			if(next - k < now) { //k만큼 깎고나서 지금높이보다 낮아지는지 체크
            				flag = false;
            				map[nr][nc] -= k;
            				visited[nr][nc] = true;
            				dfs(nr, nc, len + 1);
            				visited[nr][nc] = false;
            				map[nr][nc] += k;
            				flag = true;
            			}
            		}
            	}
            }
        }
    }//dfs
}
