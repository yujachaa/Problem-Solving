const input = require("fs")
  .readFileSync(process.platform === "linux" ? "/dev/stdin" : "./1738.txt")
  .toString()
  .trim()
  .split("\n")
  .map((e) => e.split(" ").map(Number));

//입력
//정점개수 n : 2~100 , 간선 개수 m : 1~20000
//m개의 간선 정보
//u -> v, 가중치 w : -1000~1000 (음수 가중치 가능)

//로직
//시작 정점 : 1, 도착 정점 : n
//양의 사이클이 발생하면 -1을 출력해야된다
//1. 벨만 포드 알고리즘으로 양의 사이클 확인
// ->가능한지 모르기때문에 가중치 부호 반전 후 음의 사이클 확인
// ->음의 사이클이 있다면 -1 출력
//2. 가중치 부호를 반전했기때문에 비용이 최대 -> "최소"가 되는 경로 찾기
//3. 비교 후 갱신할 때 정점 저장

//출력
//시작에서 도착까지 가는 경로들 중 금품의 양이 최대가 되는 경로를 공백을 두고 차례로 출력
//최적의 경로가 존재하지 않는다면 -1 출력

const [n, m] = input[0];
//간선 저장
let edges = [];
for (let i = 0; i < m; i++) {
  const [u, v, w] = input[i + 1];
  edges.push([u, v, -w]); //가중치 부호 반전
}

const INF = 987654321;
let dist = Array(n + 1).fill(INF);
let parent = Array(n + 1).fill(-1); //최단 경로 역추적을 위한 이전노드를 저장하는 배열
let reachableFromNegativeCycle = Array(n + 1).fill(false); // 음수 사이클 영향 여부

function bellmanFord(start) {
  //음수 사이클이 존재하는지 여부를 반환
  dist[start] = 0; //출발점
  parent[start] = null; //시작점의 parent는 없음

  let hasNegativeCycle = false;

  for (let i = 0; i < n; i++) {
    //n - 1번 반복
    for (let [now, next, weight] of edges) {
      //매 반복마다 "모든 간선" 확인
      //현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
      if (dist[now] !== INF && dist[next] > dist[now] + weight) {
        dist[next] = dist[now] + weight;
        parent[next] = now; //경로 저장
      }
    }
  }

  // n번째 반복 (음수 사이클 존재 여부 확인)
  for (let i = 0; i < n; i++) {
    for (let [now, next, weight] of edges) {
      if (dist[now] !== INF && dist[next] > dist[now] + weight) {
        dist[next] = dist[now] + weight;
        parent[next] = now;
        hasNegativeCycle = true;
        reachableFromNegativeCycle[next] = true; // 음수 사이클 영향을 받는 정점 표시
      }
      // 음수 사이클과 연결된 노드들 전파
      if (reachableFromNegativeCycle[now]) {
        reachableFromNegativeCycle[next] = true;
      }
    }
  }
  return hasNegativeCycle;
}

const negativeCycle = bellmanFord(1);

if (negativeCycle && reachableFromNegativeCycle[n]) {
  // 1번에서 n번으로 가는 경로가 "음수 사이클"에 영향을 받으면 -1 출력
  console.log(-1);
} else if (dist[n] === INF) {
  //1번에서 n번으로 도달 불가능하면 -1 출력
  console.log(-1);
} else {
  // n까지 가는 최단거리(비용최대거리) 구해져있으므로 역순으로 경로 추적
  let path = [];
  let node = n;
  while (node !== null) {
    path.push(node);
    node = parent[node];
  }
  path.reverse();

  console.log(path.join(" ").trim());
}
