package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/1-bit-and-2-bit-characters/description/?envType=daily-question&envId=2025-11-18
 *
 * 제한)
 *  1 <= bits.length <= 1000
 *  bits[i] is either 0 or 1.
 *
 * 문제)
 *  1. 1비트 문자는 '0'으로 표현된다.
 *  2. 2비트 문자는 '10' 또는 '11'로 표현된다.
 *  3. 배열은 항상 '0'으로 끝난다.
 *  4. 마지막 문자가 반드시 1비트 문자인지(즉, 마지막 0이 단독으로 쓰이는지) 여부를 반환하기
 *  ex) bits = [1, 0, 0] -> true
 *
 * 풀이)
 *  1. 배열을 순회하면서 1비트 문자와 2비트 문자를 구분한다.
 *   - 1일 경우 2비트 문자이므로 인덱스를 2칸 이동한다.
 *   - 0일 경우 1비트 문자이므로 인덱스를 1칸 이동한다.
 *  2. 마지막 문자가 1비트 문자로 끝나는지 확인한다
 *
 */

public class Question_20251118 {
    public static void main(String args[]) throws IOException {
        int[] bits = {1, 0, 0};
        System.out.println(isOneBitCharacter(bits));
    }

    public static boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        if (bits[n - 1] != 0) {
            return false;
        }

        int i = 0;
        while (i < n - 1) {
            if (bits[i] == 0) {
                i++;
            } else {
                i += 2;
            }
        }
        return i == n - 1;
    }
}
