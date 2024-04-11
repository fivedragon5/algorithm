package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. bfs, 트리
 * 
 * 풀이)
 * 1. 노드의 정보를 입력받음
 *  - 노드의 정보 입려과 동식에 int형 배열 parents에 부모의 정보를 넣어둠
 * 2. 노드의 정보를 순회하며 리프노드(자식이 없는 노드)일 경우 리프노드 수를 카운팅 해준다.
 * 3. 제거할 노드의 부모노드를 확인하여 자식이 제거할 노드 밖에 없다면 리프노드 카운트를 증가
 *  - 노드를 제거 했을경우 부모노드가 리프노드로 바뀌기 때문에 카운트를 추가해준다.
 * 3. 제거할 노드를 Stack에 추가한 뒤 노드 정보를 불러와 하위 노드를 전부 삭제
 *  - 하위 노드를 제거하면서 제거될 노드가 리프노드일경우 리프노드 카운트를 감소시켜준다.
 */
class Problem1068 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());

        if (N == 2) {
            System.out.println(1);
            return;
        }

        ArrayList<Integer>[] nodes = new ArrayList[N];
        int[] parents = new int[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int node = Integer.parseInt(st.nextToken());
            parents[i] = node;
            if (node != -1) {
                nodes[node].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());

        int removeNode = Integer.parseInt(st.nextToken());

        long leafNodeCount = 0;

        int parent = parents[removeNode];
        if (parent == -1) {
            System.out.println(0);
            return;
        }
        if (nodes[parent].size() == 1) leafNodeCount++;

        for (ArrayList a : nodes) {
            if (a.size() == 0) {
                leafNodeCount++;
            }
        }

        Stack<Integer> stack = new Stack<>();

        stack.add(removeNode);

        while(!stack.isEmpty()) {
            int node = stack.pop();
            ArrayList<Integer> list = nodes[node];

            if (list.size() == 0) leafNodeCount--;

            for (int remove : list) {
                stack.add(remove);
            }
        }

        System.out.println(leafNodeCount);

    }
}
/*
4
-1 0 1 2
2

1
 */




