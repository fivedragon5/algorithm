package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/increment-submatrices-by-one/description/?envType=daily-question&envId=2025-11-14
 *
 * 제한)
 *  1 <= n <= 500
 *  1 <= queries.length <= 10^4
 *  0 <= row1(i) <= row2(i) < n
 *  0 <= col1(i) <= col2(i) < n
 *
 * 문제)
 *  한국어 번역
 *  1. 양의 정수 n이 주어진다. 이는 처음에 0으로 채워진 n x n 2차원 행렬이 있음을 나타낸다.
 *  2. 2차원 정수 배열 query도 주어진다. 각 query[i] = [row1i, col1i, row2i, col2i]에 대해 다음 연산을 수행한다
 *   - (row1(i), col1(i))를 왼쪽 상단 모서리로 하고 (row2(i), col2(i))를
 *     오른쪽 하단 모서리로 하는 부분 행렬의 모든 요소에 1을 더한다.
 *   - 즉, row1(i) <= x <= row2(i) 및 col1(i) <= y <= col2(i)에 대해 mat[x][y]에 1을 더한다.
 *  3. 모든 쿼리를 수행한 후 연산된 2차원 행렬을 반환한다.
 *
 * 풀이)
 *  직접 모든 쿼리에 대해 반복문을 돌릴 경우
 *   - n최대 500 -> n^2 = 250,000
 *   - queries 최대 10,000 -> n^2 * queries = 2
 *   -> 2,500,000,000 -> 25억 -> 시간 초과
 *  차분 배열 사용
 *
 */

public class Question_20251114 {
    public static void main(String args[]) throws IOException {
        int n = 3;
        int[][] queries = {{1,1,2,2}, {0,0,1,1}};
        System.out.println(rangeAddQueries(n, queries));

        n = 2;
        queries = new int[][]{{0,0,1,1}};
        System.out.println(rangeAddQueries(n, queries));
    }

    public static int[][] rangeAddQueries(int n, int[][] queries) {
        // 차분배열 초기화
        int[][] diffArray = new int[n + 1][n + 1];

        for (int [] query : queries) {
            int row1 = query[0];
            int col1 = query[1];
            int row2 = query[2];
            int col2 = query[3];

            diffArray[row1][col1] += 1;
            diffArray[row2 + 1][col2 + 1] += 1;
            diffArray[row1][col2 + 1] -= 1;
            diffArray[row2 + 1][col1] -= 1;
        }

        // 차분배열 행 누적합
        for (int i = 0; i < n; i++) {
            for (int row = 1; row < n; row++) {
                diffArray[row][i] += diffArray[row - 1][i];
            }
        }

        // 차분배열 열 누적합
        for (int i = 0; i < n; i++) {
            for (int col = 1; col < n; col++) {
                diffArray[i][col] += diffArray[i][col - 1];
            }
        }

        int result[][] = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                result[row][col] = diffArray[row][col];
            }
        }

        return result;
    }
}
