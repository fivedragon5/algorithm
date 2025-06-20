package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16639
 * 제한)
 *   1 ≤ N ≤ 19
 *   0 ≤ 수식에 포함된 정수 ≤ 9
 *   -2^31 ≤ 정답 ≤ 2^31
 *   연산자(+, -, *)
 *
 * 문제)
 *  1. 길이가 N인 수식이 주어진다.
 *  2. 수식에 괄호를 적절히 추가해 만들수 있는 식의 결과의 최대값을 구하기
 *
 * 풀이)
 *  DP
 *
 *  각 구간의 최대, 최대 값을 구하기 위해 DP 테이블을 만든다.
 *
 *  3+8*7-9*2
 *  3               : 3
 *  3+8             : 11
 *  (3+8)*7         : 77
 *  (3+8)*7-9       : 68
 *  ((3+8)*7-9)*2   : 136
 *
 *  1. 숫자 numbers, 수식 ops 배열에 저장
 *  2. DP 테이블을 만들어서 DP_MAX[][], DP_MIN[][] : i ~ j 번째 숫자까지의 부분 수식의 최댓, 최솟값 배열 생성
 *  3. DP 테이블 초기값 설정
 *  4. DP 테이블을 채우기
 *   - '+' : i ~ k 구간까지의 최대값 + k+1 ~ j 구간까지의 최대값
 *   - '-' : i ~ k 구간까지의 최대값 - k+1 ~ j 구간까지의 최소값
 *   - '*' : 4가지 케이스 최대값 최소값 비교 (최대 * 최대, 최소 * 최소, 최대 * 최소, 최소 * 최대)
 */
class Problem16639 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] DP_MAX;
    private static int[][] DP_MIN;

    public static void main(String args[]) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수식의 길이
        st = new StringTokenizer(br.readLine());
        String formula = st.nextToken(); // 수식

        int numberCount = (N + 1) / 2;
        int[] numbers = new int[numberCount];
        char[] ops = new char[numberCount - 1];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                numbers[i / 2] = formula.charAt(i) - '0';
            } else {
                ops[i / 2] = formula.charAt(i);
            }
        }

        DP_MAX = new int[numberCount][numberCount]; // DP_MAX[i][j] : i ~ j 번째 숫자까지의 부분 수식의 최댓값
        DP_MIN = new int[numberCount][numberCount]; // DP_MIN[i][j] : i ~ j 번째 숫자까지의 부분 수식의 최솟값

        for (int i = 0; i < numberCount; i++) {
            DP_MAX[i][i] = numbers[i];
            DP_MIN[i][i] = numbers[i];
        }

        for (int len = 2; len <= numberCount; len++) { // 길이 2부터 전체까지
            for (int i = 0; i <= numberCount - len; i++) {
                int j = i + len - 1;
                DP_MAX[i][j] = Integer.MIN_VALUE;
                DP_MIN[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    char op = ops[k];
                    switch (op) {
                        case '+':
                            DP_MAX[i][j] = Math.max(DP_MAX[i][j], DP_MAX[i][k] + DP_MAX[k + 1][j]);
                            DP_MIN[i][j] = Math.min(DP_MIN[i][j], DP_MIN[i][k] + DP_MIN[k + 1][j]);
                            break;
                        case '-':
                            DP_MAX[i][j] = Math.max(DP_MAX[i][j], DP_MAX[i][k] - DP_MIN[k + 1][j]);
                            DP_MIN[i][j] = Math.min(DP_MIN[i][j], DP_MIN[i][k] - DP_MAX[k + 1][j]);
                            break;
                        case '*':
                            int result1 = calc(DP_MAX[i][k], DP_MAX[k+1][j], op);
                            int result2 = calc(DP_MAX[i][k], DP_MIN[k+1][j], op);
                            int result3 = calc(DP_MIN[i][k], DP_MAX[k+1][j], op);
                            int result4 = calc(DP_MIN[i][k], DP_MIN[k+1][j], op);
                            DP_MAX[i][j] = Math.max(DP_MAX[i][j] ,Math.max(Math.max(result1, result2), Math.max(result3, result4)));
                            DP_MIN[i][j] = Math.min(DP_MIN[i][j], Math.min(Math.min(result1, result2), Math.min(result3, result4)));
                    }
                }
            }
        }
        System.out.println(DP_MAX[0][numberCount - 1]);
    }

    private static int calc(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
/*
9
3+8*7-9*2

136

5
8*3+5

64

7
8*3+5+2

80

19
1*2+3*4*5-6*7*8*9*0

426384

19
1-9-1-9-1-9-1-9-1-9

32
 */
