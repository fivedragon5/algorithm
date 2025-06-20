package code.programmers.study;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 *
 * 제한)
 *  1 <= word.length <= 5
 *  word = 'A', 'E', 'I', 'O', 'U'
 *
 * 문제)
 *  1. 사전에 알파벳 모음 A, E, I, O, U만을 사용하여 만들 수 있는 5자리 이하의 모든 단어가 수록 되어 있다.
 *  2. 첫번째 단어 A, 두번쨰 단어 AA, 마지막 단어 UUUUU 이다.
 *  3. 단어가 주어질 때, 이 단어가 사전에서 몇번째 단어인지 구하기
 *
 * 풀이)
 *  BFS | 모든 단어 경우의 수 5^5
 *  1. 모든 단어를 BFS로 탐색하여 리스트에 저장
 *  2. 리스트를 정렬하여 사전 순으로 정렬
 *  3. 주어진 단어와 리스트의 단어를 비교하여 몇번째 단어인지 찾기
 */
class Lesson84512 {
    public static void main(String[] args) {
        String word = "AAAAE";
        System.out.println(solution(word)); // 6

        word = "AAAE";
        System.out.println(solution(word)); // 10

        word = "I";
        System.out.println(solution(word)); // 1563

        word = "EIO";
        System.out.println(solution(word)); // 1189
    }

    public static int solution(String word) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bfs(list, vowels, new StringBuilder(), 0, i + 1);
        }
        list.sort((String::compareTo));
        for (String w : list) {
            if (w.equals(word)) {
                return list.indexOf(w) + 1;
            }
        }
        return -1;
    }

    private static void bfs(List<String> list, char[] vowels, StringBuilder currentWord, int depth, int maxDepth) {
        if (depth == maxDepth) {
            list.add(currentWord.toString());
            return;
        }
        for (char vowel : vowels) {
            currentWord.append(vowel);
            bfs(list, vowels, currentWord, depth + 1, maxDepth);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }
}
