package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/?envType=daily-question&envId=2025-11-04
 *
 * 제한)
 *  1 <= n == nums.length <= 50
 *  1 <= nums[i] <= 50
 *  1 <= x <= k <= nums.length
 *
 * 문제)
 *  1. 정수 배열 nums와 두 개의 정수 k, x가 주어졌을 때, x-sum은 다음 절차에 따라 계산된다.
 *   - 배열의 모든 원소가 몇 번씩 나오는지 세기
 *   - 가장 많이 나온 x개의 원소를 선택
 *     - 만약 두 원소의 등장 획수가 같다면 값이 더 큰 원소가 우선 선택된다.
 *   - 남은 원소들만으로 배열을 구성해 그 배열의 합을 구하기
 *   - 만약 배열에 서로 다른 원소가 x개보다 적다면, 배열 전체의 합이 x - 합이 된다.
 *  2. 길이 k의 모든 연속된 부분 배열에 대해 x-sum을 계산하고, 이 값들을 배열로 반환
 *
 * 풀이)
 *  1. 슬라이딩 윈도우 + 우선순위 큐
 *  2. 슬라이딩 윈도우로 길이 k의 부분 배열을 순회하면서 각 부분 배열의 원소 등장 횟수를 HashMap에 저장
 *  3. 우선순위 큐를 사용하여 등장 횟수와 값을 기준으로 정렬
 *  4. 상위 x개의 원소를 선택하여 나머지 원소들의 합을 계산
 *  5. 결과를 리스트에 저장하고, 최종적으로 배열로 변환하여 반환
 *
 */

public class Question_20251104 {
    public static void main(String args[]) throws IOException {
        int[] nums = {1,1,2,2,3,4,2,3};
        int k = 6;
        int x = 2;
        System.out.println(findXSum(nums, k, x));
    }

    private static int[] findXSum(int[] nums, int k, int x) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            int current = nums[i];
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        result.add(getXsum(map, x));

        int left = 1;
        while (left + k  - 1 < nums.length) {
            int leftNum = nums[left - 1];
            int rightNum = nums[left + k - 1];

            int leftNumberCount = map.get(leftNum);
            if (leftNumberCount == 1) {
                map.remove(leftNum);
            }
            else {
                map.put(leftNum, leftNumberCount - 1);
            }

            map.put(rightNum, map.getOrDefault(rightNum, 0) + 1);
            result.add(getXsum(map, x));
            left++;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static int getXsum(HashMap<Integer, Integer> map, int x) {
        if (map.size() < x) {
            int totalSum = 0;
            for (Integer key : map.keySet()) {
                totalSum += key * map.get(key);
            }
            return totalSum;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            else {
                return b[1] - a[1];
            }
        });

        int result = 0;

        for (Integer key : map.keySet()) {
            int number = key;
            int count = map.get(key);
            pq.offer(new int[]{number, count});
        }

        for (int i = 0; i < x; i++) {
            int[] entry = pq.poll();
            int number = entry[0];
            int count = entry[1];
            result += number * count;
        }

        return result;
    }
}
