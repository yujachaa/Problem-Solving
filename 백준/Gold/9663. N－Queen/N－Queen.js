const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./9663.txt")
  .toString()
  .trim()
  .split("\n");

const N = parseInt(input);

//좌상, 상, 우상 3방향 탐색
const dr = [-1, -1, -1];
const dc = [-1, 0, 1];

let count = 0;

const backtracking = (row, board) => {
  //기저조건
  if (row === N) {
    count++;
    return;
  }

  //현재 위치에서 할 일
  for (let col = 0; col < N; col++) {
    //현재 위치가 퀸을 놓을 수 있는지 확인
    let possible = true;
    out: for (let d = 0; d < 3; d++) {
      //3방향 탐색
      let x = 1;
      let nr = row + dr[d] * x;
      let nc = col + dc[d] * x;
      while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
        //한방향 끝까지 탐색
        if (board[nr][nc]) {
          possible = false;
          break out;
        }
        x++;
        nr = row + dr[d] * x;
        nc = col + dc[d] * x;
      }
    }

    //놓을 수 있는 경우 백트래킹
    if (possible) {
      board[row][col] = true;
      backtracking(row + 1, board);
      board[row][col] = false;
    }
  }
};

const findQueen = (N) => {
  //N*N 보드 만들기
  let board = Array.from({ length: N }, () => new Array(N).fill(false));
  backtracking(0, board);
  console.log(count);
};

findQueen(N);
