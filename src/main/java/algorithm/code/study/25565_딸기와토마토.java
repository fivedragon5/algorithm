package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Probleam25565 {

    /*
        첫 번째 줄에 N, M, K가 공백으로 구분되어 주어진다.
        1 <= N,M <= 2,000
        1 <= K <= max(N,M)

        3 4 3
        0 1 0 0
        0 1 1 1
        0 1 0 0

        1
        2 2

        3 4 4
        1 1 1 1
        1 1 1 1
        0 0 0 0

        0
     */


    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int seed = Integer.parseInt(st.nextToken());
                map[i][j] = seed;
                if(seed == 1) count++;
            }
        }

        if (count == K*2) {
            //겹치는곳 없음
            System.out.println(0);
        }
        else if (count == K*2 - 1){
            // count 가 2K-1 이면 1곳
        }
        else {
            // 겹치는곳이 2곳 이상
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) {

                }
            }
        }
    }
}
