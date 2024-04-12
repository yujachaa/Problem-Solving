class Solution {
    public long solution(int a, int b) {
        int inA = Math.min(a,b);
        int inB = Math.max(a,b);
        long answer = 0;
        for(int i = inA; i<=inB ; i++){
            answer += i;
        }
        return answer;
    }
}