package algorithm.code.week;

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
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[][] map = new String[12][6];

        for (int i = 0; i < 12; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            map[i] = a.split("");
        }

        for (String[] a : map) {
            System.out.println(Arrays.toString(a));
        }
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
