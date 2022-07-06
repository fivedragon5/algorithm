package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem16967 {
    /**
2 4 1 1
1 2 3 4 0
5 7 9 11 4
0 5 6 7 8
     */
    public static void main(String srgs[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] arrayB = new int[H+X][W+Y];
        int[][] arrayA = new int[H][W];

        for (int i = 0; i < H+X ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W+Y ; j++) {
                arrayB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < H + X ; i++) {
            for (int j = 0; j < W + Y ; j++) {
                if (i < X || j < Y) {
                    if(i < H && j < W) {
                        arrayA[i][j] = arrayB[i][j];
                    }
                }
                if (i >= H || j >= W) {
                    if (i-X >= 0 && j-Y >= 0) {
                        arrayA[i-X][j-Y] = arrayB[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(arrayA[i][j]);
                if (j < W-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
