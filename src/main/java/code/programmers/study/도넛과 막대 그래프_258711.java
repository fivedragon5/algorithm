package code.programmers.study;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 *
 * 제한)
 *  1 ≤ edges의 길이 ≤ 1,000,000
 *      edges의 원소는 [a,b] 형태이며, a번 정점에서 b번 정점으로 향하는 간선이 있다는 것을 나타냅니다.
 *      1 ≤ a, b ≤ 1,000,000
 *  문제의 조건에 맞는 그래프가 주어집니다.
 *  도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상입니다.
 *
 * 문제)
 *  1. 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프가 주어진다.
 *  2. 그래프 모양
 *   - 도넛 모양 : n개의 정점, n개의 간선
 *      - 아무 한 정점에서 출발해 이용한 적 없는 간선을 계속 따라가면 나머 n-1개의 정점들을 한 번씩 방문한 뒤 원래 출발했던 정점으로 돌아오게 됨
 *   - 막대 모양 : n개의 정점, n-1개의 간선
 *      - 아무 한 정점에서 출발해 간선을 계속 따라가면 나머지 n-1개의 정점들을 한 번씩 방문한 뒤 더 이상 갈 수 없는 정점이 단 하나 존재
 *   - 8자 모양 : 2n + 1 개의 정점, 2n + 2 개의 간선
 *  3. 그래프가 정보가 주어질 때, 이 그래프들과 무관한 정점 1개를 임의로 생성하고 이 정점에서 임의의 하나의 정점으로 연결되는 간선을 하나 추가
 *  4. 이 때, 정점을 생성하기 이전의 각 그래프들의 수를 구하기
 *  answer ex) 생성한 정점의 번호, 도넛 모양 그래프 수, 막대 모양 그래프 수, 8자 모양 그래프 수
 *
 * 풀이)
 *  생성된 정점의 특징
 *   - 정점으로 들어오는 간선은 없고 정점에서 나가는 간선만 존재
 *   - 생성된 정점은 각 그래프들과 연결되어야 함으로 생성된 정점의 나가는 간선은 2개 이상
 *  특징으로 생성된 정점을 찾고 시작하기
 *
 *   1. 생성된 정점 찾기
 *    - 생성된 정점의 out 간선의 개수는 모든 그래프의 모양의 총 합과 같음
 *   2. 생성된 정점을 제외한 모든 정점들의 in, out 간선의 개수를 구하기
 *   3. 막대, 8자 모양 그래프의 수를 구하기
 *    - 8자 모양 : 정점의 out 간선의 개수가 2개인 정점의 수
 *    - 막대 모양 : 정점의 out 간선의 개수가 0개인 정점의 수
 *   4. 도넛 모양 그래프의 수 : 전체 정점의 수 - 막대 모양 그래프의 수 - 8자 모양 그래프의 수
 *
 */
class Lesson258711 {
    public static void main(String[] args) {
        // [2, 2], [3, 2], [3, 1], [1, 4]
        int[][] edges5 = new int[][]{{2, 2}, {3, 2}, {3, 1}, {1, 4}};
        System.out.println(solution(edges5));

        //[4, 11], [1, 12], [8, 3], [12, 7], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]
        int[][] edges4 = new int[][]{
                {1, 12}, {8, 3}, {12, 7}, {7, 11}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8},
                {4, 11}, {4, 8}
        };
        System.out.println(solution(edges4));

        // [1, 12], [8, 3], [12, 7], [7, 11], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8], [4, 11], [4, 8]
        int[][] edges3 = new int[][]{
                {1, 12}, {8, 3}, {12, 7}, {7, 11}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8},
                {4, 11}, {4, 8}
        };
        System.out.println(solution(edges3));

//        int[][] edges = new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}};
//        System.out.println(solution(edges));

        int[][] edges2 = new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        System.out.println(solution(edges2));
    }

    private static int[][] NODE_INFO = new int[1000001][2];

    public static int[] solution(int[][] edges) {
        int createdNodeNumber = getCreatedNodeNumber(edges);

        if (createdNodeNumber == -1) {
            System.out.println("생성된 정점 없음");
            return new int[]{-1, 0, 0, 0}; // 생성된 정점이 없는 경우
        }

        int[] result = calcGraph(edges, createdNodeNumber);
        return new int[]{
                createdNodeNumber,
                result[0], // 도넛 모양 그래프 수
                result[1], // 막대 모양 그래프 수
                result[2]  // 8자 모양 그래프 수
        };
    }

    private static int[] calcGraph(int[][] edges, int excludedNode) {
        Set<Integer> nodeSet = new HashSet<>();
        int nodeGroupCount = 0;
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (a != excludedNode) {
                NODE_INFO[b][0]++;
                NODE_INFO[a][1]++;
                nodeSet.add(a);
                nodeSet.add(b);
            } else {
                nodeSet.add(b);
                nodeGroupCount++;
            }
        }
        int totalNodeCount = nodeGroupCount;
        int donutGraphCount = 0;
        int stickGraphCount = 0;
        int eightGraphCount = 0;

        for (int node : nodeSet) {
            if (NODE_INFO[node][1] == 2) {
                eightGraphCount++;
            } else if (NODE_INFO[node][1] == 0) {
                stickGraphCount++;
            }
        }
        donutGraphCount = totalNodeCount - stickGraphCount - eightGraphCount;

        return new int[]{donutGraphCount, stickGraphCount, eightGraphCount};
    }

    private static int getCreatedNodeNumber(int[][] edges) {
        Map<Integer, Integer> nodeLineCount = new HashMap<>();
        HashSet<Integer> excludedNodes = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            nodeLineCount.put(a, nodeLineCount.getOrDefault(a, 0) + 1);
            excludedNodes.add(b);
        }
        for (int node : nodeLineCount.keySet()) {
            if (nodeLineCount.get(node) >= 2 && !excludedNodes.contains(node)) {
                return node;
            }
        }
        return -1; // 생성된 정점이 없는 경우
    }
}
