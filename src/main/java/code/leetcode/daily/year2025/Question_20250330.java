package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/partition-labels/description/?envType=daily-question&envId=2025-03-30
 *
 * 제한)
 *  1 <= s.length <= 500
 *  s consists of lowercase English letters.
 *
 * 문제)
 *  1. 주어진 문자열을 조건에 맞게 분할하기
 *   - 분할할 때, 자른 문자열의 알파벳이 다른 문자열에 등장하기 않게 자르기
 *  2. 문자열을 최대한 많이 자른다고 했을 때, 자른 문자열의 길이를 배열로 반환
 *
 * 풀이)
 *  1. 문자열, 문자열카운트 수 를 Map에 저장
 *  2. 문자열을 하나씩 탐색하면서 Set에 문자열을 추가 및 Map에 문자 카운트 수를 -1
 *   - Set에 있는 문자열들을 전부 꺼내 카운트 수가 0일 경우 해당 문자열을 자를 수 있음
 *
 *  풀이2)
 *   1. 문자가 등장하는 가장 마지막 Index를 저장
 *   2. 해당 index가 현재 탐색하는 Index와 같을 경우 해당 문자까지 자를 수 있음
 */

public class Question_20250330 {
    public static void main(String args[]) throws IOException {

        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));

        String s2 = "eccbbbbdec";
        System.out.println(partitionLabels(s2));
    }

    public static List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }

        Set<Character> set = new HashSet<>();

        List<Integer> answer = new ArrayList<>();
        int partitionCount = 0;
        for (int i = 0; i < s.length(); i++) {
            partitionCount++;
            char c = s.charAt(i);
            int count = characterIntegerHashMap.get(c);
            set.add(c);
            characterIntegerHashMap.put(c, count - 1);
            boolean flag = true;
            for (Character setCharacter : set) {
                if (characterIntegerHashMap.get(setCharacter) != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer.add(partitionCount);
                partitionCount = 0;
                set.clear();
            }
        }
        return answer;
    }
}
