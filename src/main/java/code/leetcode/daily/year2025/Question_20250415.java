package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/count-good-triplets-in-an-array/description/?envType=daily-question&envId=2025-04-15
 *
 * 제한)
 *  n == nums1.length == nums2.length
 *  3 <= n <= 10^5
 *  0 <= nums1[i], nums2[i] <= n - 1
 *  nums1 and nums2 are permutations of [0, 1, ..., n - 1].
 *
 * 문제)
 *
 * 풀이)
 *  PBDS 풀이 참고
 *  1. nums1 숫자의 인덱스 저장 mpp
 *  2. sorted_set 정렬된 집한 사용
 *  3. nums2를 순회하면서 만들어둔 nums1의 index를 찾고
 *     해당 index를 기준으로 정렬된 집합에서 작은요소, 큰요소의 갯수를 탐색
 *  4. left, right를 곱해 nums[i]가 삼중항의 중간요소가 되는 삼중항의 개수를 반환
 */

public class Question_20250415 {
    public static void main(String args[]) throws IOException {
        int[] nums1 = new int[]{2,0,1,3};
        int[] nums2 = new int[]{0,1,2,3};
        System.out.println(goodTriplets(nums1, nums2));

        int[] nums3 = new int[]{4,0,1,3,2};
        int[] nums4 = new int[]{4,1,0,2,3};
        System.out.println(goodTriplets(nums3, nums4));
    }public static long goodTriplets(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            mpp.put(nums1[i], i);
        }

        int n = nums2.length;
        long total = 0;

        ArrayList<Integer> st = new ArrayList<>();

        for (int x : nums2) {
            int idx = mpp.get(x);
            // idx보다 작은 요소의 갯수
            int left = orderOfKey(st, idx);
            // idx보다 큰 요소의 개수
            int right = (n - 1 - idx) - (st.size() - left);
            // nums2[i]가 삼중항의 중간 요소가 되는 삼중항의 개수 탐색
            total += (long) left * right;

            int pos = Collections.binarySearch(st, idx);
            if (pos < 0) pos = -pos - 1;
            st.add(pos, idx);
        }
        return total;
    }

    // 정렬된 st에서 key보다 작은 값을 조회
    private static int orderOfKey(ArrayList<Integer> st, int key) {
        int pos = Collections.binarySearch(st, key);
        return pos < 0 ? -pos - 1 : pos;
    }
}
