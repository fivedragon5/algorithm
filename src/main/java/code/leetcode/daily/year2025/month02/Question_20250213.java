package code.leetcode.daily.year2025.month02;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/?envType=daily-question&envId=2025-02-13
 *
 * 제한)
 *  2 <= nums.length <= 2 * 105
 *  1 <= nums[i] <= 109
 *  1 <= k <= 109
 *
 * 문제)
 *  아래의 계산식을 반복해서 모든 요소가 k가 넘을때까지 필요한 최소 연산 수를 구하기
 *  1. 주어진 nums의 원소들 중 가장 작은 숫자 2(x, y)개를 뽑는다.
 *  2. min(x, y) * 2 + max(x, y)
 *  3. 사용한 원소를 삭제 후 계산한 값을 배열에 추가
 *
 * 풀이)
 *  1. 우선순위 큐에 주어진 nums 저장
 *  2. 우선순위 큐에 가장 낮은 값이 k를 넘을때까지 반복
 *  3. 주어진 계산식 반복문실행
 */

public class Question_20250213 {
    public static void main(String args[]) throws IOException {
        int[] nums = {2,11,10,1,3};
        int k = 10;
        System.out.println(minOperations(nums, k));

        nums = new int[]{1,1,2,4,9};
        k = 20;
        System.out.println(minOperations(nums, k));

        nums = new int[]{999999999,999999999,999999999};
        k = 1000000000;
        System.out.println(minOperations(nums, k));

        nums = new int[]{1000000000,999999999,1000000000,999999999,1000000000,999999999};
        k = 1000000000;
        System.out.println(minOperations(nums, k));


    }

    public static int minOperations(int[] nums, int k) {
        int answer = 0;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add((long) num);
        }
        long result = 0;
        while (queue.peek() < k) {
            long num1 = queue.poll();
            long num2 = queue.poll();
            result = num1 * 2 + num2;
            queue.add(result);
            answer++;
        }
        return answer;
    }
}
