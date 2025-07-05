package code.programmers.study;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42885
 *
 * 제한)
 *  무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
 *  각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
 *  구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
 *  구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.
 *
 * 문제)
 *  1. 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 한다.
 *  2. 구명보트는 한번에 최대 2명밖에 탈 수 없다.
 *  3. 사람들의 몸무게를 담은 배열 people, 구명보트의 무게제한 limit가 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트
 *     개수의 최소값 구하기
 *
 * 풀이)
 *  방법 1
 *      시간초과: 이중 for문, visited배열을 사용해 최악의 경우 5만명일 때 성능이 크게 저하
 *  방법 2
 *      투포인터: 정렬 O(N log N), 투포인터 탐색 O(N)
 *      - 가장 가벼운 사람과 가장 무거운 사람의 합이 limit 이하일 경우 둘다 보트에 태움
 *      - 만약 둘의 합이 limit가 넘을 경우, 가장 무거운 사람만 보트에 탑승
 */
class Lesson42885 {
    public static void main(String[] args) {
        int[] people = new int[]{70, 50, 80, 50};
        int limit = 100;
        System.out.println(solution(people, limit)); // 3

        int[] people2 = new int[]{70, 80, 50};
        int limit2 = 100;
        System.out.println(solution(people2, limit2)); // 3
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            answer++;
        }
        return answer;
    }

//    public static int solution(int[] people, int limit) {
//        int answer = 0;
//        int len = people.length;
//        Arrays.sort(people);
//        boolean[] visited = new boolean[len];
//        for (int i = 0; i < len; i++) {
//            if (!visited[i]) {
//                int current = people[i];
//                int nextIndex = -1;
//                answer++;
//                visited[i] = true;
//                for (int j = i + 1; j < len; j++) {
//                    int next = people[j];
//                    if (current + next > limit) {
//                        break;
//                    }
//                    if (!visited[j]) {
//                        nextIndex = j;
//                    }
//                }
//                if (nextIndex != -1) {
//                    visited[nextIndex] = true;
//                }
//            }
//        }
//        return answer;
//    }
}
