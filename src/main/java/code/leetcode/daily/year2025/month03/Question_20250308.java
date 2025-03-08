package code.leetcode.daily.year2025.month03;

import java.io.IOException;

/**
 * https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description/?envType=daily-question&envId=2025-03-08
 *
 * 제한)
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] is either 'W' or 'B'.
 * 1 <= k <= n
 *
 * 문제)
 *  1. W, B 로 이루어진 문자열 blocks, 1~100 사이의 정수 k 가 주어짐
 *  2. 문자열 blocks중 몇개의 W를 B로 바꿔야 연속된 B가 k개 나오는지 최소한의 연산 수 를 구하기
 *
 * 풀이)
 *  1. blocks 배열을 index:0 ~ k-1 까지 추출해서 배열의 'B'의 수(blockCount)를 새준다.
 *  2. k-blockCount = 'W'의 수(answer)
 *  3. index를 1씩 증가시켜가며 blockCount를 갱신
 *   - blockCount를 갱신하면서 k-blockCount 작아질 경우 answer를 최소값으로 갱신해준다
 */

public class Question_20250308 {
    public static void main(String args[]) throws IOException {
        String blocks = "WBBWWBBWBW"; int k = 7;
        System.out.println(minimumRecolors(blocks, k)); // 3

        blocks = "WBWBBBW"; k = 2;
        System.out.println(minimumRecolors(blocks, k)); // 0
    }

    public static int minimumRecolors(String blocks, int k) {
        int blockCount = 0;
        int answer = k;
        for (int i = 0; i < k; i++) {
            char color = blocks.charAt(i);
            if (color == 'B') {
                blockCount++;
            }
        }
        answer = Math.min(answer, k - blockCount);

        for (int i = k; i < blocks.length(); i++) {
            char rightColor = blocks.charAt(i);
            char leftColor = blocks.charAt(i - k);
            if (rightColor != leftColor) {
                if (rightColor == 'B') {
                    answer = Math.min(answer, k - ++blockCount);
                } else {
                   blockCount--;
                }
            }
        }
        return answer;
    }
}
