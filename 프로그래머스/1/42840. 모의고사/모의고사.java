import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] oneArr = {1, 2, 3, 4, 5};
        int[] twoArr = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] threeArr = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] cnt = new int[3];
        
        for (int i=0; i<answers.length; i++) {
            if (answers[i] == oneArr[i % oneArr.length]) {
                cnt[0]++;
            }
            if (answers[i] == twoArr[i % twoArr.length]) {
                cnt[1]++;
            }
            if (answers[i] == threeArr[i % threeArr.length]) {
                cnt[2]++;
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<cnt.length; i++) {
            if (max < cnt[i]) {
                max = cnt[i];
            }
        }
        List<Integer> sol = new ArrayList<>();
        
        for(int i=0; i<cnt.length; i++) {
            if (max == cnt[i]) {
                sol.add(i+1);
            }
        }
        
        int[] ans = new int[sol.size()];
        for(int i=0; i<sol.size(); i++) {
            ans[i] = sol.get(i);
        }
        
        return ans;
        
    }
}