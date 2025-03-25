package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/description/?envType=daily-question&envId=2025-03-25
 *
 * 제한)
 *  3 <= n <= 10^9
 *  3 <= rectangles.length <= 10^5
 *  0 <= rectangles[i][0] < rectangles[i][2] <= n
 *  0 <= rectangles[i][1] < rectangles[i][3] <= n
 *  No two rectangles overlap.
 *
 * 문제)
 *  rectangles[i] : (x1, y1, x2, y2)
 *   1. n * n 크기에 직사각형 좌표가 주어졌을 때, 조건을 만족하는지 반환 하기
 *    - 2개의 수평 또는 2개의 수직 절단면을 나누기
 *    - 절단으로 생긴 섹션에 각각 최소1개 이상의 사각형이 포함되어야 함
 *    - 모든 사각형은 정확히 하나의 섹션에 속해야 함
 *
 * 풀이)
 *  1. 직사각형을 x1 오름차순, x2 내림차순 정렬 해서 y = a 절단면 2개 이상 나오는지 확인
 *  2. x로 불가능할 경우 y로 정렬해서 절단면 확인
 *
 */

public class Question_20250325 {
    public static void main(String args[]) throws IOException {
        int n = 5; int[][] rectangles = {{1,0,5,2},{0,2,2,4},{3,2,5,3},{0,4,4,5}};
        System.out.println(checkValidCuts(n, rectangles));

        int n2 = 4; int[][] rectangles2 = {{0,0,1,1},{2,0,3,4},{0,2,2,3},{3,0,4,3}};
        System.out.println(checkValidCuts(n2, rectangles2));

        int n3 = 4; int[][] rectangles3 = {{0,2,2,4},{1,0,3,2},{2,2,3,4},{3,0,4,2},{3,2,4,4}};
        System.out.println(checkValidCuts(n3, rectangles3));
    }

    public static boolean checkValidCuts(int n, int[][] rectangles) {
        // x 부터 탐색
        Arrays.sort(rectangles, Comparator.comparingInt((int[] a) -> a[0])
                .thenComparing((int[] a) -> a[2], Comparator.reverseOrder()));

        int beforeX = rectangles[0][2];
        int count = 0;

        for (int[] rectangle : rectangles) {
            int startX = rectangle[0];
            int endX = rectangle[2];
            if (startX >= beforeX) {
                count++;
            }
            beforeX = Math.max(beforeX, endX);
        }

        if (count > 1) return true;

        // y
        Arrays.sort(rectangles, Comparator.comparingInt((int[] a) -> a[1])
                .thenComparing((int[] a) -> a[3], Comparator.reverseOrder()));

        int beforeY = rectangles[0][3];
        count = 0;

        for (int[] rectangle : rectangles) {
            int startY = rectangle[1];
            int endY = rectangle[3];
            if (startY >= beforeY) {
                count++;
            }
            beforeY = Math.max(beforeY, endY);
        }

        return count > 1;
    }
}
