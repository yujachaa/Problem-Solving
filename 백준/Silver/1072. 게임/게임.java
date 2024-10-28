import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken()); //게임 횟수
        int Y = Integer.parseInt(st.nextToken()); //이긴 게임
        long Z = (long)Y * 100 / X; //승률 (소수점 버림)


        if(Z >= 99){ //Z가 절대 변하지 않는 경우
            System.out.println(-1);
            return;
        }
        long top = Z * X + X - 100 * (long)Y;
        long bottom = 99 - Z;
        long plus = top / bottom;

        if(top % bottom == 0){
            System.out.println(plus);
        }else {
            System.out.println(plus + 1);
        }
    }
}