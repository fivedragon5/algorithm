package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem14500 {

    /**
      4 ≤ N, M ≤ 500
      0 < number < 1000
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] array = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //시작점을 기준으로 우측 or 아래로 탐색하기마
    }
}

/**
 5 5
 1 2 3 4 5
 5 4 3 2 1
 2 3 4 5 6
 6 5 4 3 2
 1 2 1 2 1
 19

 4 5
 1 2 3 4 5
 1 2 3 4 5
 1 2 3 4 5
 1 2 3 4 5
 20

 4 10
 1 2 1 2 1 2 1 2 1 2
 2 1 2 1 2 1 2 1 2 1
 1 2 1 2 1 2 1 2 1 2
 2 1 2 1 2 1 2 1 2 1
 7

 */
