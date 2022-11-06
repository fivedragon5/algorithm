package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem24230 {

    /**
     트리의 ROOT 는 항상 1
     1 <= N <= 200,000
     */

    static int[] colors;
    static ArrayList<ArrayList<Integer>> trees = new ArrayList<>();
    static int answer = 0;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        colors = new int[N+1];
        visited = new boolean[N+1];
        trees.add(new ArrayList<Integer>());

        for (int i = 0; i < N; i++) {
            trees.add(new ArrayList<Integer>());
            colors[i+1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            trees.get(start).add(end);
            trees.get(end).add(start);
        }

//        for (int i = 1; i <= N; i++) {
//            System.out.println(colors[i] + ":" + trees.get(i).toString());
//        }

        if (colors[1] != 0) answer = 1;

        visited[1] = true;
        solve(1);

        System.out.println(answer);
    }

    static void solve(int point) {
        int currentColor = colors[point];
        visited[point] = true;

        for (int nextPoint : trees.get(point)) {
            if(!visited[nextPoint]) {
                int nextColor = colors[nextPoint];
                if (currentColor != nextColor) {
                    answer += 1;
                }
                solve(nextPoint);
            }
        }
    }

}

/**
 7
 0 0 2 0 1 2 2
 1 2
 1 3
 1 4
 2 5
 3 6
 3 7

 2

 10
 0 0 1 0 2 1 0 2 2 2
 3 1
 1 4
 9 5
 10 5
 1 2
 3 6
 3 5
 5 8
 4 7

 2
 */
