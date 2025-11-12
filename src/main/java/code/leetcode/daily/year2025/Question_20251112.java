package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/?envType=daily-question&envId=2025-11-12
 *
 * 제한)
 *  2 <= nums.length <= 50
 *  1 <= nums[i] <= 10^6
 *
 * 문제)
 *  1. 정수 배열 nums가 주어진다.
 *  2. 0 <= i < n-1 인덱스 i를 선택해 nums[i] or nums[i + 1] 중 하나를 그 두수의 gcd(최대 공약수)로 바꾼다
 *  3. 모든 원소를 1로 만들기 위해 필요한 최소 연산의 수를 반환
 *   - 만약 불가능 할 경우 -1을 반환한다.
 *
 * 풀이)
 *  1. 배열에 1이 하나라도 있을 경우, 나머지 수들을 1로 만드는데 필요한 연산의 수는 n - oneCount
 *  2. 모든 수의 gcd가 1이 아닐 경우, 1로 만드는 것이 불가능
 *  3. 모든 수를 탐색했을 때 gcd가 1이 가능할 경우, 최소 연산의 수를 계산
 *  4. i ~ j 구간에서 1을 만들기 위한 연산의 수는 (j - i)
 *  5. 최종적으로 minOperations + n - 2 반환
 *   - return minOperations + n - 2 이유
 *    - i ~ j 구간에서 1을 만들기 위한 연산의 수는 (j - i)
 *    - 구간 밖의 수들을 1로 만들기 위한 연산의 수 = 2
 *    - 따라서 전체 연산의 수는 (j - i) + (n - 2) = minOperations + n - 2
 *
 */

public class Question_20251112 {
    public static void main(String args[]) throws IOException {
        int[] nums = {2,6,3,4};
        System.out.println(minOperations(nums));

        nums = new int[]{2, 10, 6 ,14};
        System.out.println(minOperations(nums));

        nums = new int[]{6, 10 , 15};
        System.out.println(minOperations(nums));
    }

    public static int minOperations(int[] nums) {
        int n = nums.length;
        int oneCount = 0;
        int gcd = 0;
        for (int num : nums) {
            if (num == 1) oneCount++;
            gcd = findGcd(gcd, num);
        }
        // 1이 하나라도 있을 경우, 나머지 수들을 1로 만드는데 필요한 연산의 수는 n - oneCount
        if (oneCount > 0) return  n - oneCount;
        // 모든 수의 gcd가 1이 아닐 경우, 1로 만드는 것이 불가능
        if (gcd > 1) return -1;

        // 모든 수의 gcd가 1이 가능할 경우, 최소 연산의 수를 계산
        int minOperations = n;
        for (int i = 0; i < n; i++) {
            int currentGcd = 0;
            for (int j = i; j < n; j++) {
                currentGcd = findGcd(currentGcd, nums[j]);
                if (currentGcd == 1) {
                    // i ~ j 구간에서 1을 만들기 위한 연산의 수는 (j - i)
                    minOperations = Math.min(minOperations, j - i + 1);
                    break;
                }
            }
        }
        return minOperations + n - 2;
    }

    private static int findGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
