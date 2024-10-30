class Solution {
    public long solution(int n, int[] times) {
        
        //x분에 끝났다는 것은 x/심사시간 을 모두 더하면 n명이 되는 것
        //가능한 정답 범위 1~10^18(최대로 걸리는 경우)
        //정답 범위에서 빠르게 답을 찾아내려면 이분탐색 이용
        
        long start = 1;
        long end = (long)Math.pow(10, 18);
        long answer = 0;

        while(start <= end){
            long mid = start + (end - start) / 2;
            long count = 0;
            for(int i = 0; i < times.length; i++){
                count += mid / times[i];
                if(count > n) break;
            }
            // System.out.println(count);
            
            if(count >= n){ //더 많은 사람을 심사할 수 있는 시간 -> 작은 수 탐색
                end = mid - 1;
                answer = mid;
            }else{ //모든 사람을 심사할 수 없음 -> 더 큰 수 탐색
                start = mid + 1;
            }
        }
        
        
        
        return answer;
    }
}