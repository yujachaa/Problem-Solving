class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int len = photo.length;
        int[] answer = new int[len];
        
        for(int i = 0; i < len; i++){
            String[] person = photo[i];
            for(int j = 0; j < person.length; j++){
                for(int k = 0; k < name.length; k++){
                    if(person[j].equals(name[k])){
                        answer[i] += yearning[k];
                    }
                }
            }
        }
        
        return answer;
    }
}