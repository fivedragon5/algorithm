package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/?envType=daily-question&envId=2025-03-26
 *
 * 제한)
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 10^5
 *  1 <= m * n <= 10^5
 *  1 <= x, grid[i][j] <= 10^4
 *
 * 문제)
 *  1. 정수 grid의 요소들을 모두 같은 원소로 만들기
 *  2. 주어진 x를 더하거나 빼서 만든다
 *   - 그리드의 모든 숫자가 동일하도록 만드는 최소 연산 횟수를 반환
 *  3. 반약 모든 숫자를 동일하게 만들 수 없다면 -1을 반환.
 *
 * 풀이)
 *  1. grid의 값들을 List<Integer>에 저장
 *   - 저장하면서 첫번째와 앞으로 추가될 원소들의 차이를 x와 나머지 연산 해서 0이 안될 경우 -1 반환
 *  2. List값을 오름차순 정렬
 *  3. 모든 값을 중간 값으로 변한하기 위한 필요한 연산 수 를 count에 저장
 *
 */

public class Question_20250326 {
    public static void main(String args[]) throws IOException {

        int x = 2; int[][] grid = {{2,4},{6,8}};
        System.out.println(minOperations(grid, x));

        int x2 = 1; int[][] grid2 = {{1,5},{2,3}};
        System.out.println(minOperations(grid2, x2));

        int x3 = 2; int[][] grid3 = {{1,2},{3,4}};
        System.out.println(minOperations(grid3, x3));
    }

    public static int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        int firstValue = grid[0][0];
        for (int[] m : grid) {
            for (int n : m) {
                if (Math.abs(n - firstValue) % x != 0) {
                    return -1;
                }
                list.add(n);
            }
        }
        Collections.sort(list);
        int midValue = list.get(list.size() / 2);
        int count = 0;

        for (int value : list) {
            count += Math.abs(midValue - value) / x;
        }

        return count;
    }
}
