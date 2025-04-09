package code.leetcode.daily.year2025.month04;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/?envType=daily-question&envId=2025-04-09
 *
 * 제한)
 *  1 <= nums.length <= 100
 *  1 <= nums[i] <= 100
 *  1 <= k <= 100
 *
 * 문제)
 *  1. Integer 타입의 배열 nums가 주어진다
 *  2. nums[i] 를 연산을통해 모두 k 로 만드는데에 필요한 최소 연산 횟수 구하기
 *   - 만들지 못할 경우 -1 반환
 *  3. 연산
 *   - 1. nums[i] > h 를 만족
 *   - 3-1 을 만족하는 nums[i] 값이 모두 동일해야 유효한 h를 설정할 수 있음
 *   - 위 조건을 만족할 경우 nums[i] > h 를 만족하는 nums[i]를 h로 변경
 *
 * 풀이)
 *  1. nums 배열의 원소를 우선순위 큐에 추가 (내림차순)
 *   - 추가하면서 원소중 k보다 작은 값이 있다면 -1 반환
 *  2. 우선순위큐에 있는 원소를 하나씩 poll하면서 이전에 poll한 원소의 값과 다를 경우 연산 카운트 증가
 *  3. 목표 값(k)에 다다를 경우 연산 카운트 반환
 *  4. while문 종료시 연산 카운트 +1 해서 반환
 *
 */

public class Question_20250409 {
    public static void main(String args[]) throws IOException {
        int[] nums4 = new int[]{1}; int k4 = 1;
        System.out.println(minOperations(nums4, k4));

        int[] nums5 = new int[]{5,2,5,4,5}; int k = 2;
        System.out.println(minOperations(nums5, k));

        int[] nums2 = new int[]{2,1,2}; int k2 = 2;
        System.out.println(minOperations(nums2, k2));

        int[] nums3 = new int[]{9,7,5,3}; int k3 = 1;
        System.out.println(minOperations(nums3, k3));
    }

    public static int minOperations(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            if (num < k) {
                return -1;
            }
            priorityQueue.add(num);
        }
        int operationCount = 0;
        int currentMax = priorityQueue.peek();
        while (!priorityQueue.isEmpty()) {
            int currentNumber = priorityQueue.poll();
            if (currentMax != currentNumber) {
                currentMax = currentNumber;
                operationCount++;
            }
            if (currentNumber == k) {
                return operationCount;
            }
        }
        return operationCount + 1;
    }
}
