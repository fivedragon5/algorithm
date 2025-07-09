package code.programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12981
 *
 * 제한)
 *  끝말잇기에 참여하는 사람의 수 n은 2 이상 10 이하의 자연수입니다.
 *  words는 끝말잇기에 사용한 단어들이 순서대로 들어있는 배열이며, 길이는 n 이상 100 이하입니다.
 *  단어의 길이는 2 이상 50 이하입니다.
 *  모든 단어는 알파벳 소문자로만 이루어져 있습니다.
 *  끝말잇기에 사용되는 단어의 뜻(의미)은 신경 쓰지 않으셔도 됩니다.
 *  정답은 [ 번호, 차례 ] 형태로 return 해주세요.
 *  만약 주어진 단어들로 탈락자가 생기지 않는다면, [0, 0]을 return 해주세요.
 *
 * 문제)
 *  1. 1부터 N까지 번호가 붙어있는 N명의 사람이 끝말잇기를 진행합니다.
 *   - 1번부터 번호 순서대로 한사람씩 차례대로 번호를 말한다.
 *   - 마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작합니다.
 *   - 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 한다.
 *   - 이전에 등장했던 단어는 사용할 수 없다.
 *   - 한글자인 단어는 사용할 수 없다.
 *  2. 끝말잇기를 진행할때, 탈락자가 생기면 탈락자의 번호와 탈락자가 자신의 몇번째 차례인지 반환하기
 *
 * 풀이)
 *  1. Set<String> set을 사용하여 중복 단어를 체크한다.
 *  2. 단어의 마지막 문자와 다음 단어의 첫 문자를 비교하여 끝말잇기 규칙을 확인한다.
 *  3. 탈락자가 발생하면 해당 번호와 차례를 반환한다.
 *  4. 탈락자가 나오지 않을 경우 {0, 0}을 반환한다.
 */
class Lesson12981 {
    public static void main(String[] args) {
        int n = 3; String[] words = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        System.out.println(solution(n, words));

        int n2 = 5; String[] words2 = new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        System.out.println(solution(n2, words2));
    }

    public static int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        int len = words.length;
        char lastChar = words[0].charAt(words[0].length() - 1);
        set.add(words[0]);
        for (int i = 1; i < len ; i++) {
            if (lastChar != words[i].charAt(0)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            if (!set.add(words[i])) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            lastChar = words[i].charAt(words[i].length() - 1);
        }
        return new int[]{0, 0};
    }
}
