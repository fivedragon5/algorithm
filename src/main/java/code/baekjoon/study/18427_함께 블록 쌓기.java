package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18427
 * 제한)
 * 1 ≤ N ≤ 50
 * 1 ≤ M ≤ 10
 * 1 ≤ H ≤ 1,000
 *
 * 문제)
 *  1. N명의 학생이 있고, 각 학생은 최대 M개의 블럭을 가지고 있다.
 *  2. 학생당 최대 1개의 블럭을 사용할 수 있다
 *  3. 학생들이 가지고 있는 블럭의 높이를 합쳐서 H를 만들 수 있는 경우의 수를 구하기
 *  4. 경우의 수 를 10,007 로 나눈 나머지를 출력하기
 *
 * 풀이)
 *  1. DFS 시간초과
 *    학생을 하나씩 순회하면서 학생이 블럭을 선택, 선택하지 않을 경우로 나누어 재귀 함수 호출
 *     - 50(학생 수) * 10(블럭 수) = 500 | 2^500
 *  2. DP
 *   - DP[i][j] : i번째 학생까지 고려했을 경우, j(무게)를 만드는 경우의 수
 */
class Problem18427 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] DP;

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 학생당 가지고 있을 수 있는 최대 블럭 수
        int H = Integer.parseInt(st.nextToken()); // 블럭의 높이

        List<List<Integer>> blocks = new java.util.ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            blocks.add(new java.util.ArrayList<>());
            String[] blockSplit = line.split(" ");
            for (String block : blockSplit) {
                blocks.get(i).add(Integer.parseInt(block));
            }
        }

        DP = new int[N + 1][H + 1];
        // DP[i][j] = i번째 학생까지 고려했을 때, 높이 j를 만드는 경우의 수
        DP[0][0] = 1; // 초기값 설정

        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j <= H; j++) {
                DP[i + 1][j] = (DP[i + 1][j] + DP[i][j]) % 10007;

                // 이 학생이 블럭을 하나 선택하는 경우
                for (int block : blocks.get(i)) {
                    if (block + j <= H) {
                        DP[i+1][block + j] = (DP[i+1][block + j] + DP[i][j]) % 10007;
                    }
                }
            }
        }

        System.out.println(DP[N][H]);
    }
}
/*
3 3 5
2 3 5
3 5
1 2 3

50 10 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000
1 2 3 4 5 6 7 8 9 1000

 */
