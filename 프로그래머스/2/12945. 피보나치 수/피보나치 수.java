class Solution {
    public int solution(int n) {
        
        long[] fiboNum = new long[n + 1];
        
        if(n <= 1){
            return n%1234567;
        }
        fiboNum[0] = 0;
        fiboNum[1] = 1;
        
        for(int i = 2; i <= n; i++){
            fiboNum[i] = fiboNum[i-2]%1234567 + fiboNum[i-1]%1234567;
        }        
        return (int)(fiboNum[n]%1234567);
    }
    
}