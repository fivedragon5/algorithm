package code.leetcode.daily;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-total-number-of-colored-cells/description/?envType=daily-question&envId=2025-03-05
 *
 * 제한)
 * 1 <= n <= 10^5
 *
 * 문제)
 *  1. 2차원 무한 격자에서 특정한 규칙에따라 셀을 색칠한다
 *   - 임의의 한 셀을 파란색으로 칠한다
 *   - 기존 파란색 셀과 인접한 네게의 셀을 새롭게 파란색으로 칠함
 *   - 기존에 파란색으로 칠해진 모든 셀과 인접한 모든 방향의 셀을 추가로 파란색으로 칠햄
 *  이후 계속 시간이 지날수록 영역이 점점 식자 모양에서 정 사각형을 확장
 *  2. n분이 지낫을때 색칠된 셀의 수를 반환하기
 *
 * 풀이)
 *  1. n이 1일때 부터 4일때까지 셀의 수를 구해서 규칙을 찾기
 *  2. 1n = 1, 2n = 5, 3n = 13
 *   = (n - 1) * 4 씩 증가
 *  3. 점화식 도출 후 계산 y = 2n^2 - 2n + 1
 *  일반적인 방법은
 *      while문을 n까지 돌려 기존 n = 1n + (n-1) * 4 로 계산해도 가능
 */

public class Question_20250305 {
    public static void main(String args[]) throws IOException {
        int n = 1;
        System.out.println(coloredCells(n));

        n =  2;
        System.out.println(coloredCells(n));

        n = 3;
        System.out.println(coloredCells(n));

        n = 4;
        System.out.println(coloredCells(n));
    }

    public static long coloredCells(int n) {
        return (2L * n * n) - (2L * n) + 1;
    }
}
