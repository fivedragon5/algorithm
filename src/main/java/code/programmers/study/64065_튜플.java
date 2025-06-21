package code.programmers.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/64065
 *
 * 제한)
 *  s의 길이는 5 이상 1,000,000 이하입니다.
 *  s는 숫자와 '{', '}', ',' 로만 이루어져 있습니다.
 *  숫자가 0으로 시작하는 경우는 없습니다.
 *  s는 항상 중복되는 원소가 없는 튜플을 올바르게 표현하고 있습니다.
 *  s가 표현하는 튜플의 원소는 1 이상 100,000 이하인 자연수입니다.
 *  return 하는 배열의 길이가 1 이상 500 이하인 경우만 입력으로 주어집니다.
 *
 * 문제)
 *  1. 등장하는 숫자의 빈도수를 계산하여 빈도수가 높은 순서대로 정렬된 배열을 반환하기
 *
 * 풀이)
 *  1. 문자열 s에서 중괄호 '{}'를 제거하고, ','로 분리하여 숫자들을 추출
 *  2. 각 숫자의 빈도수를 계산하여 Map에 저장 <숫자, 빈도수>
 *  3. Map의 entrySet을 List로 변환
 *  4. 빈도수에 따라 내림차순으로 정렬
 *  5. 정렬된 List에서 숫자만 추출하여 결과 배열 생성
 *
 */

class Lesson64065 {
    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(solution(s)); // [2, 1, 3, 4]

        String s2 = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        System.out.println(solution(s2)); // [2, 1, 3, 4]

        String s3 = "{20,111},{111}";
        System.out.println(solution(s3)); // [111, 20]

        String s4 = "{{123}}";
        System.out.println(solution(s4)); // [123]
    }

    public static int[] solution(String s) {
        String[] split = s.replaceAll("[{}]", "").split(",");
        Map<Integer, Integer> map = new HashMap<>();
        for (String str : split) {
            int num = Integer.parseInt(str);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Map의 entrySet을 List로 변환
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        // 빈도수에 따라 내림차순 정렬
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // 결과 배열 생성
        int[] answer = new int[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            answer[i] = entryList.get(i).getKey();
        }

        return answer;
    }
}
