package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/?envType=daily-question&envId=2025-11-05
 *
 * 제한)
 *  nums.length == n
 *  1 <= n <= 10^5
 *  1 <= nums[i] <= 10^9
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
 *  풀이1) 슬라이딩 윈도우 + 우선순위 큐
 *   -> 매번 우선순위 큐를 구성하기 때문에 시간복잡도 O(k log k) 작업 반복 -> 시간초과발생
 *  풀이2) 슬라이딩 윈도우 + TreeSet
 *   - count : 현재 윈도우 내 각 숫자의 등장 빈도 저장
 *   - top : 상위 x개 빈도 원소를 빈도 내림차순, 값 내림차순으로 정렬하여 저장
 *   - bot : 나머지 원소들을 빈도 내림차순, 값 내림차순으로 정렬하여 저장
 *   - update() : 원소 빈도 변경 시 top, bot의 집합에서 원소를 추가/삭제하고 WINDOW_SUM(상위 x개 원소들의 합)을 동적으로 유지
 *  1. 윈도우를 한 칸 움직일 때마다 update를 두 번 호출하여 빈도를 갱신
 *  2. 결과 배열에 각 구간의 x-sum 을 기록
 *
 */

public class Question_20251105 {
    public static void main(String args[]) throws IOException {
        int[] nums = {3,8,7,8,7,5};
        int k = 2;
        int x = 2;
        System.out.println(Arrays.toString(findXSum(nums, k, x)));

//        int[] nums = {1,1,2,2,3,4,2,3};
//        int k = 6;
//        int x = 2;
//        System.out.println(Arrays.toString(findXSum(nums, k, x)));

    }



    private static long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] ans = new long[n - k + 1];

        Map<Integer, Integer> count = new HashMap<>();

        TreeSet<Map.Entry<Integer, Integer>> top = new TreeSet<>(
                (a, b) -> {
                    if (!Objects.equals(a.getKey(), b.getKey())) {
                        return b.getKey() - a.getKey(); // 빈도 내림차순
                    }
                    return b.getValue() - a.getValue(); // 값 내림차순
                }
        );

        TreeSet<Map.Entry<Integer, Integer>> bot = new TreeSet<>(
                (a, b) -> {
                    if (!Objects.equals(a.getKey(), b.getKey())) {
                        return b.getKey() - a.getKey(); // 빈도 내림차순
                    }
                    return b.getValue() - a.getValue(); // 값 내림차순
                }
        );

        long windowSum = 0L;

        for (int i = 0; i < n; i++) {
            windowSum = update(nums[i], 1, count, top, bot, x, windowSum);
            if (i >= k) {
                windowSum = update(nums[i - k], -1, count, top, bot, x, windowSum);
            }
            if (i >= k - 1) {
                ans[i - k + 1] = windowSum;
            }
        }

        return ans;
    }

    private static long update(int num, int delta, Map<Integer, Integer> count,
                               TreeSet<Map.Entry<Integer, Integer>> top,
                               TreeSet<Map.Entry<Integer, Integer>> bot, int x,
                               long windowSum) {
        int oldCount = count.getOrDefault(num, 0);
        Map.Entry<Integer, Integer> oldEntry = new AbstractMap.SimpleEntry<>(oldCount, num);

        if (oldCount > 0) {
            if (!bot.remove(oldEntry)) {
                top.remove(oldEntry);
                windowSum -= (long) num * oldCount;
            }
        }

        int newCount = oldCount + delta;

        if (newCount > 0) {
            Map.Entry<Integer, Integer> newEntry = new AbstractMap.SimpleEntry<>(newCount, num);
            bot.add(newEntry);
            count.put(num, newCount);
        } else {
            count.remove(num);
        }

        while (top.size() < x && !bot.isEmpty()) {
            Map.Entry<Integer, Integer> candidate = bot.pollFirst();
            top.add(candidate);
            windowSum += (long) candidate.getValue() * candidate.getKey();
        }

        while (!bot.isEmpty() && !top.isEmpty()) {
            Map.Entry<Integer, Integer> botMax = bot.first();
            Map.Entry<Integer, Integer> topMin = top.last();

            if (compareEntry(botMax, topMin) > 0) {
                bot.remove(botMax);
                top.remove(topMin);

                bot.add(topMin);
                top.add(botMax);

                windowSum += (long) botMax.getValue() * botMax.getKey();
                windowSum -= (long) topMin.getValue() * topMin.getKey();
            } else {
                break;
            }
        }
        return windowSum;
    }

    private static int compareEntry(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
        if (!Objects.equals(a.getKey(), b.getKey())) {
            return a.getKey() - b.getKey(); // 빈도 오름차순 비교
        }
        return a.getValue() - b.getValue(); // 값 오름차순 비교
    }
}
