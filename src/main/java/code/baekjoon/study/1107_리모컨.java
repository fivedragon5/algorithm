package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/1107
 * 0 <= N <= 500,000
 * 0 <= M <= 10
 *
 * 1. 리모컨의 버튼을 눌러서 채널 N으로 이동하려고 함
 * 2. 현재(시작) 채널은 100번
 * 3. 채널은 0에서 500,000까지
 * 4. 고장난 버튼이 있음
 * 5. 고장난 버튼을 누르지 않고 이동할 수 있는 채널로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지 구함
 */
class Problem1107 {

    private static List<Integer> remoteControlNumbers;
    private static int min = Integer.MAX_VALUE;
    private static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        // 고장난 번호가 없을 경우에대한 예외처리
        if (M == 0) {
            min = Math.min(min, Math.abs(N - 100));
            min = Math.min(min, String.valueOf(N).length());
            System.out.println(min);
            return;
        }

        st = new StringTokenizer(br.readLine());

        // 모든 숫자를 Set
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        // 고장난 숫자를 제거
        for (int i = 0; i < M; i++) {
            numbers.remove(Integer.parseInt(st.nextToken()));
        }

        // 100번에서 N까지의 거리
        min = Math.min(min, Math.abs(N - 100));
        remoteControlNumbers = numbers.stream().toList();

        get("");
        System.out.println(min);
    }

    static void get(String str) {
        for (Integer number : remoteControlNumbers) {
            String newNumber = str + number;
            min = Math.min(min, Math.abs(N - Integer.parseInt(newNumber)) + newNumber.length());
            if (newNumber.length() < 6) {
                get(newNumber);
            }
        }
    }
}
/*
5457
3
6 7 8

6

0
0

97
0
 */
