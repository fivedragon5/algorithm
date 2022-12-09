package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문제)
 * 상이 왕에게 접근할 수 있는 최소 접근 횟수를 구하시오.
 *
 * 제한)
 * 0 <= 행 <= 9
 * 0 <= 열 <= 8
 */
class Problem16509 {

    static int[] dx = {-2, 2, 3, 3, 2 ,-2, -3, -3};
    static int[] dy = {3, 3, 2, -2, -3, -3, -2, 2};
    static int[] kingPosition = new int[2];
    static int[] sangPosition = new int[2];
    static boolean[][] map = new boolean[10][9];

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sangPosition[0] = Integer.parseInt(st.nextToken());
        sangPosition[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        kingPosition[0] = Integer.parseInt(st.nextToken());
        kingPosition[1] = Integer.parseInt(st.nextToken());

        map[sangPosition[0]][sangPosition[1]] = true;


        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{sangPosition[0], sangPosition[1], 0});

        int moveX = 0;
        int moveY = 0;

        while(stack.size() > 0) {
            if (!stack.isEmpty()) {
                int[] sang = stack.pop();

                int x = sang[1];
                int y = sang[0];
                int step = sang[2];

                if (x == kingPosition[1] && y == kingPosition[0]) {
                    System.out.println(step);
                    return;
                }

                for (int i = 0; i < 8; i++) {
                    moveX = x + dx[i];
                    moveY = y + dy[i];
                    if (moveX < 0 || moveY < 0 || moveX > 8 || moveY > 9) {
                        continue;
                    }
                    if (map[moveY][moveX]) {
                        continue;
                    }

                    stack.add(new int[]{moveY, moveX, step + 1});

                    map[moveY][moveX] = true;
                }

            }
        }
    }
}

/*
  4 2
  2 5

  1
 */

