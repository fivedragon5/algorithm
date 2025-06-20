package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11058
 *
 * 제한)
 *  1 ≤ N ≤ 100
 *
 * 문제)
 * 크리보드의 버튼은 총 N번 눌러서 화면에 출력된 'A'개수의 최대값을 구하기
 *  크리보드 버튼 종류
 *   1. 화면에 A를 출력한다.
 *   2. Ctrl-A: 화면을 전체 선택한다
 *   3. Ctrl-C: 전체 선택한 내용을 버퍼에 복사한다
 *   4. Ctrl-V: 버퍼가 비어있지 않은 경우에는 화면에 출력된 문자열의 바로 뒤에 버퍼의 내용을 붙여넣는다.
 *
 * 풀이)
 *  DP
 *   1. dp[i] : i번 버튼을 눌렀을 경우 표시되는 화면의 'A' 촤대 갯수로 놓고 풀이
 *    - i <= 6 까지는 화면에 'A'를 입력하는게 최대값을 보장해줌
 *    - i > 6 경우
 *      - 복사, 붙여넣기 경우의 수를 비교해서 최대값 갱신 필요
 *      - 경우의 수
 *        - 복사 붙여넣기를 위해선 버튼을 최소 3번 눌러야 복사 붙여넣기를 할 수 있음
 *        - 버튼을 6번 이상 누를 경우에는 3번 눌렀을 경우를 반복 하면 됨
 *        - 따라서 dp[i]에서 버튼을 3번 ~ 5번 눌렀을 경우를 경우의 수로 두고 최대값을 갱신해준다
 *        - 버튼을 3번 눌렀을 경우 dp[i-3]의 2배
 *        - 버튼을 4번 눌렀을 경우 dp[i-4]의 3배
 *        - 버튼을 5번 눌렀을 경우 dp[i-45의 4배
 */

class Problem11058 {
    public static void main(String[] args) throws IOException {
        int N = input();
        if (N <= 4) {
            System.out.println(N);
            return;
        }

        long[] dp = new long[N+1];
        // dp 초기화
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;

        for (int i = 5; i <= N; i++) {
            dp[i] = dp[i-1] + 1; // 그냥 'A'를 추가했을 경우 처리
            for (int j = 3; j <= 5; j++) {
                // 버튼을 3번 누를 경우 i-3 2배 : dp[i-3] * 2
                // 버튼을 4번 누를 경우 i-3 3배 : dp[i-4] * 3
                // 버튼을 5번 누를 경우 i-3 4배 : dp[i-5] * 4
                dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
            }
        }
        System.out.println(dp[N]);
        return;
    }

    private static int input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
}
/*
100
1391569403904


 */
