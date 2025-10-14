package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/count-the-number-of-complete-components/description/?envType=daily-question&envId=2025-03-22
 *
 * 제한)
 *  1 <= n <= 50
 *  0 <= edges.length <= n * (n - 1) / 2
 *  edges[i].length == 2
 *  0 <= ai, bi <= n - 1
 *  ai != bi
 *  There are no repeated edges.
 *
 * 문제)
 *  n : 꼭지점의 수
 *  edges[n][2] : edges[i] | a(i)와 b(i)를 연결하는 간선
 *
 *  1. 위 그래프의 완전 연결 컴포넌트 수를 반환하기
 *   - 완전연결 컴포넌트는 그래프의 부분 그래프로, 임의의 두 정점 사이에 경로가 존재하고, 부분 그래프의 어떤 정점도 부분 그래프 외부의
 *     정점과 간선을 공유하지 않은 부분 그래프 입니다.
 *  2. 연결 컴포넌트는 해당 정점 쌍 사이에 간선이 존재하는 경우 완전하다고 합니다.
 *
 * 풀이)
 *  1. 주어진 edges 를 인접 그래프로 표현
 *  2. 인접그래프로 DFS(깊이 우선 탐색)를 통해 컴포넌트로 분리
 *  3. 분리한 컴포넌트를 완전한 컴포넌트인지 확인
 *   - 완전한 컴포넌트
 *     - 컴포넌트간의 간선의 수가 컴포넌트의 꼭지점 수 * (꼭지점 수 - 1) / 2
 */

public class Question_20250322 {
    public static void main(String args[]) throws IOException {
        int n = 6; int[][] edges = {{0,1},{0,2},{1,2},{3,4}};
        System.out.println(countCompleteComponents(n, edges));

        int n2 = 6; int[][] edges2 = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        System.out.println(countCompleteComponents(n2, edges2));

    }

    public static int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                List<Integer> component = new ArrayList<>();
                component.add(i);
                stack.add(i);
                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    for (int point : graph.get(node)) {
                        if (!visited[point]) {
                            stack.add(point);
                            visited[point] = true;
                            component.add(point);
                        }
                    }
                }
                components.add(component);
            }
        }
        int answer = 0;
        for (List<Integer> component : components) {
            int size = component.size();
            int edgeCount = 0;
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (graph.get(component.get(i)).contains(component.get(j))) {
                        edgeCount++;
                    }
                }
            }
            if (edgeCount == size * (size - 1) / 2) {
                answer++;
            }
        }
        return answer;
    }
}
