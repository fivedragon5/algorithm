package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1484
 * 제한)
 * 1 <= G <= 100,000
 * x
 * 문제)
 * G킬로그램은 성원이의 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것이다
 * x : 성원이의 현재 몸무게
 * y : 성원이가 기억하고 있던 몸무게
 * G = x^2 - y^2
 * G = (x+y)(x-y)
 *
 * 풀이)
 * 1. 투포인터로 제한범위내에서 값을 1씩 증가시키면서 조건에 맞는 답을 찾기
 * 2. x-y = 1 && x^2 - y^2 > G 일 경우 종료
 *
 */
class Problem1484 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken());
        int x = 2;
        int y = 1;
        List<Integer> answers = new ArrayList<>();
        while (y < 100000) {
            int squareX = x * x;
            int squareY = y * y;
            int calc = squareX - squareY;
            if (calc == G) {
                answers.add(x);
            }
            if (G >= calc) {
                x++;
            } else if (x - y == 1) {
                break;
            } else {
                y++;
            }
        }
        if (answers.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int answer : answers) {
                System.out.println(answer);
            }
        }
    }
}
/*
5 5
0 0 0 0 0
0 10 10 10 0
0 9 9 9 0
0 10 10 10 0
0 0 0 0 0
 */
