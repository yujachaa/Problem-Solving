import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>(); //입차시간 저장
        SortedMap<String, Integer> totalTime = new TreeMap<>(); //차 번호별 누적 주차 시간 저장
        
        for(int i = 0; i < records.length; i++){
            //주차기록 하나씩 보면서 확인
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            String carNum = st.nextToken();
            String type = st.nextToken();
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            if(type.equals("IN")){ //입차인 경우 시간 저장
                inTime.put(carNum, hour*60 + minute);
            }else{ //출차인 경우 -> 시간 계산
                int before = inTime.remove(carNum);
                int count = hour*60 + minute - before;
                if(totalTime.containsKey(carNum)){
                    totalTime.put(carNum, totalTime.get(carNum) + count);
                }else {
                    totalTime.put(carNum, count);
                }
            }
        }
        //레코드 다 확인했는데 출차 없는 것 계산하기
        for(String key : inTime.keySet()){
            int sum = 1439 - inTime.get(key);
            if(totalTime.containsKey(key)){
                totalTime.put(key, totalTime.get(key) + sum);
            }else {
                totalTime.put(key, sum);
            }
        }

        //출력
        int[] answer = new int[totalTime.size()];
        int i = 0;
        for(String key : totalTime.keySet()){
            int time = totalTime.get(key);
            // System.out.println(time);
            if(time <= fees[0]){ //기본시간 이하인 경우
                answer[i] = fees[1]; 
            }else{ //기본시간 초과인 경우
                double div = (double)(time - fees[0]) / fees[2];
                answer[i] = fees[1] + (int)Math.ceil(div) * fees[3]; 
            }
            i++;
        }
        
        
        
        return answer;
    }
}