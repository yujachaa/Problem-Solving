//입력
//길이가 1~500000 이하인 1차원 정수 배열 a
//a의 모든 수는 0 이상 a길이 미만

//로직
//배열의 길이 / 2 = n -> 정답이 될 수 있는 최댓값
//교집합 원소가 될 수 있는 숫자를 찾아내야됨
//1. 빈도수 배열 만들어서 각 숫자의 개수 세기
//2. 빈도 가장 많은 숫자부터 스타수열 가능한지 확인
//  -숫자 양 옆을 체크해서 집합으로 만들기
//  -숫자 양 옆의 숫자가 같은숫자면 불가능, 집합으로 사용된 곳이면 불가능
//  -가능한 숫자 개수 세서 저장
//3. 빈도 그 다음으로 많은 숫자 <= 가능한 숫자 개수 -> 정답 출력
//4. 아니라면 2번 반복
//5. 빈도 수 1이 되면 0 출력

//출력
//a의 모든 부분 수열 중에서 "가장 길이가 긴" 스타 수열의 길이를 출력
//모든 부분 수열 중에서 스타 수열이 없다면 0 출력

function solution(a) {
    //스타수열을 만들 수 없는 경우
    if(a.length < 4) return 0;
    const len = a.length;
    
    let freq = Array.from({length: len}, () => 0); //각 숫자 빈도수 세는 배열
    for (let i of a){
        freq[i]++;
    }
    let maxFreq = 0;
    let maxFreqNum = -1;
    for(let i=0; i < len; i++){
        if(freq[i] > maxFreq){
            maxFreq = freq[i];
            maxFreqNum = i;
        }
    } //최대 빈도수를 가진 숫자 찾기
    freq[maxFreqNum] = 0;
    
    if(maxFreq === 1){ //최대 빈도수가 1이면 교집합을 만들 수 없으므로 종료
        return 0;
    }
    let possible = 0;
    let possibleMax = 0;
    while(maxFreq > 1){
        let union = Array.from({length: len}, () => false); //집합으로 만들었는지 체크하는 배열
        for(let i = 0; i < len; i++){
            if(a[i] === maxFreqNum){
                if(i - 1 >= 0){ //왼쪽 숫자 확인
                    if(a[i - 1] !== maxFreqNum && !union[i - 1]){
                        union[i - 1] = true;
                        possible++;
                        continue;
                    }
                }
                if(i + 1 < len){ //오른쪽 숫자 확인
                    if(a[i + 1] !== maxFreqNum && !union[i + 1]){
                        union[i + 1] = true;
                        possible++;
                        continue;
                    }
                }
            }
        }
        
        let nextMaxFreq = 0;
        let nextMaxFreqNum = -1;
        for(let i=0; i < len; i++){
            if(freq[i] > nextMaxFreq){
                nextMaxFreq = freq[i];
                nextMaxFreqNum = i;
            }   
        } //최대 빈도수를 가진 숫자 찾기
        
        possibleMax = Math.max(possibleMax, possible);
        if(nextMaxFreq <= possibleMax){
            return possibleMax * 2;
        }
        
        maxFreq = nextMaxFreq;
        maxFreqNum = nextMaxFreqNum;
        freq[maxFreqNum] = 0;
        possible = 0;
    }
        
    console.log(freq);
    
    return 0;
}