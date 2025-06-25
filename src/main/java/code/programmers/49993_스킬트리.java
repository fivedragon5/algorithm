package code.programmers;

import java.util.HashSet;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49993
 *
 * 제한)
 *  스킬은 알파벳 대문자로 표기하며, 모든 문자열은 알파벳 대문자로만 이루어져 있습니다.
 *  스킬 순서와 스킬트리는 문자열로 표기합니다.
 *      예를 들어, C → B → D 라면 "CBD"로 표기합니다
 *  선행 스킬 순서 skill의 길이는 1 이상 26 이하이며, 스킬은 중복해 주어지지 않습니다.
 *  skill_trees는 길이 1 이상 20 이하인 배열입니다.
 *  skill_trees의 원소는 스킬을 나타내는 문자열입니다.
 *      skill_trees의 원소는 길이가 2 이상 26 이하인 문자열이며, 스킬이 중복해 주어지지 않습니다.
 *
 * 문제)
 *  선행스킬 : 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬
 *  1. 선행스킬 순서 skill과 유저들이 만든 스킬트리를 담은 배열 skill_trees가 주어질 때,
 *     가능한 스킬트리 개수를 반환하기
 *
 * 풀이)
 *  1. skill 문자열을 순서대로 배열에 저장
 *  2. skill_trees 배열을 순회하며 각 스킬트리의 스킬이 선행스킬 순서에 맞는지 확인
 *  3. 각 스킬트리에서 스킬이 선행스킬 순서에 맞게 배워졌다면 카운트 증가
 *  4. 선행스킬 순서에 맞지 않는 스킬이 발견되면 해당 스킬트리는 무시
 *  5. 최종적으로 카운트된 스킬트리의 개수를 반환
 *
 */

class Lesson49993 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        char[] skill_order = new char[skill.length()];
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < skill.length(); i++) {
            char c = skill.charAt(i);
            skill_order[i] = c;
            set.add(c);
        }
        int answer = 0;
        for (String skill_tree : skill_trees) {
            if (isPossible(skill_order, set, skill_tree)) {
                answer++;
            }
        }
        return answer;
    }

    private static boolean isPossible(char[] skill_order, HashSet<Character> set, String skill_tree) {
        int skillIndex = 0;
        for (int i = 0; i < skill_tree.length(); i++) {
            char c = skill_tree.charAt(i);
            if (set.contains(c)) {
                if (skill_order[skillIndex] != c) {
                    return false;
                }
                skillIndex++;
            }
        }
        return true;
    }
}
