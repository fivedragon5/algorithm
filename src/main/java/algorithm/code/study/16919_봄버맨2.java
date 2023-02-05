package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * //n이 1일때 처리 해주기
 * //나머지는 반전처리
 *
 * 제한)
 * 1 ≤ R, C ≤ 200
 * 1 ≤ N ≤ 109
 */
class Problem16919 {

    static int R, C, N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()) % 4;

        StringBuilder firstMap = new StringBuilder();
        ArrayList<int[]> booms = new ArrayList<>();
        Character[][] map = new Character[R][C];
        for (Character[] c : map) {
            Arrays.fill(c, 'O');
        }

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            firstMap.append(str + "\n");
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                if (ch == 'O') booms.add(new int[]{i, j});
            }
        }

        for (int[] boom : booms) {
            int x = boom[0];
            int y = boom[1];

            map[x][y] = '.';

            for (int i = 0; i < 4; i++) {
                int moveX = x + dx[i];
                int moveY = y + dy[i];
                if (moveX >= 0 && moveY >= 0 && moveX < R && moveY < C) {
                    map[moveX][moveY] = '.';
                }
            }
        }

        switch (N) {
            case 0:
            case 2: {
                for (int i = 0; i < R; i++) {
                    System.out.println("O".repeat(C));
                }
                break;
            }
            case 1: {
                System.out.println(firstMap.delete(firstMap.length() - 1 , firstMap.length()));
                break;
            }
            default: {
                for (Character[] m : map) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : m) {
                        sb.append(c);
                    }
                    System.out.println(sb);
                }
                break;
            }
        }
    }
}
/*
6 7 3
.......
...O...
....O..
.......
OO.....
OO.....

OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO

2 2 5
O.
O.

 */

