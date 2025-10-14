package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-index-of-a-valid-split/description/?envType=daily-question&envId=2025-03-27
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *  nums has exactly one dominant element.
 *
 * 문제)
 *  1. nums 배열에는 하나의 지배적인 원소가 무조건 존재
 *   - 지배적인 원소 : 원소중 배열의 길이 반 이상 존재하는 원소를 지배적인 원소라고 함
 *  2. nums 배열을 2개로 나눴을때 나누기 전의 지배적인 원소와 나눈 배열들의 지배적인 원소가 같은
 *     촤소 인덱스에서 유요한 분할 지점을 찾기
 *
 * 풀이)
 *  1. Map에 등장한 수 와 등장한 횟 수를 저장 하면서 지배적인 원소 찾기
 *   - 지배적인 원소를 찾을경우 반복문 종료
 *  2. for문을 nums의 길이만큼 돌리면서 배열 분할 진행
 *   - 분할하면서 해당 인덱스의 지배적인 원소가 등장할 경우 count 추가
 *   - count가 현재 분할 지점의 반보다 클 경우 자르고 난 나머지의 배열도 지배적인 원소가 그대로인지 확인
 *
 */

public class Question_20250327 {
    public static void main(String args[]) throws IOException {

        List<Integer> nums = List.of(1,2,2,2);
        System.out.println(minimumIndex(nums));

        List<Integer> nums2 = List.of(2,1,3,1,1,1,7,1,2,1);
        System.out.println(minimumIndex(nums2));

        List<Integer> nums3 = List.of(3,3,3,3,7,2,2);
        System.out.println(minimumIndex(nums3));

    }

    public static int minimumIndex(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int midCount = nums.size() / 2 + 1;
        int dominantNumber = 0;
        for (int key : map.keySet()) {
            if (map.get(key) >= midCount) {
                dominantNumber = key;
                break;
            }
        }

        if (dominantNumber == 0) return -1;
        int leftIndex = 0;
        int leftDominantCount = 0;

        for (int i = 0; i < nums.size() - 1; i++) {
            int currentNumber = nums.get(i);
            leftIndex++;
            if (currentNumber == dominantNumber) {
                leftDominantCount++;
            }

            if (leftDominantCount > leftIndex / 2 &&
                map.get(dominantNumber) - leftDominantCount > (nums.size() - leftIndex) / 2) {
                return i;
            }
        }
        return -1;
    }
}
