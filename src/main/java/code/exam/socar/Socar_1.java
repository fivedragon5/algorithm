package code.exam.socar;

import java.util.regex.Pattern;

/**
 * 2025.09.17
 * 제한)
 *  1 <= strs.length <= 200
 *  1 <= strs[i].length <= 200
 *  strs의 원소는 "A", "B" 로만 이루어져 있다.
 *
 * 문제)
 *  1. 어떤 문자의 반복을 ~로 표시한다
 *   ex) B~ = {"B", "BB", "BBB", ...}
 *   ex) (AB)~ = {"AB", "ABAB", "ABABAB", ...}
 *   ex) BA~BB = {"BABB, "BAABB", "BAAABB", "BAAAABB", ...}
 *   ex) (BA~B)~ = {"BAB", "BAAB", "BAAAB", "BAAAAB", "BABBAAB"}
 *  2. 추가로 (A|B)는 A또는 B중에서 아무거나 하나만 선택해서 만든 문자열의 집합을 의미한다.
 *   ex) (BAA|ABA)를 집합으로 표현 = {"BAA", "ABA"}
 *   ex (A|B)~ = A,B를 사용하여 만들수 있는 모든 집합을 의미
 *  3. (AAB~|BAB~A)~ 패턴을 띄는 문자열이 몇개인지 판별하기
 *  4. 문자열이 담긴 배열 strs가 매개변수로 주어질 때, strs의 원소 중 (AAB~|BAB~A)~ 패턴을 띄는 문자열의 개수를 return
 *
 * 풀이)
 *  정규표현식 사용
 *      - (AAB+|BAB+A)+
 *
 */

class Socar_1 {
    public static void main(String[] args) {
        String[] strs = {"AABAAA", "BABABB", "BABBAAAB", "BABAAABAABBABBA"};
        System.out.println(solution(strs)); // 2

        String[] strs2 = {"AA", "BAB", "BAAAA", "ABBABB", "AABBBBABBAAAA"};
        System.out.println(solution(strs2)); // 0

        String[] strs3 = {"AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA"};
        System.out.println(solution(strs3)); // 3
    }

    public static int solution(String[] strs) {
        int count = 0;
        String regex = "^(AAB+|BAB+A)+$";
        Pattern pattern = Pattern.compile(regex);
        for (String str : strs) {
            if (pattern.matcher(str).matches()) {
                count++;
            }
        }
        return count;
    }
}
