package code.leetcode.daily;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/tuple-with-same-product/?envType=daily-question&envId=2025-02-06
 *
 * 제한)
 *  1 <= nums.length <= 1000
 *  1 <= nums[i] <= 104
 *  All elements in nums are distinct.
 *
 * 문제)
 *  1. 자연수 배열 nums가 주어진다
 *  2. nums에서 4개를 뽑아 (a,b,c,d) | a * b = c * d 를 만족하는 (a,b,c,d) 쌍을 구하시오
 *   - a != b != c != d
 *
 * 풀이)
 *  1. 백트래킹 조합 -> Memory Limit Exceeded
 *
 *  1. 2개씩 뽑아 곱한 수를 Map에 저장 (누적)
 *  2. Map에 저장할때 이미 값이 있을 경우 정답카운트 추가
 */

public class Question_20250206 {
    public static void main(String args[]) throws IOException {

        int[] nums = {2,3,4,6};
        System.out.println(tupleSameProduct(nums));

        int[] nums2 = {1,2,4,5,10};
        System.out.println(tupleSameProduct(nums2));
    }

    public static int tupleSameProduct(int[] nums) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int calc = nums[i] * nums[j];
                int mapData = map.getOrDefault(calc, 0);
                answer += mapData * 8;
                map.put(calc, mapData + 1);
            }
        }
        return answer;
    }

    public static int tupleSameProduct2(int[] nums) {
        int answer = 0;
        List<List<Integer>> combinationList = new ArrayList<>();
        combination(nums, new ArrayList<>(), new boolean[nums.length], 0, combinationList);
        for (List<Integer> list : combinationList) {
            if (list.get(0) * list.get(1) == list.get(2) * list.get(3)) {
                answer++;
            } else if (list.get(0) * list.get(2) == list.get(1) * list.get(3)) {
                answer++;
            } else if (list.get(0) * list.get(3) == list.get(1) * list.get(2)) {
                answer++;
            } else {

            }
        }
        return answer * 8;
    }

    // 백트래킹
    private static void combination(int[] nums, List<Integer> list, boolean[] visited, int start, List<List<Integer>> combinationList) {
        if (list.size() == 4) {
            combinationList.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);

                combination(nums, list, visited, i + 1, combinationList);

                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
