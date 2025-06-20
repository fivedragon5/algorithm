package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1790
 * 제한)
 * 1 <= N <= 100,000,000
 * 1 <= k <= 1,000,000,000
 * 문제)
 * 1. 1 ~ N 까지의 수를 이어 붙여 만든다
 * 2. 붙여 만든 수에 k번째 위치한 수를 출력
 * 3. 이어 붙여 만든 수의 길이보다 k가 더 클 경우 -1 출력
 *
 * 풀이)
 * 1.
 *
 */
class Problem1790 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long currentNumber = 0; // 현재까지 계산한 총 자리 수
        // 1자리 : 1 ~ 9    | 9개
        // 2자리 : 10 ~ 99  | 90개
        // .....
        long numberCount = 1; // 자리 수
        long repeat = 9; // 반복되는 수

        while (k > numberCount * repeat) {
            k -= numberCount * repeat;
            currentNumber += repeat;
            numberCount++;
            repeat = repeat * 10;
        }

        // 어떤 수에 속하는지 계산
        currentNumber = currentNumber + 1 + (k - 1) / numberCount;

        // K번째 숫자가 존재하지 않으면 -1 반환
        if (currentNumber > N) {
            System.out.println(-1);
        } else {
            String result = String.valueOf(currentNumber);
            System.out.println(result.charAt((int) ((k - 1) % numberCount)));
        }
    }
}
/*
20 23
6

20 10

10 99999
 */
