package code.leetcode.daily.year2026;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/find-missing-elements/description/?envType=daily-question&envId=2026-08-04
 *
 * 제한)
 *  2 <= nums.length <= 100
 *  1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 정수배열 nums가 주어짐
 *      - 이 배열에는 서로 다른 Unique한 정수가 들어 있음
 *  2. 원래 nums에는 연속된 정수 범위안의 모든 정수를 포함하고 있었지만 일부 정수들이 배열에서 사라짐
 *  3. 원래 범위의 가장 작은 값과 가장 큰 값은 항상 nums에 포함되어 있다.
 *  원래 범위 내에서 누락된 모든 정수를 오름차순으로 정렬된 리스트로 반환하세요
 *   - 만약 누락된 정수가 없다면 빈 리스트 [] 를 반환
 *
 * 풀이)
 *  1. nums의 최솟값과 최대값을 구한다.
 *  2. nums를 HashSet에 넣는다
 *  3. min ~ max 까지 순회하면서 Set에 없는 숫자를 결과에 추가한다
 *
 */

public class Question_20260804 {
    public static void main(String args[]) throws IOException {
        int[] nums = {1,4,2,5};
        System.out.println(findMissingElements(nums));
    }

    public static List<Integer> findMissingElements(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        List<Integer> answer =  new ArrayList<>();
        for (int num : nums) {
            set.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                answer.add(i);
            }
        }
        return answer;
    }
}
