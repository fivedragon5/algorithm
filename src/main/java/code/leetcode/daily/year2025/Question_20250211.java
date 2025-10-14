package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/?envType=daily-question&envId=2025-02-11
 *
 * 제한)
 *  1 <= s.length <= 1000
 *  1 <= part.length <= 1000
 *  "s" and "part" consists of lowercase English letters.
 *
 * 문제)
 *
 * 풀이)
 *
 */

public class Question_20250211 {
    public static void main(String args[]) throws IOException {
        String s = "daabcbaabcbc";
        String part = "abc";
        removeOccurrences2(s, part);
    }

    public static String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            s = s.replaceFirst(part, "");
        }
        return s;
    }

    public static String removeOccurrences2(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.indexOf(part) != -1) {
            int index = sb.indexOf(part);
            sb.delete(index, index + part.length());
        }
        return sb.toString();
    }
}
