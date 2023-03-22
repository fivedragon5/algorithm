package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 자료형 주의
 *
 * 풀이)
 *  1. 입력받은 수 를 N진수로 나타냈을때 값을 String 배열로 저장
 *   - 어떤 수, N 들의 값은 저장할 필요가 없기 때문에 N진수로 변환한 값만 저장
 *  2. 변환한 값을 순회하면서 첫자리와 끝자리를 일치하면서 비교
 *   - true, false List로 저장 했다가 마지막에 출력
 *
 * 제한)
 *  T(1 ≤ T ≤ 1000)
 *  A(1 ≤ A ≤ 100,000,000,000)
 *  n(2 ≤ n ≤ 16)
 */
class Problem17609 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        String[] palindromes = new String[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            long number = Long.parseLong(st.nextToken());
            int radix = Integer.parseInt(st.nextToken());
            palindromes[i] = Long.toString(number, radix);
        }

        List<Boolean> isPossible = new LinkedList<>();

        for (String palindrome : palindromes) {
            int size = palindrome.length();
            boolean isFlag = true;
            for (int i = 0; i < size / 2; i++) {
                if (palindrome.charAt(i) != palindrome.charAt(size - i - 1)) {
                    isFlag = false;
                    isPossible.add(false);
                    break;
                }
            }
            if (isFlag) isPossible.add(true);
        }

        for (boolean b : isPossible) {
            System.out.println(b ? "1" : "0");
        }
    }
}
/*
5
100 3
30 2
25 4
58 8
342633 16

1
0
1
0
0
 */

