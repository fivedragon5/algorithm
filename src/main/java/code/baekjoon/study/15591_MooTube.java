package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem15591 {

    static int n, q;

    public static void main(String args[]) throws IOException {
        /** ex
          4 3
          1 2 3
          2 3 2
          2 4 4
          1 2
          4 1
          3 1
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] usado  = new ArrayList[n+1];

        for(int i = 1; i < n + 1; i++) {
            usado[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());

            usado[a].add(new int[]{b, c});
            usado[b].add(new int[]{a, c});
        }

        for (int i = 1; i <= 4; i++) {
            System.out.println(i + "!!");
            for(int[] arr : usado[i] ) {
                System.out.println(Arrays.toString(arr));
            }
        }

        for(int i = 1; i <= q; i++) {
            st = new StringTokenizer(br.readLine());
            int answer = 0;

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new LinkedList<>();

            int[] visited = new int[n+1];
            visited[v] = 1;
            queue.add(v);

            while(!queue.isEmpty()) {
                int now = queue.poll();
                for(int[] arr : usado[now] ) {
                    int next = arr[0];
                    int usadoMin = arr[1];
                    if(visited[next] == 1 || usadoMin < k)
                        continue;
                    answer++;
                    visited[next] = 1;
                    queue.add(next);
                }
            }
            System.out.println(answer);
        }
    }
}
