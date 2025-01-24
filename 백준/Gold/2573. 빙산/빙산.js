const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((e) => e.split(" ").map(Number));

//4방 탐색 상 하 좌 우
const dr = [-1, 1, 0, 0];
const dc = [0, 0, -1, 1];

const N = input[0][0]; //행 개수
const M = input[0][1]; //열 개수
let map = input.slice(1);
// console.log(map);

//빙산 개수 세기
let ice = 0;
for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (map[i][j] > 0) ice++;
  }
}

//dfs로 탐색해서 visited 표시, 방문하지 않은 빙산이 있으면 분리된 것
const isSeperated = (map) => {
  let visited = Array.from({ length: N }, () => Array(M).fill(false)); //N*M 배열 만들기
  let startR, startC;
  out: for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] > 0) {
        startR = i;
        startC = j;
        break out;
      }
    }
  } //얼음이 다 녹았을 때 고려안함

  dfs(startR, startC, visited, map); //시작위치가 undefined로 에러 발생

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] > 0) {
        if (visited[i][j] === false) return true; //분리되어있다
      }
    }
  }
  return false; //분리되지 않음
};

//끝까지 탐색
const dfs = (row, col, visited, map) => {
  //현재 위치에서 할 일
  visited[row][col] = true; //방문 체크
  //4방탐색
  for (let i = 0; i < 4; i++) {
    const nr = row + dr[i];
    const nc = col + dc[i];
    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //범위 초과
    if (visited[nr][nc] === false && map[nr][nc] > 0) dfs(nr, nc, visited, map);
  }
};

const meltIce = () => {
  let newMap = map.map((row) => row.slice());
  // console.log(newMap);
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] > 0) {
        let count = 0;
        //4방탐색
        for (let k = 0; k < 4; k++) {
          const nr = i + dr[k];
          const nc = j + dc[k];
          if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //범위 초과
          if (map[nr][nc] === 0) count++;
        }
        newMap[i][j] = map[i][j] - count < 0 ? 0 : map[i][j] - count;
        if (newMap[i][j] === 0) ice--;
      }
    }
  }

  for (let i = 0; i < N; i++) {
    map[i] = newMap[i].slice();
  }
};

let day = 0;
let flag = false;

while (ice > 0) {
  //빙산이 다 녹을때까지 반복
  day++;
  meltIce();
  // console.log(map.map((row) => row.join(" ")).join("\n"));

  if (ice === 0) break; //얼음 다 녹은 경우 분리 확인하지 않고 바로 종료

  if (isSeperated(map)) {
    flag = true;
    break;
  }
}

if (flag) console.log(day);
else console.log(0);
