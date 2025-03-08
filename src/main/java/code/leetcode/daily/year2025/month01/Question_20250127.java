package code.leetcode.daily.year2025.month01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/course-schedule-iv/description/?envType=daily-question&envId=2025-01-27
 * 제한)
 *  2 <= numCourses <= 100
 *  0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 *  prerequisites[i].length == 2
 *  0 <= ai, bi <= numCourses - 1
 *  ai != bi
 *  All the pairs [ai, bi] are unique.
 *  The prerequisites graph has no cycles.
 *  1 <= queries.length <= 104
 *  0 <= ui, vi <= numCourses - 1
 *  ui != vi
 *
 * 문제)
 *  1. 강좌의 수, 선행 강좌 배열이 주어짐
 *  2. u(i), v(i) u는 v의 선행 강좌인지 판별
 *
 * 풀이)
 *  풀이-1
 *      1-1. 각 Node Input
 *      1-2. 주어진 query 로 DFS 실행 후 true, false 판별
 *
 *  풀이-2
 *      1-1. 각 Node Input
 *      1-2. 주어진 query 로 BFS 실행
 *      1-3. 실행하면서 메모제이션 추가
 *       - 다중 TestCase일 경우, 케이스가 많을수록 메모제이션 효율 증가
 */

public class Question_20250127 {
    public static void main(String args[]) throws IOException {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        int[][] queries = {{0,1},{1,0}};
        System.out.println(checkIfPrerequisite(numCourses, prerequisites, queries));

        numCourses = 3;
        int[][] prerequisites2 = {{1,2}, {1,0}, {2,0}};
        int[][] queries2 = {{1,0},{1,2}};
        System.out.println(checkIfPrerequisite(numCourses, prerequisites2, queries2));
    }

    private static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> answer = new ArrayList<>();
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            nodes.add(new ArrayList<>());
        }

        int prerequisitesSize = prerequisites.length;
        for (int i = 0; i < prerequisitesSize; i++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];
            nodes.get(start).add(end);
        }

        int queriesSize = queries.length;
        for (int i = 0; i < queriesSize; i++) {
            answer.add(check(nodes, queries[i][0], queries[i][1]));
        }
        return answer;
    }

    private static boolean check(List<List<Integer>> nodes, int start, int end) {
        boolean result = false;
        Stack<Integer> stack = new Stack<>();
        stack.addAll(nodes.get(start));
        boolean[] visited = new boolean[nodes.size()];
        while (!stack.isEmpty()) {
            int nextNode = stack.pop();
            if (nextNode == end) {
                result = true;
                break;
            }
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                stack.addAll(nodes.get(nextNode));
            }
        }
        return result;
    }

    /**
     *  메모제이션 활용 풀이
     */
    private static List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> answer = new ArrayList<>();
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            nodes.add(new ArrayList<>());
        }

        int prerequisitesSize = prerequisites.length;
        for (int i = 0; i < prerequisitesSize; i++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];
            nodes.get(start).add(end);
        }

        // 메모이제이션 테이블 생성
        Boolean[][] memo = new Boolean[numCourses][numCourses];

        // 쿼리 처리
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            // start -> end 경로 존재 여부를 확인하고 결과 추가
            answer.add(hasPath(nodes, memo, start, end));
        }

        return answer;
    }

    // start -> end 경로 존재 여부를 확인하는 메서드 (DFS 기반)
    private static boolean hasPath(List<List<Integer>> nodes, Boolean[][] memo, int start, int end) {
        // 메모이제이션 확인
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        // start에서 바로 end로 이동 가능한 경우
        if (nodes.get(start).contains(end)) {
            memo[start][end] = true;
            return true;
        }

        // DFS 탐색으로 경로 찾기
        for (int next : nodes.get(start)) {
            if (hasPath(nodes, memo, next, end)) {
                memo[start][end] = true;
                return true;
            }
        }
        memo[start][end] = false;
        return false;
    }
}
