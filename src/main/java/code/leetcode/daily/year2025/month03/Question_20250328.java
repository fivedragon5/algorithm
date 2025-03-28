package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/?envType=daily-question&envId=2025-03-28
 *
 * 제한)
 *  m == grid.length
 *  n == grid[i].length
 *  2 <= m, n <= 1000
 *  4 <= m * n <= 10^5
 *  k == queries.length
 *  1 <= k <= 10^4
 *  1 <= grid[i][j], queries[i] <= 10^6
 *
 * 문제)
 *  1. m * n 크기의 정수 행렬 grid 와 queries가 주어짐
 *  2. queries[i] 가 현재 셀의 값보다 크면, 해당 셀을 처음 방문 하는경우 1점을 얻고 상,하,좌,우 4 방향으로 이동 가능
 *   - 그렇지 않으면 종료
 *  3. 과정이 끝난 후 answer[i]는 얻을 수 있는 최대 점수를 반환
 *   - 각 쿼리마다 동일한 셀을 여러변 방문 할 수 있음
 *
 * 풀이)
 *
 */

public class Question_20250328 {
    public static void main(String args[]) throws IOException {

        int[][] grid = new int[][]{{1,2,3},{2,5,7},{3,5,1}};
        int[] queries = new int[]{5,6,2};
        System.out.println(maxPoints(grid, queries).toString());

        int[][] grid2 = new int[][]{{5,2,1},{1,1,2}};
        int[] queries2 = new int[]{3};
        System.out.println(maxPoints(grid2, queries2).toString());
    }

    public static int[] maxPoints(int[][] grid, int[] queries) {
        int rows = grid.length, cols = grid[0].length, qLen = queries.length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Store original indices and sort queries
        int[][] qIndex = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            qIndex[i] = new int[]{queries[i], i};
        }
        Arrays.sort(qIndex, Comparator.comparingInt(a -> a[0]));

        // Min-heap (PriorityQueue) to store (value, row, col)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;

        int[] result = new int[qLen];
        int count = 0;

        for (int[] q : qIndex) {
            int query = q[0], index = q[1];

            // Process the heap while top element is less than the query value
            while (!pq.isEmpty() && pq.peek()[0] < query) {
                int[] cell = pq.poll();
                int r = cell[1], c = cell[2];
                count++;

                // Explore neighbors
                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                        pq.offer(new int[]{grid[nr][nc], nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            result[index] = count;
        }

        return result;
    }
}
