package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 접근)
 *  1. 친구들간의 연결관계를 통해 친구들을 그룹으로 만듬
 *  2. 각 그룹중 1명만 친구비를 내면 전부 친구가 될 수 있기때문에 최소값을 구함
 *  3. 최소값들을 더해 가지고 있는 돈 내에서 해결가능한지 판별
 *
 * 풀이)
 *  1. 친구들간의 연결관계를 풀어 그룹을 만듬
 *      - DFS
 *  2. 그룹을 만들면서 최소값을 가지고 있다가 그룹이 완성되는 시점에 totalCost에 누적해서 더해줌
 *  3. 모든 친구들의 대한 순회가 끝나면 totalCost를 확인하여 수중에 잇는 돈(K)와 비교하여 정답을 산출
 *
 * 제한)
 * 1 ≤ N ≤ 10,000
 * 0 ≤ M ≤ 10,000
 * 1 ≤ k ≤ 10,000,000
 * 1 ≤ Ai ≤ 10,000
 * 1 ≤ i ≤ N
 */
class Problem16562 {

    static int N, M, K;
    static ArrayList<Integer>[] friends;
    static int[] costs;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        K = Integer.parseInt(st.nextToken()); // 가지고 있는 돈

        costs = new int[N+1]; // 친구가 되기 위한 비용
        friends = new ArrayList[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N+1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        int result = groupFriend();

        System.out.println(result > K ? "Oh no" : result);

    }

    static int groupFriend() {
        int totalCost = 0;
        boolean visited[] = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int minCost = costs[i];
            while (!queue.isEmpty()) {
                ArrayList<Integer> list = friends[queue.poll()];
                for (int num : list) {
                    if (!visited[num]) {
                        visited[num] = true;
                        queue.add(num);
                        minCost = Math.min(minCost, costs[num]);
                    }
                }
            }
            totalCost += minCost;
        }
        return totalCost;
    }
}
/*
5 3 20
10 10 20 20 30
1 3
2 4
5 4

20

5 3 10
10 10 20 20 30
1 3
2 4
5 4

Oh no
 */

