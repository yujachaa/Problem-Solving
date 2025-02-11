function solution(scoville, K) {
    let answer = 0;
    let newScoville = [];

    // 오름차순 정렬
    scoville.sort((a,b)=>a-b);

    let left = 0; // scoville 현재 인덱스
    let right = 0; // newScoville 현재 인덱스

    // scoville, newScoville은 항상 오름차순 정렬
    // 음식을 섞을 수 있고 && 최솟값이 K보다 작은 경우 
    while((scoville.length-left+newScoville.length-right) >= 2 && (scoville[left] < K || newScoville[right] < K)) {
        let origin1;
        let origin2;
        let new1;
        let new2;

        if(left < scoville.length) origin1 = scoville[left];
        if(left+1 < scoville.length) origin2 = scoville[left+1];
        if(right < newScoville.length) new1 = newScoville[right];
        if(right+1 < newScoville.length) new2 = newScoville[right+1];


        if(newScoville.length===0 || right >= newScoville.length || (origin2!==undefined && origin2 <= new1)) {
            // case1. scoville에서 2가지 음식을 섞는 경우
            left += 2;
            newScoville.push(origin1+origin2*2);
        } else if(left >= scoville.length || new2!==undefined && new2 <= origin1 ) {
            // case2. newScoville에서 2가지 음식을 섞는 경우
            right += 2;
            newScoville.push(new1+new2*2);
        } else {
            // case3. scoville, newScoville에서 1가지 음식씩 섞는 경우
            left += 1;
            right += 1;
            origin1 < new1 ? newScoville.push(origin1+new1*2):newScoville.push(new1+origin1*2);
        }

        answer += 1;
    }

    return (scoville[left] < K || newScoville[right] < K) ? -1 : answer;
}