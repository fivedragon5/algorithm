package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제)
 * 상이 왕에게 접근할 수 있는 최소 접근 횟수를 구하시오.
 * 
 * 풀이)
 * 1. 상이 움직일 수 있는 방향 (11시 부터 시계방향으로 8방향) int[] dc, int[] dr 선언
 * 2. 처음 주어진 상위치를 시작으로 예외조건을 제외하고 BFS탐색 (8방향)
 *  - 예외조건)
 *   - 이미 방문한 위치
 *   - 상이 이동하는 경로에 왕이 있을경우
 * 3. 상이 이동한 위치에 왕이 있을경우 해당 스텝을 return
 * 4. step를 return하지 못한경우 -1을 return
 *
 * 제한)
 * 0 <= 행 <= 9
 * 0 <= 열 <= 8
 */
class Problem16509 {

    static int[] dc = {-2, 2, 3, 3, 2 ,-2, -3, -3};
    static int[] dr = {-3, -3, -2, 2, 3, 3, 2, -2};
    static int[] sangPosition = new int[2];
    static boolean[][] map = new boolean[10][9];

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sangPosition[0] = Integer.parseInt(st.nextToken());
        sangPosition[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int kingRow = Integer.parseInt(st.nextToken());
        int kingCol = Integer.parseInt(st.nextToken());

        map[sangPosition[0]][sangPosition[1]] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sangPosition[0], sangPosition[1], 0});

        int moveRow = 0;
        int moveCol = 0;
        boolean isCheckMate = false;

        while(queue.size() > 0) {
            int[] sang = queue.poll();

            int row = sang[0];
            int col = sang[1];
            int step = sang[2];

            if (row == kingRow && col == kingCol) {
                System.out.println(step);
                isCheckMate = true;
                break;
            }

            map[row][col] = true;

            for (int i = 0; i < 8; i++) {
                moveRow = row + dr[i];
                moveCol = col + dc[i];
                if (moveRow < 0 || moveCol < 0 || moveRow > 9 || moveCol > 8) {
                    continue;
                }
                if (map[moveRow][moveCol]) {
                    continue;
                }
                if (isMovePossible(kingRow, kingCol, row, col, i)) {
                    queue.add(new int[]{moveRow, moveCol, step + 1});
                }
            }
        }
        if (!isCheckMate) {
            System.out.println(-1);
        }

    }

    static boolean isMovePossible(int kingRow, int kingCol, int startRow, int startCol, int direction) {
        if (direction == 0) {
            if ((kingRow == startRow-1 && kingCol == startCol) || (kingRow == startRow-2 && kingCol == startCol-1)) return false;
        }
        else if (direction == 1) {
            if ((kingRow == startRow-1 && kingCol == startCol) || (kingRow == startRow-2 && kingCol == startCol+1)) return false;
        }
        else if (direction == 2) {
            if ((kingCol == startCol+1 && kingRow == startRow) || (kingCol == startCol+2 && kingRow == startRow-1)) return false;
        }
        else if (direction == 3) {
            if ((kingCol == startCol+1 && kingRow == startRow) || (kingCol == startCol+2 && kingRow == startRow+1)) return false;
        }
        else if (direction == 4) {
            if ((kingRow == startRow+1 && kingCol == startCol) || (kingRow == startRow+2 && kingCol == startCol+1)) return false;
        }
        else if (direction == 5) {
            if ((kingRow == startRow+1 && kingCol == startCol) || (kingRow == startRow+2 && kingCol == startCol-1)) return false;
        }
        else if (direction == 6) {
            if ((kingCol == startCol-1 && kingRow == startRow) || (kingCol == startCol-2 && kingRow == startRow+1)) return false;
        }
        else if (direction == 7) {
            if ((kingCol == startCol-1 && kingRow == startRow) || (kingCol == startCol-2 && kingRow == startRow-1)) return false;
        }
        return true;
    }
}

/*
  4 2
  2 5

  1
 */

