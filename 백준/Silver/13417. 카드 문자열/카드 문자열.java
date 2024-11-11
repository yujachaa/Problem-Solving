import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            String answer = st.nextToken();
            for(int i = 1; i < N; i++){
                String s = st.nextToken();
                if(answer.charAt(0) >= s.charAt(0)){
                    String newS = s.charAt(0) + answer;
                    answer = newS;
                } else{
                    String newS = answer + s.charAt(0);
                    answer = newS;
                }
            }
            System.out.println(answer);
        }
    }
}
