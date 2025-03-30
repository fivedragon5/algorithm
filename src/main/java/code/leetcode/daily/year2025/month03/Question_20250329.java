package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/apply-operations-to-maximize-score/description/?envType=daily-question&envId=2025-03-29
 *
 * 제한)
 *  1 <= nums.length == n <= 10^5
 *  1 <= nums[i] <= 10^5
 *  1 <= k <= min(n * (n + 1) / 2, 10^9)
 *
 * 문제)
 *
 * 풀이)
 *
 */

public class Question_20250329 {
    public static void main(String args[]) throws IOException {

        List<Integer> nums = Arrays.asList(8,3,9,3,8);
        int k = 2;
        System.out.println(maximumScore(nums, k));

        List<Integer> nums2 = Arrays.asList(19,12,14,6,10,18);
        int k2 = 3;
        System.out.println(maximumScore(nums2, k2));
    }

    public static int maximumScore(List<Integer> nums, int k) {
        int mod = (int) 1e9 + 7;
        int[] primeData = new int[100001];
        int n = nums.size();
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {i, getPrimeNumber(nums.get(i), primeData), nums.get(i)};
        }
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int[] e : arr) {
            int i = e[0], f = e[1];
            while (!stk.isEmpty() && arr[stk.peek()][1] < f) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int f = arr[i][1];
            while (!stk.isEmpty() && arr[stk.peek()][1] <= f) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        Arrays.sort(arr, (a, b) -> b[2] - a[2]);
        long ans = 1;
        for (int[] e : arr) {
            int i = e[0], x = e[2];
            int l = left[i], r = right[i];
            long cnt = (long) (i - l) * (r - i);
            if (cnt <= k) {
                ans = ans * qpow(x, cnt) % mod;
                k -= cnt;
            } else {
                ans = ans * qpow(x, k) % mod;
                break;
            }
        }
        return (int) ans;
    }

    private static int qpow(long a, long n) {
        int mod = (int) 1e9 + 7;
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }

    private static int getPrimeNumber(int num, int[] primeData) {
        int number = num;
        if (primeData[num] != 0) {
            return primeData[num];
        }
        Set<Integer> set = new HashSet<>();
        int n = 2;
        while (n * n <= num) {
            if (num % n == 0) {
                set.add(n);
                num = num / 2;
            } else {
                n += 1;
            }
        }
        if (n > 1) {
            set.add(n);
        }
        primeData[number] = set.size();
        return set.size();
    }
}
