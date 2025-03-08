package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/description/?envType=daily-question&envId=2025-03-02
 *
 * 제한)
 * 1 <= nums1.length, nums2.length <= 200
 * nums1[i].length == nums2[j].length == 2
 * 1 <= idi, vali <= 1000
 *
 * 문제)
 *  1. 주어진 배열의 첫번쨰 원소는 ID, 두번째 원소는 value
 *  2. num1.id + num2.id = id : num1 + num2
 *  3. 새로운 배열을 만들기
 *
 * 풀이)
 *  result[id][2] : result[id][0] = id, result[id][1] = value
 *  1. nums1, nums2 을 순회하면서 result[id][1] 에 값을 갱신
 */

public class Question_20250302 {
    public static void main(String args[]) throws IOException {
        int[][] nums1 = new int[][]{{1,2}, {2,3}, {4,5}};
        int[][] nums2 = new int[][]{{1,4}, {3,2}, {4,1}};
        System.out.println(mergeArrays(nums1, nums2));

        int[][] nums3 = new int[][]{{2,4}, {3,6}, {5,5}};
        int[][] nums4 = new int[][]{{1,3}, {4,3}};
        System.out.println(mergeArrays(nums3, nums4));
    }

    public static int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int num1Length = nums1.length;
        int num2Length = nums2.length;
        int maxLength = Math.max(num1Length, num2Length);
        int numMaxId = Math.max(nums1[num1Length - 1][0], nums2[num2Length - 1][0]);
        int[][] result = new int[numMaxId][2];

        int index = 0;

        while (index < maxLength) {
            if (num1Length > index) {
                int id = nums1[index][0];
                result[id-1][1] += nums1[index][1];
            }

            if (num2Length > index) {
                int id = nums2[index][0];
                result[id-1][1] += nums2[index][1];
            }
            index++;
        }

        List<int[]> answer = new ArrayList<>();

        for (int i = 0; i < result.length; i++) {
            if (result[i][1] != 0) {
                answer.add(new int[]{i + 1, result[i][1]});
            }
        }
        return answer.toArray(new int[0][]);
    }
}
