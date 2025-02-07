const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./4949.txt")
  .toString()
  .trim()
  .split("\n");

const strings = input.slice(0, -1); //마지막 줄 제외

for (let line of strings) {
  let stack = [];

  for (let char of line) {
    //.을 만나면 결과 출력
    if (char === ".") {
      if (stack.length === 0) {
        //스택이 비어있으면 균형을 이루고 있음
        console.log("yes");
      } else {
        console.log("no");
      }
    }

    //괄호 만나는 경우
    if (char === "(") {
      stack.push(char);
    }
    if (char === ")") {
      if (stack[stack.length - 1] === "(") {
        //짝이 맞으면 pop
        stack.pop();
      } else {
        stack.push(char);
      }
    }
    if (char === "[") {
      stack.push(char);
    }
    if (char === "]") {
      if (stack[stack.length - 1] === "[") {
        //짝이 맞으면 pop
        stack.pop();
      } else {
        stack.push(char);
      }
    }
  }
}
