//입력
//key : 2차원배열 (M*M)
//lock : 2차원배열 (N*N)

//로직
// 열쇠든 자물쇠든 일단 돌려서 홈을 맞춰야함 → 홈이 맞으면 true 안맞으면 false
//M은 항상 N 이하입니다. → 열쇠는 항상 자물쇠보다 같거나 작음 → 열쇠를 돌리자
// M*M/ N*N 중 하나를 고정하고, 나머지를 돌려가며, 배치를 해봤을 때, 홈에 정확히 일치하면 true/ 아니면 false
// 왜 키를 돌려야될까? (작은걸 돌리는게 직관적)
// 자물쇠나 키를 돌려서 다른 한쪽에 맞추면 된다
//   1. 자물쇠 영역에 패딩을 줘서 자물쇠를 그 영역의 중앙에 놓기 ( 범위를 넘어가서 홈에 맞을 수 있기 때문)
//   2. 키를 회전 시키고 이동시키면서 홈에 맞는지 확인
//      2-1. 회전시키는 방법
//      2-2. 홈에 맞는지 확인
//          2-3-1. 중앙에 키가 들어간 영역에 홈이 겹치는 부분이 없어야함
//          2-3-2. 중앙에 자물쇠 영역이 꽉 차야함
//   3. 찐 자물쇠의 모든 홈이 채워졌는지 확인

//출력
//자물쇠를 열 수 있으면 true / 아니면 false

function solution(key, lock) {
    const N = lock.length;
    const M = key.length;
    
    //자물쇠 3배 크기의 큰 배열 생성
    let bigArr = Array.from({length: N * 3}, () => new Array(N * 3).fill(0));
    //자물쇠를 중앙에 옮김
    for(let i = N; i < 2*N; i++){
        for(let j = N; j < 2*N; j++){
            bigArr[i][j] = lock[i - N][j - N];
        }
    }
    
    function rotate(arr){ //배열을 오른쪽으로 90도 회전시키는 함수
        const len = arr.length;
        let newArr = Array.from({length: len}, () => new Array(len));
        for(let i = 0; i < len; i++){
            for(let j = 0; j < len; j++){
                newArr[j][len - 1 - i] = arr[i][j];          
            }
        }
        return newArr;
    }

    function canOpen(row, col, key){ //키의 위치에 따라 열 수 있는지 확인하는 함수
        let tmpBigArr = bigArr.map((row) => row.slice());
        for(let i = row; i < row + M; i++){
            for(let j = col; j < col + M; j++){
                if(i >= N && i < 2*N && j >= N && j < 2*N){//찐 자물쇠 범위
                    //홈이 안채워지는 경우, 둘다 돌기인 경우
                    if(key[i-row][j-col] === tmpBigArr[i][j]) return false;
                    if(tmpBigArr[i][j] === 0) tmpBigArr[i][j] = 1;
                }
            }
        }
        
        for(let i = N; i < 2*N; i++){
            for(let j = N; j < 2*N; j++){
                //안채워진 홈이 있는 경우
                if(tmpBigArr[i][j] === 0) return false;
            }
        }

        return true;
    }

    
    //열쇠를 최대 4번 돌리면서 확인
    for(let r = 0; r < 4; r++){
        //열쇠를 좌측 상단에서부터 한칸씩 이동시키면서 확인
        for(let i = 0; i < 3*N - M; i++){
            for(let j = 0; j < 3*N - M; j++){
                //탐색시작좌표 
                if(canOpen(i, j, key))
                    return true;
            }
        }
        key = rotate(key);
    }
    return false;
}