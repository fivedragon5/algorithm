package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1034
 *
 * 제한)
 *  1 <= N, M <= 50
 *  0 <= K <= 1,000
 *
 * 문제)
 *  1. 램프가 N x M 격자판에 놓여 있다. 각 램프는 켜져 있거나 꺼져 있다.
 *  2. 각 열의 아래에는 스위치가 하나씩 달려있는데, 이 스위치를 누를 때마다 그 열에 있는 램프의 상태가 모두 바뀐다.
 *   - 켜져 있는 램프는 꺼지고, 꺼져 있는 램프는 켜진다.
 *  3. 스위치를 K번 누른 후에 켜져있는 행의 최대값을 구하기
 *   - 서로다른 스위치를 누르지 않아도 됨
 *
 * 풀이)
 *  1. 각 행의 램프 상태를 문자열로 변환하여 해시맵에 저장
 *  2. 각 행을 기준으로 해당 행을 모두 켤 수 있는지 확인
 *  3. 모두 켤 수 있다면, 해시맵에서 해당 패턴의 빈도수를 확인하여 최대값 갱신
 *  4. 최대값 출력
 *
 */
class Problem1034 {

    private static int N, M, K;
    private static int[][] lamps;
    private static Map<String, Integer> lampPatterns = new HashMap<>();

    public static void main(String args[]) throws IOException {
        input();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            int offCount = 0;
            String pattern = getPatternString(lamps[i]);
            for (int j = 0; j < M; j++) {
                if (lamps[i][j] == 0) {
                    offCount++;
                }
            }
            // 해당 행을 모두 켤 수 있는지 확인
            if (offCount == K || (offCount < K && (K - offCount) % 2 == 0)) {
                answer = Math.max(answer, lampPatterns.get(pattern));
            }
        }
        System.out.println(answer);
    }

    private static String getPatternString(int[] lampRow) {
        StringBuilder sb = new StringBuilder();
        for (int state : lampRow) {
            sb.append(state);
        }
        return sb.toString();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lamps = new int[N][M]; // 1 : on, 0 : off

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            lampPatterns.put(row, lampPatterns.getOrDefault(row, 0) + 1);
            for (int j = 0; j < M; j++) {
                lamps[i][j] = row.charAt(j) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        br.close();
    }
}
/*
3 2
01
10
10
1
 */
