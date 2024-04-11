package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem17182 {
    /**
      3 0
      0 30 1
      1 0 29
      28 1 0

     2 0
     0 0
     0 0
     */

    static int[][] planet;
    static boolean[] visited;
    static int min = 10000;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        planet = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                planet[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //k: 거쳐가는 행성, i:출발지, j:도착지
        for (int k = 0; k < N; k++ ) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(planet[i][k] + planet[k][j] < planet[i][j]) {
                        planet[i][j] = planet[i][k] + planet[k][j];
                    }
                }
            }
        }

        backTracking(0, K, 0, N);

        System.out.println(min);
    }

    static void backTracking(int depth, int start, int sumDistance,int N) {
        if (depth >= N) {
            min = sumDistance < min ? sumDistance : min;
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                backTracking(depth+1, i, sumDistance + planet[start][i], N);
                visited[i] = false;
            }
        }
    }
}
