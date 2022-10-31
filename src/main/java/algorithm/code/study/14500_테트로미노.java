package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Problem14500 {

    static int MAX = 0;
    static int N, M; //y, x
    static int[][] map;

    /**
      4 ≤ N, M ≤ 500
      0 < number < 1000
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int sum = map[i][j];
                List<String> list = new LinkedList<>();
                list.add("(" + j + "," + i + ")");
                solve(j, i, 1, sum, list);
            }
        }

        System.out.println("a"+MAX);

        
    }

    static void solve(int x, int y, int step, int sum, List<String> list) {

        if (list.size() == 4) {
            System.out.println(sum + "/" + list.toString());
            MAX = Math.max(MAX, sum);
            return;
        }

        if (x+1 < M) {
            List<String> listX = list;
            listX.add("(" +(x+1) + "," + y + ")");
            solve(x+1, y, step+1, sum + map[y][x+1], listX);
        }

        if (y+1 < N) {
            List<String> listY = list;
            listY.add("(" + x + "," + (y+1) + ")");
            solve(x, y+1, step+1, sum + map[y+1][x], listY);
        }
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
