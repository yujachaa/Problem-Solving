import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	//1. 일단 전체 배얼에서 암호코드 추출하기 - 8개 숫자 (가로 56비트)
	//2. 7비트씩 나눠서 숫자 확인
	//3. 암호코드 규칙에 맞는지 확인
	//4. 맞으면 각 자리수 합 출력 / 틀리면 0 출력
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//배열 크기 입력
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][M];
			
			//배열 입력
			for(int i = 0; i < N; i++) {
				String tmp = br.readLine();
				for(int j = 0; j < M; j++) {
					arr[i][j] = tmp.charAt(j) - '0';
				}
			}
			
			//암호 코드 받는 배열
			int[] code = new int[8];
			
			//암호 코드 시작 위치
			int idx_row = 0;
			int idx_col = 0;
			
			//암호가 시작된다는 플래그
			boolean start = false;
			
			//배열에서 7개씩 받아서 0~9 규칙에 해당하는지 체크
			out: for(int i = 0; i < N; i++) {
				for(int j = 0; j < M - 7; j++) {
					//7개 받아서 확인하기 위한 문자열 만들기
					StringBuilder sb = new StringBuilder();
					for(int k = j; k < j + 7; k++) {
						//숫자 7개 받음
						sb.append(arr[i][k]);
					}
					String tmp = sb.toString();
					int tmpNumber = isNumber(tmp);
					if(tmpNumber == -1) { //숫자가 아니라면 계속 탐색
						continue;
					} else { //숫자가 시작되면 다음 코드들의 7비트 중 첫자리가 0인지 확인
                        code[0] = tmpNumber;
						start = true;
						for(int x = j + 7; x < j + 56; x = x + 7) {
							if(arr[i][x] != 0) {
								start = false;
								break;
							}
						}
						if(start) {
							//다음 코드들이 모두 숫자면 인덱스 저장하고 빠져나감
							idx_row = i;
							idx_col = j + 7;
							break out;
						}
					}
				}
			} //7개 확인 끝
			
			//암호 첫자리 빼고 7개 * 7비트 받아서 숫자로 바꿈
			for(int i = 1; i < 8; i++) {
				StringBuilder sb = new StringBuilder();
				//7비트 받아서 
				for(int c = idx_col + (i-1) * 7; c < idx_col + (i-1) * 7 + 7; c++) {
					sb.append(arr[idx_row][c]);
				}
				//숫자 확인 
				String tmp = sb.toString();
				code[i] = isNumber(tmp);
			}//8개 코드 모두 숫자로 바꿈
			
			//암호코드 규칙에 맞는지 확인
			int odd = 0;
			int even = 0;
			int sum = 0;
			
			for(int i = 0; i < 8; i++) {
				if((i + 1) % 2 == 0) { //짝수
					even += code[i];
				} else {
					odd += code[i];					
				}
			}
			if((odd * 3 + even) % 10 == 0) { //10의 배수가 되는 경우
				sum = even + odd;
			}
			//출력
			System.out.printf("#%d %d\n", tc, sum);
		} //테스트케이스 끝
	} //메인 끝
	
	static int isNumber(String tmp) {
		int result = -1;
		switch(tmp) {
		case "0001101": // 0
			result = 0;
			break;
		case "0011001": // 1
			result = 1;
			break;
		case "0010011": // 2
			result = 2;
			break;
		case "0111101": // 3
			result = 3;
			break;
		case "0100011": // 4
			result = 4;
			break;
		case "0110001": // 5
			result = 5;
			break;
		case "0101111": // 6
			result = 6;
			break;
		case "0111011": // 7
			result = 7;
			break;
		case "0110111": // 8
			result = 8;
			break;
		case "0001011": // 9
			result = 9;
			break;						
		} //숫자인지 확인 끝		
		return result;
	}
}