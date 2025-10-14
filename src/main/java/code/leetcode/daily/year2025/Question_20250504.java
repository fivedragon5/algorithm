package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/number-of-equivalent-domino-pairs/description/?envType=daily-question&envId=2025-05-04
 *
 * 제한)
 *  1 <= dominoes.length <= 4 * 10^4
 *  dominoes[i].length == 2
 *  1 <= dominoes[i][j] <= 9
 *
 * 문제)
 *  1. dominoes 배열이 주어질 때, dominoes[i]와 dominoes[j]가 같은 도미노인지 판별하라.
 *   - i < j 만족
 *   - dominoes[i] = [a, b]와 dominoes[j] = [c, d]일 때, a == c && b == d 또는 a == d && b == c이면 같은 도미노이다.
 *  2. 같은 도미노의 쌍의 개수를 구하라.
 *
 * 풀이)
 *  1. dominoes[i]의 두 숫자를 정렬하여 인덱스를 구한다.
 *  2. 인덱스를 키로 하는 count 배열을 만들어서, 같은 도미노의 개수를 구한다.
 *  3. count 배열의 각 인덱스에 대해, count[i] - 1 만큼 result에 더한다.
 *
 */

public class Question_20250504 {
    public static void main(String args[]) throws IOException {
        int[][] dominoes = new int[][]{{1,2},{2,1},{3,4},{5,6}}; // 1
        System.out.println(numEquivDominoPairs(dominoes));

        int[][] dominoes2 = new int[][]{{1,2},{1,2},{1,1},{1,2},{2,2}}; // 1
        System.out.println(numEquivDominoPairs(dominoes2));
    }

    public static int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        int result = 0;

        for (int[] domino : dominoes) {
            int index = Math.min(domino[0], domino[1]) * 10 + Math.max(domino[0], domino[1]);
            result += count[index]++;
        }

        return result;
    }
}
