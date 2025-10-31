package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/description/?envType=daily-question&envId=2025-10-31
 *
 * 제한)
 *  2 <= n <= 100
 *  nums.length == n + 2
 *  0 <= nums[i] < n
 *  입력받은 nums 배열에 정확히 두 개의 숫자가 중복되어 있다
 *
 * 문제)
 *  1. Digitville 마을에는 0 ~ n-1까지의 숫자가 각각 한 번씩 포함된 배열이 있다.
 *  2. 각 숫자는 리스트에 정확히 한 번씩만 나타나야 한다.
 *  3. 하지만, 숫자 2개가 한 번 더 들어 오면서 리스트의 길이가 평소보다 길어졌다.
 *  4. 순서 상관없이 중복된 숫자를 찾아 int[] 형태로 반환
 *
 * 풀이)
 *  1. 크기가 100인 배열을 만들어 각 숫자가 몇 번 나왔는지 카운트
 *  2. 2번 이상 나온 숫자를 answer 배열에 담아 반환
 *
 */

public class Question_20251031 {
    public static void main(String args[]) throws IOException {
        int[] nums = {0,1,1,0};
        System.out.println(getSneakyNumbers(nums));
    }

    private static int[] getSneakyNumbers(int[] nums) {
        int[] number = new int[100];
        int[] answer = new int[2];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            number[nums[i]]++;
            if (number[nums[i]] >= 2) {
                answer[count++] = nums[i];
            }
        }
        return answer;
    }
}
