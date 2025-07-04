package code.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17684
 *
 * 제한)
 *  입력으로 영문 대문자로만 이뤄진 문자열 msg가 주어진다. msg의 길이는 1 글자 이상, 1000 글자 이하이다.
 *
 * 문제)
 *  1. 주어진 문자열을 압축한 후의 사전 색인 번호를 배열로 출력하기
 *   LZW 압축 과정
 *      - 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
 *      - 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
 *      - w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
 *      - 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
 *      - 단계 2로 돌아간다.
 *
 * 풀이)
 *  1. A ~ Z 까지 Map<Stirng, Integer> <문자열, 색인번호> 에 저장
 *  2. 주어진 msg 문자열을 앞에서부터 사전에 등록된 가장 긴 문자열 범위만큼 탐색
 *   - 사전에 등록되어있는 문자열 일 경우 :  answer에 해당 색인 번호 저장
 *   - 사전에 등록되어있지 않은 문자열 일 경우 : 범위를 1개씩 줄여 가며 탐색
 *   - 사전에서 탐색 후 탐색한 문자열 + 1개의 문자를 사전에 등록
 *    - 등록할때 map의 size(크기)를 색인 번호로 하기
 *  3. answer에 add한 정답을 int[] 형태로 변경
 *
 */
class Lesson17684 {
    public static void main(String[] args) {
        String msg = "KAKAO";
        System.out.println(solution(msg));

        msg = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println(solution(msg));

        msg = "ABABABABABABABAB";
        System.out.println(solution(msg));
    }

    public static int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> lzwDictionary = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            lzwDictionary.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        int maxWordLength = 1;
        int currentIndex = 0;
        int wordEndIndex = 27;

        while (currentIndex <= msg.length()) {
            int currentWordLength = maxWordLength;
            String word = msg.substring(currentIndex, Math.min(currentIndex + currentWordLength, msg.length()));
            if (isExistWord(word, lzwDictionary)) {
                answer.add(lzwDictionary.get(word));
            } else {
                while (currentWordLength > 1) {
                    currentWordLength--;
                    word = msg.substring(currentIndex, Math.min(currentIndex + currentWordLength, msg.length()));
                    if (isExistWord(word, lzwDictionary)) {
                        break;
                    }
                }
                answer.add(lzwDictionary.get(word));
            }
            if (currentIndex + currentWordLength >= msg.length()) {
                break;
            }
            String nextWord = msg.substring(currentIndex + currentWordLength, Math.min((currentIndex + currentWordLength) + 1, msg.length()));
            String newWord = word + nextWord;
            lzwDictionary.put(newWord, wordEndIndex++);
            currentIndex += word.length();
            maxWordLength = Math.max(maxWordLength, newWord.length());
        }
        int[] result = new int[answer.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = answer.get(i);
        }
        return result;
    }

    private static boolean isExistWord(String word, Map<String, Integer> map) {
        return map.getOrDefault(word, 0) != 0;
    }
}
