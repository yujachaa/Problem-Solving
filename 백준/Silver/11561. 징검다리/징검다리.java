import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++){
            long N = sc.nextLong();
            long start = 0;
            long end = (long)Math.pow(10, 9); //N = 10^16 -> K의 최대 10^9

            long answer = 0;
            //k(k+1)/2 <= N 인 최대 k 찾기
            //이분탐색
            while(start <= end){
                long mid = (start + end) / 2;
                long sum = mid * (mid + 1) / 2;
                if(sum <= N){
                    //최대값 갱신
                    answer = Math.max(answer, mid);
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }
}
