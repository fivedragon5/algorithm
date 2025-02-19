package code.leetcode.daily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/?envType=daily-question&envId=2025-02-19
 *
 * 제한)
 * 1 <= n <= 10
 * 1 <= k <= 100
 *
 * 문제)
 *  1. Happy 문자열은 다음 조건을 만족
 *   - 'a', 'b', 'c' 으로만 구성 되어 있음
 *   - 문자열의 인덱스가 1부터 시작한다고 할때, s[i] != s[i+1]를 만족
 *  2. 두개의 정수 n, k 가 주어졌을 때, 길이가 n인 모든 해피 문자열을 사전순으로 정렬 했을때 k번쨰 문자를 반환 하기
 *
 * 풀이)
 *  1. 해피 문자열을 만족하는 문자열을 List에 add
 *  2. List에 k번째 문자열을 반환
 *   - List size가 0 이거나 Index가 초과 했을 경우 "" 반환
 *
 */


public class Question_20250219 {
    public static void main(String args[]) throws IOException {
        int n = 1; int k = 3;
        System.out.println(getHappyString(n, k));

        n = 1; k = 4;
        System.out.println(getHappyString(n, k));

        n = 3; k = 9;
        System.out.println(getHappyString(n, k));
    }

    public static String getHappyString(int n, int k) {
        List<String> happyStringList = new ArrayList<>();
        char[] characters = new char[]{'a', 'b', 'c'};
        dfs(n, "", happyStringList, characters);
        if (happyStringList.isEmpty() || happyStringList.size() < k) {
            return "";
        }
        return happyStringList.get(k-1);
    }

    private static void dfs(int n, String current, List<String> happyStringList, char[] characters) {
        if (current.length() == n) {
            happyStringList.add(current);
            return;
        }

        for (int i = 0; i < characters.length; i++) {
            int size = current.length();
            if (size == 0) {
                dfs(n, String.valueOf(characters[i]), happyStringList, characters);
                continue;
            } else {
                char lastChar = current.charAt(size - 1);
                if (lastChar != characters[i]) {
                    dfs(n, current + characters[i], happyStringList, characters);
                }
            }
        }
    }
}
