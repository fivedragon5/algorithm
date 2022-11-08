package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem15900 {

    /**
     * 풀이)
     * 루트노드 ~ 모든 리프노드(자식이 없는 노드) 거리의 합을 구해서 품
     *
     * 1. 입력된 값을 바탕으로 노드간 간선을 연결한 arrayList 만들기
     * 2. 루트노드에서부터 순회 시작, DFS, 순회하면서 step을 늘려나감
     * 3. 순회중 노드가 리프노드일경우 중지하고 리프노드의 값을 저장
     *
     * 설명)
     * arrayList roads : 트리 정보
     * step : 거리

        문제 조건)
        2 ≤ N ≤ 500,000
        1 <= a,b <= N
        a != b (a,b 사이에 간선 존재)
        ROOT 1번 정점
        성원이가 먼저 시작
     */

    static ArrayList<ArrayList<Integer>> roads = new ArrayList<>();
    static int leafSum = 0;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            roads.get(node1).add(node2);
            roads.get(node2).add(node1);
        }
        dfs(1,0);

        if (leafSum%2 != 0) System.out.println("Yes");
        else System.out.println("No");
    }

    static void dfs(int node, int step) {

        visited[node] = true;

        for (int num : roads.get(node)) {

            if (!visited[num]) {
                dfs(num, step+1);
            }
        }
        
        if (roads.get(node).size() == 1) {
            leafSum += step;
        }
    }
}

/**
 2
 2 1

 Yes

 4
 1 2
 2 3
 2 4

 No

 8
 8 1
 1 4
 7 4
 6 4
 6 5
 1 3
 2 3

 No

 10
 1 2
 2 3
 3 4
 4 5
 5 6
 5 7
 5 8
 5 9
 5 10

 7
 1 2
 1 3
 1 4
 1 5
 1 6
 1 7
 1 8
 */
