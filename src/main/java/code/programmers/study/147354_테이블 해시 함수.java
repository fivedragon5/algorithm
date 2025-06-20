package code.programmers.study;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/147354
 *
 * 제한)
 *  1 ≤ data.length ≤ 2,500
 *  1 ≤ data[i] ≤ 500
 *  1 ≤ data[i][j] ≤ 1,000,000
 *      data[i][j]는 i + 1 번째 튜플의 j + 1 번째 컬럼의 값을 의미합니다.
 *  1 ≤ col ≤ data[i].length
 *  1 ≤ row_begin ≤ row_end ≤ data.length
 *
 * 문제)
 *  1. data[i][col] 기준으로 data를 오름 차순 정렬 한다.
 *   - 단 data[i][col] 값이 같을 경우 data[i][0] 기준으로 내림차순 정렬
 *  2. 정렬한 데이터 중 row_begin번째부터 row_end번째까지의 데이터를 추출
 *  3. 추출한 데이터의 각 행의 i번째 원소를 i + 1로 나눈 나머지들의 합을 각각 구한다.
 *  4. 구한 나머지들을 XOR 연산을 해서 최종 결과를 구한다.
 *
 * 풀이)
 *  구현
 *      1. data를 정렬할 때, Comparator를 사용하여 col 기준으로 오름차순 정렬하고,
 *         col 값이 같을 경우 0번째 원소 기준으로 내림차순 정렬한다.
 *      2. row_begin부터 row_end까지의 행을 순회하면서,
 *         각 행의 원소를 i + 1로 나눈 나머지를 구하고,
 *         그 나머지들의 합을 구한다.
 *      3. 구한 합들을 XOR 연산하여 최종 결과를 반환한다.
 *
 */
class Lesson147354 {
    public static void main(String[] args) {
        int[][] data = new int[][]{{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        System.out.println(solution(data, col, row_begin, row_end));
    }

    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return Integer.compare(b[0], a[0]); // 내림차순
            }
            return Integer.compare(a[col - 1], b[col - 1]); // 오름차순
        });

        int result = 0;

        for (int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for (int j = 0; j < data[i].length; j++) {
                sum += data[i][j] % (i + 1);
            }
            result ^= sum; // XOR 연산
        }
        return result;
    }
}
