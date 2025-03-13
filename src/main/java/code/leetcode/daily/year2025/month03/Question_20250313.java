package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/zero-array-transformation-ii/description/?envType=daily-question&envId=2025-03-13
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  0 <= nums[i] <= 5 * 10^5
 *  1 <= queries.length <= 10^5
 *  queries[i].length == 3
 *  0 <= li <= ri < nums.length
 *  1 <= vali <= 5
 *
 * 문제)
 *  1. 배열 nums, 2차원 배열 queries 주어짐
 *  2. queries[i] = {l(i), r(i), val(i)}로 이루어짐
 *  3. l(i), r(i) 는 각각 nums의 index로 시작 ~ 끝 index를 의미
 *  4. val(i) 는 최대 감소할 수 있는 값
 *  5. 배열 nums를 Zero Array 로 만들 수 있는 최소한의 queries(k) 갯수 를 반환하기
 *  6. queries로도 Zero Array를 만들 수 없다면 -1 반환
 *
 * 풀이)
 *  이분탐색 + 차분배열
 * 1. 이분탐색으로 ZeroArray를 만족하는 조건의 최소값을 찾기
 *   - O(logN)
 * 2. 조건을 만족하는지 확인하기 위해 차분배열 활용
 *  - 누적합 계산:O(N), 배열의 업데이트 O(1)
 *  - 이 로직(checkMakeZeroArray)에서는 O(end + nums.length)
 * 3. 차분배열
 *  - 변화의 시작, 끝 부분을 표시해서 업데이트를 수행하고, 차분배열을 누적해서 더해서 각 위치의 변화량을 계산 할 수 있음
 */

public class Question_20250313 {
    public static void main(String args[]) throws IOException {
        // diff : 0 0 0 | -4 0 4 | -5 0 5 | -9 0 9
        // arr  : 0 8 | -4 4 | -4 3 | -9 -1
        // sum = diff[0]
        // num[0] = num[0] + sum | -4, sum -4
        // num[1] = num[1] + diff[1] + sum -4
        int[] nums3 = new int[]{0,8};
        int[][] queries3 = new int[][]{{0,1,4},{0,1,1},{0,1,4}};
        System.out.println(minZeroArray(nums3, queries3));

        // diff : 0 0 0 0 | -1 0 0 -1 | -2 0 0 -2 | -2 3 -3 -2
        // arr : 2 0 2 | 1 -1 1 | 0 -2 0 | 0 1 0
        // sum = -1
        // num[0] = num[0] + sum |
        // num[1] = num[1] + sum + diff[1]

        int[] nums = new int[]{2,0,2};
        int[][] queries = new int[][]{{0,2,1},{0,2,1},{1,1,3}};
        System.out.println(minZeroArray(nums, queries));

        int[] nums2 = new int[]{4,3,2,1};
        int[][] queries2 = new int[][]{{1,3,2}, {0,2,1}};
        System.out.println(minZeroArray(nums2, queries2));
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        // nums의 원소가 전부 0 일 경우
        if (Arrays.stream(nums).allMatch(num -> num == 0)) {
            return 0;
        }
        int left = 0;
        int right = queries.length;
        int mid = left + right;
        int answer = mid;
        if (!checkMakeZeroArray(nums, queries, right)) {
            return -1;
        }
        while (left <= right) {
            mid = (left + right) / 2;
            if (checkMakeZeroArray(nums, queries, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private static boolean checkMakeZeroArray(int[] nums, int[][] queries, int end) {
        int[] diffArray = new int[nums.length + 1];
        for (int i = 0; i < end; i++) {
            int li = queries[i][0];
            int ri = queries[i][1];
            int vali = queries[i][2];

            diffArray[li] -= vali;
            diffArray[ri + 1] += vali;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += diffArray[i];
            if (nums[i] + sum > 0) return false;
        }
        return true;
    }


    private static boolean checkZeroArray(int[] nums, int[] diffArray) {
        int sum = diffArray[0];
        if (nums[0] + sum > 0) {
            return false;
        }
        for (int i = 1; i < nums.length; i++) {
            sum += diffArray[i];
            if (nums[i] + sum > 0) {
                return false;
            }
        }
        return true;
    }

    public static int minZeroArray2(int[] nums, int[][] queries) {
        Map<Integer, Integer> nonZeorArrayMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                nonZeorArrayMap.put(i, nums[i]);
            }
        }
        if (nonZeorArrayMap.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < queries.length; i++) {
            executeQuery(queries[i], nonZeorArrayMap);
            if (nonZeorArrayMap.isEmpty()) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void executeQuery(int[] query, Map<Integer, Integer> nonZeorArrayMap) {
        int start = query[0];
        int end = query[1];
        int maxValue = query[2];
        for (int i = start; i <= end; i++) {
            Integer value = nonZeorArrayMap.get(i);
            if (value != null) {
                if (value <= maxValue) {
                    nonZeorArrayMap.remove(i);
                } else {
                    nonZeorArrayMap.put(i, value - maxValue);
                }
            }
        }
    }
}
