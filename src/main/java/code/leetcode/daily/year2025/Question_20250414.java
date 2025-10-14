package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-good-triplets/description/?envType=daily-question&envId=2025-04-14
 *
 * 제한)
 *  3 <= arr.length <= 100
 *  0 <= arr[i] <= 1000
 *  0 <= a, b, c <= 1000
 *
 * 문제)
 *  1. (arr[i], arr[j], arr[k])가 아래의 조건을 만족하는 삼중항을 찾기
 *   - 0 <= i < j < k < arr.length
 *   - |arr[i] - arr[j]| <= a
 *   - |arr[j] - arr[k]| <= b
 *   - |arr[i] - arr[k]| <= c
 *
 * 풀이)
 *  1. i,j,k 3중 for문을 사용해 위 조건식을 확인해서 가능한 삼중항을 count 해주기
 *
 */

public class Question_20250414 {
    public static void main(String args[]) throws IOException {
        int[] arr = new int[]{3,0,1,1,9,7}; int a = 7; int b = 2; int c = 3;
        System.out.println(countGoodTriplets(arr, a, b, c));

        int[] arr2 = new int[]{1,1,2,2,3}; int a2 = 0; int b2 = 0; int c2 = 1;
        System.out.println(countGoodTriplets(arr2, a2, b2, c2));
    }

    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int answer = 0;
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int arr_i = arr[i];
                int arr_j = arr[j];
                if (Math.abs(arr_i - arr_j) > a) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    int arr_k = arr[k];
                    if (Math.abs(arr_j - arr_k) <= b && Math.abs(arr_i - arr_k) <= c) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
}
