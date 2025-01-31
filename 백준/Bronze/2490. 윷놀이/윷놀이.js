const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./2490.txt")
  .toString()
  .trim()
  .split("\n")
  .map((e) => e.split(" ").map(Number));

for (let i = 0; i < input.length; i++) {
  let zero = 0;
  for (j of input[i]) {
    if (j === 0) zero++;
  }

  let answer;
  switch (zero) {
    case 0: {
      answer = "E";
      break;
    }
    case 1: {
      answer = "A";
      break;
    }
    case 2: {
      answer = "B";
      break;
    }
    case 3: {
      answer = "C";
      break;
    }
    case 4: {
      answer = "D";
      break;
    }
  }
  console.log(answer);
}
