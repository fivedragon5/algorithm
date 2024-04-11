package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. BFS
 *
 * 풀이)
 * 1. 0 ~ N까지 시작점을 다르게 하여 친구 체이닝이 되는지 확인
 * 2. BFS를 사용, BFS의 인자로 다음 노드, 지금까지의 친구, count를 담았다.
 * 3. 친구의 수가 5명이 된 순간 체이닝이 성공한것으로 판별하여 종료
 *
 * 제한)
 *  5 <= N <= 2000
 *  1 <= M <= 2000
 *  0 <= a, b <= N-1
 *  같은 친구 관계가 두 번 이상 주어지는 경우는 없음
 */
class Problem13023 {

    static ArrayList<Integer>[] friends;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        friends = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            friends[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }
        // BFS로 visited를 넘겨주는 형태로 해야할 듯

        for (int i = 0; i < N; i++) {
            for (int next : friends[i]) {
                Set<Integer> nextSet = new HashSet();
                nextSet.add(i);
                nextSet.add(next);
                if (bfs(next, nextSet, 2)) {
                    System.out.println("1");
                    return;
                }
            }
        }
        System.out.println("0");
    }

    static boolean bfs(int current, Set<Integer> set, int count) {
        if (count == 5) {
            if (set.size() == 5) return true;
            return false;
        }
        for (int next : friends[current]) {
            if (!set.contains(next)) {
                Set<Integer> newSet = new HashSet();
                newSet.addAll(set);
                newSet.add(next);
                if (bfs(next, newSet, count + 1)) return true;
            }
        }
        return false;
    }
}
/*
5 4
0 1
1 2
2 3
3 4

1

5 5
0 1
1 2
2 3
3 0
1 4

1

6 5
0 1
0 2
0 3
0 4
0 5

0
 */

