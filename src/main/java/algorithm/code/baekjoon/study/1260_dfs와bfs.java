package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Problem1260 {

    static int N;
    static int[][] map;
    static boolean[] vistied;

    public static void main(String args[]) throws IOException {
        /**
         4 5 1
         1 2
         1 3
         1 4
         2 4
         3 4
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        map = new int[N][1001];

        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            map[start][end] = map[end][start] = 1;
        }

        vistied = new boolean[N];
        dfs(V-1);

        vistied = new boolean[N];
    }

    public static void dfs(int V) {
        vistied[V] = true;
        System.out.print((V+1) + " ");

        for (int i = 0; i < N ; i++) {
            if(map[V][i] == 1 && vistied[i] == false) {
                dfs(i);
            }
        }
    }

    public static void bfs(int V) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(V);
        vistied[V] = true;
        System.out.print((V+1) + " ");

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int i = 0; i < N; i++) {
            }
        }
    }

}
