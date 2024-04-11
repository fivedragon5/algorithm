package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 * T -> S로 바꾸기
 *
 * 풀이)
 * 1. T의 끝의 글자가 A인경우
 *  - 'A' 삭제
 * 2. T의 끝의 글자가 B인경우
 *  - 'B' 삭제후 T 역순으로 재배열
 * 
 * 제한)
 * 1 ≤ S.length() ≤ 999
 * 2 ≤ T.length() ≤ 1000
 * S.length()< T.length()
 */
class Problem12904 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String T = st.nextToken();

        StringBuilder sb = new StringBuilder(T);

        int SLength = S.length();

        while (sb.length() > SLength) {
            char ch = sb.charAt(sb.length() - 1);
            sb.delete(sb.length()-1, sb.length());
            if (ch != 'A') {
                sb.reverse();
            }
        }

        if (S.equals(sb.toString())) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }
}

/*
B
ABBA

1

AB
ABBA

0
 */

