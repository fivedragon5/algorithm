package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem1922 {
    /**
     6
     9
     1 2 5
     1 3 4
     2 3 2
     2 4 7
     3 4 6
     3 5 11
     4 5 3
     4 6 8
     5 6 8
     */
    static int N = 0;
    static int minDistance = 999999;
    static ArrayList<int[]>[] array;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        array = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            //컴퓨터 1 을 배열에 넣을때 index를 0번으로 넣으려고 -1 해줌
            int computer_1 = Integer.parseInt(st.nextToken()) - 1;
            int computer_2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            array[computer_1].add(new int[]{computer_2, distance});
        }

        for (ArrayList<int[]> a : array) {
            for(int[] arr : a) {
                System.out.print(Arrays.toString(arr));
            }
            System.out.println();
        }

        bfs(0);
    }

    static void bfs(int now) {
        visited[now] = true;
    }
}
