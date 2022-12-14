package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제)
 * 1.반복되지 않은 문자열중 가장 긴 문자열을 출력
 *
 * 풀이)
 * 1. 문자열길이를 반으로 나눈 부분까지 for문을 돌면서 조건 확인
 *  - 문자열 전체가 회문인지 확인 (isRepeat)
 *  - 문자열 전체가 같은 문자인지 확인 (isAllEquals)
 *  
 * 2. 조건을 확인하여 정답 출력
 *  2-1 주어진 문자열이 회문이 아닐경우 
 *    - 전체가 회문이 아님 따라서 주어진 문자열 길이를 출력
 *  2-2 주어진 문자열이 회문일경우
 *    - 하나의 문자로 이루어진 문자열일경우 -1출력
 *    - 아닐경우 전체문자열 길이의 -1 한 값을 출력
 * 
 *
 * 제한)
 * 1 <= length <= 50
 */
class Problem15927 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        int strLength = str.length();
        boolean isRepeat = true;
        boolean isAllEquals = true;

        for (int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(strLength-i-1)) {
                isRepeat = false;
                break;
            }
            else if (str.charAt(i) != str.charAt(i+1)) {
                isAllEquals = false;
            }
        }

        if (!isRepeat) {
            System.out.println(strLength);
        }
        else if (isAllEquals){
            System.out.println(-1);
        }
        else {
            System.out.println(strLength-1);
        }

    }
}
/*
ABCBA
4

PALINDROME
10

ZZZ
-1
 */

