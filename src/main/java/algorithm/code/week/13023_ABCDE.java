package algorithm.code.week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 제한)
 * 5 <= N <= 2000
 * 1 <= M <= 2000
 * 0 <= a, b <= N-1
 * 같은 친구 관계가 두 번 이상 주어지는 경우는 없음
 *
 */
class Problem13023 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] friends = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            friends[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].get(b);
            friends[b].get(a);
        }

        // BFS로 visited를 넘겨주는 형태로 해야할 듯
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

