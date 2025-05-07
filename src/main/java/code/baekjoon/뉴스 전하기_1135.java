package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1135
 *
 * 제한)
 *  1 <= N <= 50
 *
 * 문제)
 *  1. 모든 직원은 정확하게 한명의 직속 상사가 있다.
 *  2. 뉴스를 들은 후, 각 부하는 그의 직속 부하에게 한번에 한 사람씩만 전화를 할 수 있다.
 *  3. 모든 부하가 전화를 받기 위해서는 최소 몇 초가 걸리는지 구하기
 *
 * 풀이)
 *  1. 트리 구조로 저장 parent - child 관계
 *  2. DFS-재귀 탐색을 통해 모든 자식들의 전파 시간을 구한다.
 *  3. 자식들의 전파 시간을 내림차순 정렬
 *  4. 각 자식의 전파 시간에 자식의 인덱스 + 1을 더한 값 중 최대값을 구한다.
 *   - 자식의 인덱스 + 1을 더하는 이유는, 자식의 전파 시간이 같을 경우, 자식의 인덱스가 낮은 자식(최대값)부터 전파를 시작하기 때문
 *   - 최대값 부터 전파를 시작해야 모든 전파 시간의 최소값을 구할 수 있음
 *
 */
class Problem1135 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<List<Integer>> tree;

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;
            tree.get(parent).add(i);
        }
        System.out.println(recursive(0));
    }

    private static int recursive(int current) {
        List<Integer> times = new ArrayList<>();

        for (int node : tree.get(current)) {
            times.add(recursive(node));
        }
        times.sort((a, b) -> b - a);
        int max = 0;
        for (int i = 0; i < times.size(); i++) {
            max = Math.max(max, times.get(i) + i + 1);
        }

        return max;
    }
}
/*
5
-1 0 0 2 2

5
-1 0 1 2 3

1
-1
 */
