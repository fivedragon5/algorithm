package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17471
 *
 * 제한)
 *  2 ≤ N ≤ 10
 *  1 ≤ 구역의 인구 수 ≤ 100
 *
 * 문제)
 *  1. 백준시는 N개의 구역으로 나누어져 있다.
 *  2. 구역은 1번부터 N번까지 번호가 붙어 있다.
 *  3. 구역을 2개의 선거구로 나누어야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다.
 *  4. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다.
 *  5. 구역 A에서 인접한 구역을 통해 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다.
 *  6. 중간에 통하는 인접한 구역은 0개 이상이여야 하고, 모두 같은 선거구에 포함되어야 한다.
 *  7. 공평하게 선거구를 나누기 위 두 선거구에 포함된 인구의 차이를 최소로 하는 최소 값은?
 *
 * 풀이)
 *  1. 구역들을 A,B 두 선거구로 나누는 조합 전부를 구한다.
 *  2. 두 선거구로 나눈 조합이 각각 연결 되어있는지 확인한다.
 *   - 두 선거구로 나눌때 한쪽 선거구의 구역의 수를 1개 ~ N/2개 까지 구하기 (중복 조합 제거)
 *   - 연결 되어 있을 경우 : 각 선거구의 인구수를 구하고 차이를 구한다.
 *   - 연결 되어 있지 않을 경우 : skip
 */
class Problem17471 {
    private static int N;
    private static int[] POPULATIONS;
    private static List<List<Integer>> NODE = new LinkedList<>();
    private static boolean[] selected;
    private static int minDiff = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        input();
        selected = new boolean[N];
        // 구역을 2개로 나누기 위해서는 1~N/2 까지의 조합을 구해야 한다.
        // N/2 까지 조합을 구하는 이유
        // - 1:N = N:1 일때 그룹이 A,B로 나누어도 인구수의 차이는 같기 때문에 중복을 피하기 위해서
        for (int r = 1; r <= N / 2; r++) {
            combination(0, 0, r);
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static void combination(int start, int depth, int r) {
        if (depth == r) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (selected[i]) groupA.add(i);
                else groupB.add(i);
            }

            if (isConnected(groupA) && isConnected(groupB)) {
                int diff = Math.abs(getPopulation(groupA) - getPopulation(groupB));
                minDiff = Math.min(minDiff, diff);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            combination(i + 1, depth + 1, r);
            selected[i] = false;
        }
    }

    private static int getPopulation(List<Integer> group) {
        int sum = 0;
        for (int i : group) {
            sum += POPULATIONS[i];
        }
        return sum;
    }

    private static boolean isConnected(List<Integer> group) {
        boolean[] visited = new boolean[N];
        List<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited[group.get(0)] = true;

        while (!queue.isEmpty()) {
            int current = queue.remove(0);
            for (int neighbor : NODE.get(current)) {
                if (!visited[neighbor] && group.contains(neighbor)) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        for (int i : group) {
            if (!visited[i]) return false;
        }
        return true;
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        POPULATIONS = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            POPULATIONS[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjacentCount = Integer.parseInt(st.nextToken());
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < adjacentCount; j++) {
                list.add(Integer.parseInt(st.nextToken()) - 1);
            }
            NODE.add(list);
        }
    }
}
/*
6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2


 */

