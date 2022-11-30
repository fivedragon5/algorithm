package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1 <= N <= 26
 * 1 <= str <= 100,000
 * 알파벳은 소문자만이 포함
 */
class Problem16472 {

    static String str;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        HashMap<Character, Integer> map = new HashMap<>();

        str = st.nextToken();

        if (N >= str.length() || N == 26) {
            System.out.println(str.length());
            return;
        }

        int leftIndex = 0;
        int rightIndex = 1;
        char leftChar = str.charAt(0);
        int count = 1;
        int answer = 1;

        map.put(str.charAt(0), 1);

        while(rightIndex < str.length()) {
            if (map.size() > N) {
                leftChar = str.charAt(leftIndex);
                int leftCharCount = map.get(leftChar);
                leftIndex++;
                count--;
                if (leftCharCount == 1) {
                    map.remove(leftChar);
                }
                else {
                    map.put(leftChar, leftCharCount-1);
                }
            }
            else {
                char rightChar = str.charAt(rightIndex);
                rightIndex++;
                map.put(rightChar,map.getOrDefault(rightChar, 0)+1);
                count++;
            }
            if (map.size() <= N) {
                answer = Math.max(answer, count);
            }
        }

        System.out.println(answer);
    }
}
/*
2
abbcaccba

4

2
a

1
ac
 */

