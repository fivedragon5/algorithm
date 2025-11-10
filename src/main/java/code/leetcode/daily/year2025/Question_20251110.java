package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/description/?envType=daily-question&envId=2025-11-10
 * 제한)
 *  1 <= n == nums.length <= 10^5
 *  0 <= nums[i] <= 10^5
 *
 * 문제)
 *  1. 정수 배열 nums가 주어진다.
 *  2. nums의 모든 원소를 0으로 만들기 위한 최소 연산 횟수 반환
 *   - 한번의 연산)
 *      - 1. nums에서 임의의 부분 배열을 선택 [i, j](0 <= i <= j < nums.length)
 *      - 2. 부분 배열에서 가장 작은 정수 x를 선택
 *      - 3. 부분 배열에서 가장 작은 정수를 0으로 만듬
 *
 * 풀이)
 *  1. 단조 증가 스택을 활용한 그리디
 *   - nums를 순회하며 단조 증가 스택 유지
 *   - 현재 원소가 스택 top보다 작으면 스택에서 제거 (증가 구간 종료)
 *   - 0은 건너뜀 (이미 0이니까 새 연산 불필요)
 *   - 새로운 "높이"가 등장한 경우 — push & 연산 추가
 *   - 최종 스택 크기가 최소 연산 횟수
 *
 */

public class Question_20251110 {
    public static void main(String args[]) throws IOException {
        int[] nums = {0, 2};
        System.out.println(minOperations(nums));

        nums = new int[]{1,2,3,4,1};
        System.out.println(minOperations(nums));

        nums = new int[]{3, 1, 2, 1};
        System.out.println(minOperations(nums));

        nums = new int[]{7,2,0,4,2};
        System.out.println(minOperations(nums));
    }

    public static int minOperations(int[] nums) {
        List<Integer> st = new ArrayList<>(); // 단조 증가 스택
        int count = 0; // 연산 횟수 카운트

        for (int ele : nums) {
            // 현재 값이 top보다 작으면 — 스택에서 제거 (증가 구간 종료)
            while (!st.isEmpty() && st.get(st.size() - 1) > ele)
                st.remove(st.size() - 1);

            // 0은 건너뜀 (이미 0이니까 새 연산 불필요)
            if (ele == 0) continue;

            // 새로운 "높이"가 등장한 경우 — push & 연산 추가
            if (st.isEmpty() || st.get(st.size() - 1) < ele) {
                st.add(ele);
                count++;
            }
        }
        return count;
    }

    public static int minOperations2(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private static int process(int[] nums, int l, int r) {
        if (l > r) return 0;

        int total = 0;
        int i = l;

        while (i <= r) {
            // 1) 스킵: 0인 구간은 건너뛴다 (이미 처리된 구간)
            while (i <= r && nums[i] == 0) i++;
            if (i > r) break;

            // 2) 연속된 0이 아닌 블록 찾기: [i, j-1]
            int j = i;
            while (j <= r && nums[j] != 0) j++;

            // 3) 해당 블록의 최소값 찾기
            int minVal = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                if (nums[k] < minVal) minVal = nums[k];
            }

            // 4) 한 번의 연산으로 minVal을 0으로 만들기 (블록 내부 모든 비0 원소에서 빼기)
            for (int k = i; k < j; k++) {
                nums[k] -= minVal;
            }

            // 5) 이 블록을 더 쪼갤 수 있으므로 재귀로 내부 처리 (minVal 연산 1회 + 내부 연산)
            int opsForBlock = 1 + process(nums, i, j - 1);

            // 6) 블록을 원소별로 제거하는 것(길이)과 비교
            total += Math.min(opsForBlock, j - i);

            // 다음 블록으로 이동
            i = j;
        }

        return total;
    }
}
