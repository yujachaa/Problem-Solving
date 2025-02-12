const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./14235.txt")
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.trim().split(" ").map(Number));

class MaxHeap {
  constructor() {
    this.heap = [];
  }

  swap(i, j) {
    [this.heap[i], this.heap[j]] = [this.heap[j], this.heap[i]];
  }

  enqueue(value) {
    this.heap.push(value);
    this.bubbleUp();
  }

  bubbleUp() {
    let index = this.heap.length - 1;
    let parent = Math.floor((index - 1) / 2);

    while (index > 0 && this.heap[parent] < this.heap[index]) {
      this.swap(index, parent);
      index = parent;
      parent = Math.floor((index - 1) / 2);
    }
  }

  dequeue() {
    if (this.heap.length === 0) return -1;
    if (this.heap.length === 1) return this.heap.pop();

    const max = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.bubbleDown(0);
    return max;
  }

  bubbleDown(index) {
    let left = index * 2 + 1;
    let right = index * 2 + 2;
    let largest = index;

    if (left < this.heap.length && this.heap[left] > this.heap[largest]) {
      largest = left;
    }
    if (right < this.heap.length && this.heap[right] > this.heap[largest]) {
      largest = right;
    }
    if (largest !== index) {
      this.swap(index, largest);
      this.bubbleDown(largest);
    }
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

// 입력 처리 및 문제 풀이
const n = input[0][0];
let pq = new MaxHeap();

for (let i = 1; i <= n; i++) {
  const line = input[i];
  const a = line[0];

  if (a === 0) {
    // 아이를 만난 경우
    console.log(pq.isEmpty() ? -1 : pq.dequeue());
  } else {
    // 거점지에서 선물 추가
    for (let j = 1; j < line.length; j++) {
      pq.enqueue(line[j]);
    }
  }
}
