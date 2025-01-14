import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        
        int[] answer = new int[queries.length];
        
        //<문자길이, 단어리스트> 로 이루어진 맵
        Map<Integer, ArrayList<String>> data = new HashMap<>();
        Map<Integer, ArrayList<String>> reverseData = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            String reverseWord = new StringBuilder(word).reverse().toString();
            int wordLen = word.length();
            
            if(!data.containsKey(wordLen)){ //맵에 처음 넣는 경우 (문자길이 기준)
                ArrayList<String> in = new ArrayList<>();
                in.add(word);
                ArrayList<String> reverseIn = new ArrayList<>();
                reverseIn.add(reverseWord);
                data.put(wordLen, in);
                reverseData.put(wordLen, reverseIn);
            } else{ //기존 리스트에 추가
                data.get(wordLen).add(word);
                reverseData.get(wordLen).add(reverseWord);
            }
        }
        
        //맵에 저장된 word 오름차순 정렬
        for(int len : data.keySet()){
            List<String> list = data.get(len);
            List<String> reverseList = reverseData.get(len); 
            //얕은복사라서 원본리스트 정렬됨
            Collections.sort(list);
            Collections.sort(reverseList);
        }
        
        
        
        
        for(int i = 0; i < queries.length; i++){
            String query = queries[i];
            int len = query.length();
            
            if(!data.containsKey(len)){ //문자길이에 해당하는 단어가 없는 경우
                answer[i] = 0;
                continue;
            }
            
            List<String> wordList;
            if(query.charAt(0) == '?'){ 
                //와일드카드 앞에 있는 경우 -> 거꾸로된 문자 데이터 사용
                wordList = reverseData.get(len);
                query = new StringBuilder(query).reverse().toString();
            } else {
                wordList = data.get(len);
            }
            
            //이진탐색
            int start = 0;
            int end = 0;
            String minQuery = query.replace('?', 'a'); //g??? -> gaaa
            String maxQuery = query.replace('?', 'z'); //g??? -> gzzz
            start = binarySearch(wordList, minQuery);
            end = binarySearch(wordList, maxQuery);
            
            answer[i] = end - start;      
            
        }
        
        return answer;
    }
    
    static int binarySearch(List<String> wordList, String query){
        int start = 0; 
        int end = wordList.size();
        
        while(start < end){
            int mid = (start + end) / 2;
            if(query.compareTo(wordList.get(mid)) >= 0){
                //query >= word 
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start;
    }
}