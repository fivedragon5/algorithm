package algorithm.programmers;

/*
    최단거리 알고리즘
    1. 다익스트라
        - 하나의 정점에서 다른 모든 정점으로 가는 최단 거리

    2. 플로이드 와샬
        - 모든 정점에서 모든 정점으로의 최단거리


        - 다익스트라 정렬, 정렬이후에 적은것 하나씩 선택 (그리디 개념)
        - 현재까지 알고있던 최단경로를 계속해서 갱신하는 알고리즘
        - 1. 출발점 정의
          2. 출발노드를 기준으로 각 노드 의 최소비용
          3. 방문하지 않는 노드 중 가장 비용이 적은 노드를 선택
          4. 해당 노드를 거쳐 특정한 노드로 가는 경우를 고려 최소 비용을 갱신
             끝날때 까지 3,4를 반복
 */

import org.springframework.core.style.ToStringCreator;

import java.util.ArrayList;
import java.util.Arrays;

class Node {
    int index;
    int distance;
    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
    int getIndex() {
        return this.index;
    }
    int getDistance() {
        return this.distance;
    }
    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", distance=" + distance +
                '}';
    }
}

class Lesson72413 {
    static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        //그래프 초기화
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        //그래프에 값 넣기
        for (int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
        }

        //방문여부 배열, start~end노드까지의 최소 거리를 저장할 배열 생성
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];

        //최소 거리 정보를 담을 배열 초기화
        for (int i = 0; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            int nodeValue = Integer.MAX_VALUE;
            int nodeIdx = 0;

            for (int j = 1; j < n + 1; j++) {
                if (!visited[j] && dist[j] < nodeValue) {
                    nodeValue = dist[j];
                    nodeIdx = j;
                }
            }

            visited[nodeIdx] = true;

            for (int j = 0; j < graph.get(nodeIdx).size(); j++) {

                Node adjNode = graph.get(nodeIdx).get(j);

                if (dist[adjNode.index] > dist[nodeIdx] + adjNode.distance) {
                    dist[adjNode.index] = dist[nodeIdx] + adjNode.distance;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else {
                System.out.println(dist[i]);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6; // 지점의 개수
        int s = 4; // 출발지
        int a = 6; // a도착지
        int b = 2; // b 도착지
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println("===START===");
        System.out.println(solution(n, s, a, b, fares));
        System.out.println("===END===");
    }
}
