package code.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 *
 * 제한)
 *  relation은 2차원 문자열 배열이다.
 *  relation의 컬럼(column)의 길이는 1 이상 8 이하이며, 각각의 컬럼은 릴레이션의 속성을 나타낸다.
 *  relation의 로우(row)의 길이는 1 이상 20 이하이며, 각각의 로우는 릴레이션의 튜플을 나타낸다.
 *  relation의 모든 문자열의 길이는 1 이상 8 이하이며, 알파벳 소문자와 숫자로만 이루어져 있다.
 *  relation의 모든 튜플은 유일하게 식별 가능하다.(즉, 중복되는 튜플은 없다.)
 *
 * 문제)
 *  1. 릴레이션을 나타내는 배열 relation이 주어질 때, 이 릴레이션의 후보키의 개수를 구하기
 *
 * 풀이)
 *  1. 모든 컬럼의 조합을 구한다 (1개 ~ 컬럼의 수)
 *  2. 각 조합이 완성 될 때 마다 해당 조합이 후보키가 될 수 있는지 확인한다.
 *   - 후보키 확인 방법 (최소성 -> 유일성)
 *    1. 최소성 확인
 *     - 후보키 중 중복되는 조합 확인 contains 사용 주의
 *     - ex) 02가 후보키 일때 0123은 최소성을 만족하지 못함
 *           contains 사용시 false가 나오기 때문에 사용시 주의 필요
 *    2. 유일성 확인
 *     - 조합된 컬럼의 값을 Set에 넣어 중복 여부 확인
 *  3. 모든 조합을 확인한 후 후보키의 개수를 반환한다.
 *
 */

class Lesson42890 {
    public static void main(String[] args) {
        String[][] relation = new String[][]{
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };
        System.out.println(solution(relation));
    }

    private static String[][] RELATIONS;
    private static List<String> LIST = new ArrayList<>();

    public static int solution(String[][] relation) {
        RELATIONS = relation;
        int colCount = relation[0].length;

        for (int c = 1; c <= colCount; c++) {
            combination(c, 0, new StringBuilder());
        }

        return LIST.size();
    }

    public static void combination(int r, int start, StringBuilder sb) {
        if (r == 0) {
            // 최소성 검사
            for (String key : LIST) {
                int keyCount = key.length();
                for (int i = 0; i < key.length(); i++) {
                    String a = String.valueOf(key.charAt(i));
                    if (sb.toString().contains(a)) {
                        keyCount--;
                    }
                }
                if (keyCount == 0) {
                    return; // 이미 존재하는 후보키이므로 중복 추가 방지
                }
            }

            if (isUnique(sb)) {
                LIST.add(sb.toString());
            }
            return;
        }
        for (int i = start; i < RELATIONS[0].length; i++) {
            sb.append(i);
            combination(r - 1, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static boolean isUnique(StringBuilder sb) {
        Set<String> set = new HashSet<>();
        for (String[] relation : RELATIONS) {
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                int index = sb.charAt(i) - '0';
                key.append(relation[index]);
            }
            if (!set.add(key.toString())) {
                return false;
            }
        }
        return true;
    }
}
