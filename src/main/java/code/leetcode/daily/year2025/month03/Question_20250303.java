package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/partition-array-according-to-given-pivot/description/?envType=daily-question&envId=2025-03-03
 *
 * 제한)
 * 1 <= nums.length <= 10^5
 * -10^6 <= nums[i] <= 10^6
 *
 * 문제)
 *  1. 주어진 nums 배열을 조건에 따라 재배치 하기
 *  2. pivot를 기준으료 작은 값은 배열의 좌측
 *  3. pivot와 값이 같을 경우 작은 배열, 큰 배열 사이에 배치
 *  4. pivot를 기준으료 큰 값은 배열의 좌측
 *  5. 순서는 기존 nums 배열의 순서를 따른다
 *
 * 풀이)
 *  1. Pivot 기준 작은 값은 answer[left] 에 쌓기
 *  2. 같은 값은 equalCount에 더하기
 *  3. 큰 값은 Queued에 넣기
 *  4. answer에 equlaCount 만큼 추가
 *  5. 나머지 값에 queue를 비우면서 answer에 추가한다
 */

public class Question_20250303 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{9,12,5,10,14,3,10};
        int pivot = 10;
        System.out.println(pivotArray(nums, pivot));

        int[] nums1 = new int[]{-3,4,3,2};
        int pivot1 = 2;
        System.out.println(pivotArray(nums1, pivot1));
    }

    public static int[] pivotArray(int[] nums, int pivot) {
        int[] answer = new int[nums.length];
        int left = 0;
        Queue<Integer> queue = new LinkedList<>();
        int equalCount = 0;
        for (int num : nums) {
            if (num == pivot) {
                equalCount++;
            } else if (num < pivot) {
                answer[left++] = num;
            } else {
                queue.add(num);
            }
        }

        while (equalCount > 0) {
            answer[left++] = pivot;
            equalCount--;
        }

        while (!queue.isEmpty()) {
            answer[left++] = queue.poll();
        }

        return answer;
    }
}
