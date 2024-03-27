import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	// 크루스칼로 구현

	static class Edge implements Comparable<Edge> {
		int A, B;
		double W;

		public Edge(int a, int b, double w) {
			A = a;
			B = b;
			W = w;
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}

		@Override
		public int compareTo(Edge e) { // 가중치 오름차순으로 정렬
			return Double.compare(this.W, e.W);
		}
	}

	static int T, N;
	static double E;
	static int[][] island; // 섬의 좌표 저장할 배열
	static Edge[] edges; // 간선 저장할 배열
	static int[] p; //집합 대표 저장할 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			island = new int[N][N]; // 섬좌표 0:x, 1:y

			// 섬좌표 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine()); // 가중치 계산할 때 쓰는 환경 부담 세율

			// 모든 간선을 구하자
			edges = new Edge[N * (N - 1) / 2];
			int idx = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					long x1 = island[i][0];
					long y1 = island[i][1];
					long x2 = island[j][0];
					long y2 = island[j][1];
					// 섬 2개의 좌표
					long L = ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
					double weight = E * L;
					
					edges[idx++] = new Edge(i, j, weight);// 무향 그래프
				}
			}
			
			//1. 간선 가중치 기준으로 정렬
			Arrays.sort(edges);
			
//			for(Edge e : edges) {
//				System.out.println(e.toString());
//			}
			
			
			//2. 간선 N - 1개 고르기 (사이클 생기지 않도록)
			p = new int[N];
			for(int i = 0; i < N; i++) {
				p[i] = i;
			}
			int pick = 0;
			double answer = 0;
			for(int i = 0; i < edges.length; i++) {
				//i번째 간선을 뽑아서 두 정점의 집합 대표를 확인!
				int pa = findset(edges[i].A);
				int pb = findset(edges[i].B);
				//사이클 생기지 않게 유니온
				if(pa != pb) {
					union(pa, pb);
					pick++; //간선 골랐으니까 카운트
					answer += edges[i].W;
				}
				if(pick == N - 1) break;
			}
			System.out.printf("#%d %.0f\n", tc, answer);
		}//테스트케이스 끝
	}

	static void union(int pa, int pb) {
		//a에 냅다 b넣어버리기
		p[pb] = pa;
	}

	static int findset(int a) {
		//패스 컴프레션
		if(a != p[a])
			p[a] = findset(p[a]);
		return p[a];
	}
}
