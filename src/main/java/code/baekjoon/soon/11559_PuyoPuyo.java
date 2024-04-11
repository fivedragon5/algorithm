package code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 제한)
 * 6 x 12
 * . : 빈공간이고
 * !. : 뿌요
 * R:빨강, G:초록, B:파랑, P:보라, Y:노랑
 * 뿌요 아래에 빈 칸이 있는 경우는 없다.
 */

class Problem11559 {

    static String [][] puyoMap = new String[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int maxHeight = 11;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 12; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            puyoMap[i] = a.split("");
        }

        for (String[] a : puyoMap) {
            System.out.println(Arrays.toString(a));
        }
    }

    // 폭파 로직
    static boolean explode() {

        for (int i = 11; i <= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j]) {

                }
            }
        }

        return false;
    }

    // 이동 로직
    static void moveDownPuyo() {

    }

    static void checkPuyo(String color) {

    }
}
/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.

3
 */
