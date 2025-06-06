package code.programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/132265
 *
 * 제한)
 *  1 ≤ topping의 길이 ≤ 1,000,000
 *      1 ≤ topping의 원소 ≤ 10,000
 *
 * 문제)
 *  1. 토핑의 종류가 담긴 배열 topping이 주어진다.
 *  2. 케익을 두조각으로 나눴을 때, 공평하게 자르는 방법의 수를 반환 하기
 *   - 공평하게 자른다는 것은 두 조각의 토핑 종류의 갯수가 같다는 의미
 *
 * 풀이)
 *  1. 모든 토핑의 종류와 개수를 세기 위해 Map을 사용한다.
 *  2. 왼쪽 조각에 포함된 토핑의 종류와 개수를 세기 위해 또 다른 Map을 사용한다.
 *  3. 왼쪽 조각에 토핑을 하나씩 추가하면서, 왼쪽 조각과 오른쪽 조각의 토핑 종류의 개수가 같을 때마다 카운트한다.
 *
 */
class Lesson132265 {
    public static void main(String[] args) {
        int[] topping = new int[]{1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(solution(topping));

        int[] topping2 = new int[]{1, 2, 3, 1, 4};
        System.out.println(solution(topping2));
    }

    public static int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> allToppingCount = new HashMap<>();
        Map<Integer, Integer> leftToppingCount = new HashMap<>();

        for (int i = 0; i < topping.length; i++) {
            allToppingCount.put(topping[i], allToppingCount.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 0; i < topping.length; i++) {
            int currentTopping = topping[i];

            leftToppingCount.put(currentTopping, leftToppingCount.getOrDefault(currentTopping, 0) + 1);
            allToppingCount.put(currentTopping, allToppingCount.get(currentTopping) - 1);

            if (allToppingCount.get(currentTopping) == 0) {
                allToppingCount.remove(currentTopping);
            }

            if (leftToppingCount.size() == allToppingCount.size()) {
                answer++;
            }
        }
        return answer;
    }
}
