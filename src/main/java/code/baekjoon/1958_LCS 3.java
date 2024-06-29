package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/1958
 * 1 <= word.length <= 100
 *
 * 1. 3개의 문자열이 주어짐
 * 2. 3개의 문자열의 공통 LCS를 구함
 */
class Problem1958 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][][] dp = new int[101][101][101];
        String[] words = new String[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
        }

        for (int i = 0 ; i < words[0].length(); i++) {
            for (int j = 0; j < words[1].length(); j++) {
                for (int k = 0; k < words[2].length(); k++) {
                    if (words[0].charAt(i) == words[1].charAt(j) && words[1].charAt(j) == words[2].charAt(k)) {
                        // 문자열이 전부 같을경우 이전 최장 공통 수열 길이의 1을 더함
                        dp[i+1][j+1][k+1] = dp[i][j][k] + 1;
                    } else { // 일치하지 않을 경우, 지금 까지의 최대값 저장
                        dp[i+1][j+1][k+1] = Math.max(dp[i][j+1][k+1], Math.max(dp[i+1][j][k+1], dp[i+1][j+1][k]));
                    }
                }
            }
        }
        System.out.println(dp[words[0].length()][words[1].length()][words[2].length()]);
    }
}
/*
abcdefghijklmn
bdefg
efg

aaab
aab
ab
 */
