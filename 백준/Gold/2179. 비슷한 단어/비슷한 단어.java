import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            String s= br.readLine();
            if(!list.contains(s))
                list.add(s);
        }

        int resultStr1=0;
        int resultStr2=0;
        int maxCount=0;
        for(int i=0;i<n;i++) {
            String str1 = list.get(i);
            for(int j=i+1;j<n;j++) {
                int count=0;
                String str2=list.get(j);
                int size=Math.min(str1.length(),str2.length());
                for(int z=0;z<size;z++) {
                    if(str1.charAt(z)==str2.charAt(z)) count++;
                    else break;
                }
                if(count>maxCount) {
                    resultStr1=i;
                    resultStr2=j;
                    maxCount=count;
                }
            }
        }

        System.out.println(list.get(resultStr1));
        System.out.println(list.get(resultStr2));
    }
}