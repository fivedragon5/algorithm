package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Problem15900 {

    /**
        2 ≤ N ≤ 500,000
        1 <= a,b <= N
        a != b (a,b 사이에 간선 존재)
        ROOT 1번 정점
        성원이가 먼저 시작

        자식이 없는 노드 = 리프노드
        리프노드에서 루트까지의 거리의 총합 : sum
        sum == 홀수 ? Yes : No

     */

    static ArrayList<ArrayList<Integer>> roads;
    static int leafSum = 0;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        roads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            roads.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            roads.get(node1).add(node2);
            roads.get(node2).add(node1);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N];

        // Root Node = 0
        visited[0] = true;
        solve(0, stack, visited);

        System.out.println(leafSum%2 == 0 ? "NO" : "YES");

//        for(ArrayList<Integer> al : roads) {
//            System.out.println(al.toString());
//        }
    }

    static public void solve(int node, Stack<Integer> stack, boolean[] visited) {

        //간선을 연결 했을때 차수가1인 노드 = 리프노드
        if (roads.get(node).size() == 1 && node != 0) {
            //System.out.println("리프노드 : " + node + "/ size : " + stack.size());
            leafSum += stack.size();
        }

        for (int n : roads.get(node)) {
            //방문 체크
            if (!visited[n]) {
                visited[n] = true;
                stack.add(n);
                solve(n, stack, visited);
                stack.pop();
                visited[n] = false;
            }
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

 1 2
 1 3
 1 4
 1 5
 1 6
 1 7
 1 8
 */
