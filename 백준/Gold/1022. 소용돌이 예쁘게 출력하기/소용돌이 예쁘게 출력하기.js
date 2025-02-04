const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./1022.txt")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

// 입력 값
let [r1, c1, r2, c2] = input;

// 필요한 배열 크기 계산
const rows = r2 - r1 + 1;
const cols = c2 - c1 + 1;
let arr = Array.from({ length: rows }, () => new Array(cols));

// 최대 숫자 구하기 및 소용돌이 패턴 생성
function makeSpiral(r1, c1, r2, c2) {
  const dr = [0, -1, 0, 1]; // 우, 상, 좌, 하 이동
  const dc = [1, 0, -1, 0];
  let row = 0,
    col = 0,
    num = 1;
  let moveMax = 1,
    move = 0,
    repeatCount = 0,
    dir = 0;

  while (true) {
    if (r1 <= row && row <= r2 && c1 <= col && col <= c2) {
      arr[row - r1][col - c1] = num;
    }
    if (
      num >
      (2 * Math.max(Math.abs(r1), Math.abs(c1), Math.abs(r2), Math.abs(c2)) +
        1) **
        2
    )
      break;

    row += dr[dir];
    col += dc[dir];
    num++;
    move++;

    if (move === moveMax) {
      repeatCount++;
      dir = (dir + 1) % 4;
      move = 0;
      if (repeatCount === 2) {
        moveMax++;
        repeatCount = 0;
      }
    }
  }
}

makeSpiral(r1, c1, r2, c2);

// 최대 숫자의 자릿수 계산
const maxNum = Math.max(...arr.flat().filter((v) => v !== undefined));
const maxLen = maxNum.toString().length;

// 결과 출력
let result = "";
for (let i = 0; i < rows; i++) {
  result +=
    arr[i].map((num) => num.toString().padStart(maxLen, " ")).join(" ") + "\n";
}
console.log(result);