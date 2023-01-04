package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** 
 * 풀이)
 * 1. 리프노드의 모음을 list로 저장
 * 2. 리프노드에서 리프노드까지의 거리를 DFS로 탐색
 * 3. 거리를 이동하면서 이동거리를 누적후 리프노드 도착시 max값 갱신
 * 
 * 제한)
 * 1 <= n <= 10000
 */
class Problem1967 {

    static ArrayList<ArrayList<int[]>> trees;
    static boolean[] visited;
    static int max = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        trees = new ArrayList<>();
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            trees.add(new ArrayList<>());
        }

        int parentNode = 0;
        int childNode = 0;
        int distance = 0;

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            parentNode = Integer.parseInt(st.nextToken());
            childNode = Integer.parseInt(st.nextToken());
            distance = Integer.parseInt(st.nextToken());
            trees.get(parentNode).add(new int[]{childNode, distance});
            trees.get(childNode).add(new int[]{parentNode, distance});
        }

        ArrayList<Integer> leafNodes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (trees.get(i).size() == 1) {
                leafNodes.add(i);
            }
        }

        for (int i = 0; i < leafNodes.size(); i++) {
            visited = new boolean[n+1];
            visited[leafNodes.get(i)] = true;
            dfs(leafNodes.get(i), leafNodes.get(i), 0);
        }

        System.out.println(max);
    }

    static void dfs(int nextNode, int startNode, int sum) {
        if (trees.get(nextNode).size() == 1 && nextNode != startNode) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < trees.get(nextNode).size(); i++) {
            int next = trees.get(nextNode).get(i)[0];
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, startNode, sum + trees.get(nextNode).get(i)[1]);
            }
        }
    }
}
/*
12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10

45
 */

