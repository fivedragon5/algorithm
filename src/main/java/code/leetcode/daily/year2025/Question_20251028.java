package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/make-array-elements-equal-to-zero/description/?envType=daily-question&envId=2025-10-28
 *
 * 제한)
 *  1 <= nums.length <= 100
 *  0 <= nums[i] <= 100
 *  적어도 하나의 원소가 0
 *
 * 문제)
 *  1. 정수 배열 nums가 주어졌을 때, 다음 조건을 만족하는 선택의 수를 반환
 *  2. 조건
 *   - 시작위치 curr를 선택, 이때 nums[curr] == 0 이어야 함.
 *   - 이동 방향 왼쪽 또는 오른쪽 을 선택
 *   - 그후 다음 과정을 반복 한다.
 *       - curr이 [0, n - 1] 범위를 벗어나면 종료
 *       - nums[curr] == 0
 *          - 오른쪽일 경우 curr += 1
 *          - 왼쪽일 경우 curr -= 1
 *       - nums[curr] > 0
 *           - nums[curr]--
 *           - 이동 방향을 반대로 바꿈
 *           - 바뀐 방향으로 한칸 이동
 *   - 이 과정을 끝냈을 때, nums의 모든 요소가 0이 되는지 확인
 *
 * 풀이)
 *  1. 누적합 배열을 만들어줌 prefixSum[]
 *  2. nums 배열을 순회하면서 nums[i] == 0 인 경우에 대해
 *     - 왼쪽 합 leftSum = prefixSum[i]
 *     - 오른쪽 합 rightSum = totalSum - leftSum
 *     - leftSum == rightSum 인 경우 count += 2
 *     - |leftSum - rightSum| == 1 인 경우 count += 1
 *  3. count 반환
 *  ex) 좌우 벽돌깨기 문제와 유사
 *  ex) 1,0,2,0,3
 *    - index : 1 선택
 *        = leftSum 1 | 0 | rightSum 5
 *    - index : 3 선택
 *        = leftSum 3 | 0 | rightSum 3 (leftSum == rightSum) count + 2
 */

public class Question_20251028 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{2,3,4,0,4,1,0};
        System.out.println(countValidSelections(nums));

        int[] nums2 = new int[]{1,0,2,0,3};
        System.out.println(countValidSelections(nums2));
    }

    private static int countValidSelections(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            if (curr == 0) {
                int leftSum = prefixSum[i];
                int rightSum = sum - leftSum;
                if (leftSum == rightSum) {
                    count+=2;
                }
                else if (Math.abs(leftSum - rightSum) == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
