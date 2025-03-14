package code.leetcode.daily.year2025.month03;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/?envType=daily-question&envId=2025-03-14
 *
 * 제한)
 *  1 <= candies.length <= 10^5
 *  1 <= candies[i] <= 10^7
 *  1 <= k <= 10^12
 *
 * 문제)
 *  1. 각 캔디의 더미는 하위 더미로 나눌 수 있다
 *   - 단 다른 더미와 합칠 순 없음
 *  2. k명의 아이들에게 사탕을 동일한 갯수만큼 나눠주려고 한다.
 *   - 이때 한 더미에서만 사탕을 나눠줄 수 있음
 *  3. 아이들에게 최대로 나눠줄 수 있는 사탕으 수를 구하기
 *
 * 풀이)
 *  이분 탐색 시간복잡도 : O(LogN)
 *  1. 이분탐색을 위한 left, right 변수 선언
 *   - left : 사탕을 나눌 수 있는 최소 갯 수 (1개)
 *   - right : 사탕은 한 더미에서만 줄 수 있기에 candies[i] 의 최대값을 right를 초기값으로 선언
 *   - answer : 정답을 담아둘 변수 (0 으로 선언)
 *  2. 이분 탐색을 진행하면서 사탕을 몇개씩 나눠야 하는지 탐색
 *   - 사탕을 나눌 수 있다면 left 값을 올리면서 탐색 진행
 *   - 사탕을 나눌 수 없다면 right 값을 내리면서 탐색 진행
 */

public class Question_20250314 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{5,8,6}; long k = 3L;
        System.out.println(maximumCandies(nums, k));  // 5

        int[] nums2 = new int[]{2, 5}; long k2 = 11L;
        System.out.println(maximumCandies(nums2, k2));  // 0
    }

    public static int maximumCandies(int[] candies, long k) {
        int maxCandy = 1; // max right
        for (int candy : candies) {
            maxCandy = Math.max(candy, maxCandy);
        }
        int answer = 0;
        int left = 1;
        int right = maxCandy;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (canDivideCandy(candies, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    private static boolean canDivideCandy(int[] candies, long k, int divideCount) {
        long count = 0;
        for (int candy : candies) {
            count += (candy / divideCount);
        }
        return count >= k;
    }
}
