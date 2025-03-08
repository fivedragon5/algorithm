package code.leetcode.daily.year2025.month02;

import java.io.IOException;

/**
 * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/description/?envType=daily-question&envId=2025-02-27
 *
 * 제한)
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 109
 *
 * 문제)
 *  1. 양의 정수로 이루어진 증가하는 배열 arr가 주어질 때, arr 에서 피보나치 수열과 유사한 가장 긴 부분수열의 길이를 반환
 *  2. 만약 존재하지 않을 경우 0 반환
 *
 * 풀이)
 *  1. 배열을 순회하면서 누적 최대, 최소 합을 갱신
 *  2. max - min 은 sum 이 가질 수 있는 가장 큰 변화량(최대 - 최소)를 절대값으로 반환
 *
 */

public class Question_20250227 {
    public static void main(String args[]) throws IOException {
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(lenLongestFibSubseq(arr));

//        int[] nums2 = new int[]{1,3,7,11,12,14,18};
//        System.out.println(lenLongestFibSubseq(nums2));
    }

    public static int lenLongestFibSubseq(int[] arr) {
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                answer = Math.max(fibonacci(arr, i, j, 0), answer);
            }
        }
        return answer == 0 ? 0 : answer + 2;
    }

    private static int fibonacci(int[] arr, int index1, int index2, int count) {
        for (int i = index2 + 1; i < arr.length; i++) {
            int sum = arr[index1] + arr[index2];
            if (sum == arr[i]) {
                return fibonacci(arr, index2, i,count + 1);
            } else if (sum < arr[i]) {
                return count;
            }
        }
        return count;
    }
}
