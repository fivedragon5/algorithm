package code.programmers.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/72412
 *
 * 제한)
 *  info 배열의 크기는 1 이상 50,000 이하입니다.
 *  info 배열 각 원소의 값은 지원자가 지원서에 입력한 4가지 값과 코딩테스트 점수를 합친 "개발언어 직군 경력 소울푸드 점수" 형식입니다.
 *      개발언어는 cpp, java, python 중 하나입니다.
 *      직군은 backend, frontend 중 하나입니다.
 *      경력은 junior, senior 중 하나입니다.
 *      소울푸드는 chicken, pizza 중 하나입니다.
 *      점수는 코딩테스트 점수를 의미하며, 1 이상 100,000 이하인 자연수입니다.
 *      각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
 *  query 배열의 크기는 1 이상 100,000 이하입니다.
 *  query의 각 문자열은 "[조건] X" 형식입니다.
 *      [조건]은 "개발언어 and 직군 and 경력 and 소울푸드" 형식의 문자열입니다.
 *      언어는 cpp, java, python, - 중 하나입니다.
 *      직군은 backend, frontend, - 중 하나입니다.
 *      경력은 junior, senior, - 중 하나입니다.
 *      소울푸드는 chicken, pizza, - 중 하나입니다.
 *      '-' 표시는 해당 조건을 고려하지 않겠다는 의미입니다.
 *      X는 코딩테스트 점수를 의미하며 조건을 만족하는 사람 중 X점 이상 받은 사람은 모두 몇 명인 지를 의미합니다.
 *      각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
 *      예를 들면, "cpp and - and senior and pizza 500"은 "cpp로 코딩테스트를 봤으며,
 *          경력은 senior 이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 500점 이상 받은 사람은 모두 몇 명인가?"를 의미합니다.
 *
 * 문제)
 *  1. 개발자 정보를 담은 info 배열과 조건을 담은 query 배열이 주어질 때, 조건에 맞는 개발자의 수를 반환하기
 *   - 개발 언어 : cpp, java, python
 *   - 직군 : backend, frontend
 *   - 소울 푸드 : chicken, pizza
 *
 * 풀이)
 *  구현
 *  1. info 배열을 순회하며 각 개발자의 정보를 key로 하는 Map을 생성
 *   - 정보 : 개발언어 + 직군 + 경력 + 소울푸드
 *   - key를 생성할 때, 각 조건에 대해 와일드카드 '-'를 포함한 모든 조합을 생성
 *  2. Map의 value는 해당 조건에 맞는 개발자의 코딩테스트 점수로 추후 이분탐색을 위해 오름차순 정렬
 *  3. query 배열을 순회하며 각 조건에 맞는 개발자의 수를 계산
 *   - query의 조건을 파싱하여 key를 생성
 *  4. 이분탐색을 통해 해당 key에 대한 개발자의 수를 계산
 *   - 조건을 만족하는 첫번째 index를 탐색 후 (조건을 만족하는 개발자 의 전체 수 - 찾은 index)를 반환
 *
 *  처음문제를 풀 때, 일반적인 구현 방식으로 접근했으나, 효율성 테스트 전부 실패로 key조합 풀이로 변경
 *   - 시간복잡도
 *   O(N * 2^4 + Q * log(N)) = O(N + Q)
 *    - N : info 배열의 크기
 *    - 2^4 : 각 개발자의 정보 조합 (개발언어, 직군, 경력, 소울푸드)에서 와일드카드 '-'를 포함한 모든 조합
 *    - Q : query 배열의 크기
 *    - log(N) : 이분탐색을 통한 조건에 맞는 개발자의 수 계산
 *   최악의 경우의 수 : 50,000 * 16 + 100,000 * log(50,000) = 800,000 + 1,400,000 = 2,200,000
 *
 */

class Lesson72412 {
    public static void main(String[] args) {
//        String[] info2 = {
//                "java backend junior pizza 150"
//                ,"python frontend senior chicken 210"
//                ,"python frontend senior chicken 150"
//                ,"cpp backend senior pizza 260"
//                ,"java backend junior chicken 80"
//                ,"python backend senior chicken 50"
//        };
//        String[] query2 = {
//                "- and - and - and - 10"
//                ,"- and - and - and - 40"
//                ,"- and - and - and - 50"
//                ,"- and - and - and - 60"
//                ,"- and - and - and - 210"
//                ,"- and - and - and - 270"
//        };
//        System.out.println(solution(info2, query2));


        String[] info = {
                "java backend junior pizza 150"
                ,"python frontend senior chicken 210"
                ,"python frontend senior chicken 150"
                ,"cpp backend senior pizza 260"
                ,"java backend junior chicken 80"
                ,"python backend senior chicken 50"
        };
        String[] query = {
                "java and backend and junior and pizza 100"
                ,"python and frontend and senior and chicken 200"
                ,"cpp and - and senior and pizza 250"
                ,"- and backend and senior and - 150"
                ,"- and - and - and chicken 100"
                ,"- and - and - and - 150"
        };
        System.out.println(solution(info, query));
    }

    static Map<String, List<Integer>> infoMap = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        for (String s : info) {
            String[] parts = s.split(" ");
            makeKeys(parts, 0, "", Integer.parseInt(parts[4]));
        }

        // 정렬
        for (List<Integer> list : infoMap.values()) {
            Collections.sort(list);
        }

        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] parts = query[i].replace(" and ", " ").split(" ");
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]);
            result[i] = countByScore(key, score);
        }
        return result;
    }

    // info 조건 조합별로 key 생성 (와일드카드 포함)
    private static void makeKeys(String[] arr, int depth, String key, int score) {
        if (depth == 4) {
            infoMap.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }
        makeKeys(arr, depth + 1, key + arr[depth], score);
        makeKeys(arr, depth + 1, key + "-", score);
    }

    // 이분탐색
    private static int countByScore(String key, int score) {
        if (!infoMap.containsKey(key)) return 0;
        List<Integer> list = infoMap.get(key);
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < score) left = mid + 1;
            else right = mid;
        }
        return list.size() - left;
    }
}
