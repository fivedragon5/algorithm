package code.leetcode.daily;

import java.io.IOException;

/**
 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/?envType=daily-question&envId=2025-02-16
 *
 * 제한)
 *  1 <= n <= 20
 *
 * 문제)
 *  1. 배열을 만들기
 *   - 1은 단 1개만 배열에 넣을 수 있음
 *   - 1을 제외한 나머지 수는 2번 넣을 수 있음
 *   - 두숫자 a[i], a[j] 사이의 거리는 |i - j| 로 정의
 *   - 숫자 N 끼리의 거리는 정확히 N 이여야 한다.
 *   - [3,1,2,3,2]
 *  2. 만들 수 있는 배열 중 사전순으로 가장 큰 수열을 반환하기
 *
 * 풀이)
 *  1. index[0] 부터 넣을 수 있는 가장 큰수를 배치
 *   - 배치할 수 없을 경우 다음에 올 수 있는 가장 큰수를 배치
 *   - 배치하다가 조건에 만족하지 않을경우 백트레킹으로 다음 경우에 수를 탐색
 */

public class Question_20250216 {
    public static void main(String args[]) throws IOException {
        // n : 2 | 2 1 2
        // n : 3 | 3 1 2 3 2
        // n : 4 | 4 2 3 2 4 1 3
        // n : 5 | 5 3 1 4 3 5 2 4 2



        int num = 5;
        System.out.println(constructDistancedSequence(num));

//        num = 5;
//        System.out.println(constructDistancedSequence(num));

    }

    public static int[] constructDistancedSequence(int n) {
        int size = n * 2 - 1;
        int[] result = new int[size];
        boolean[] visited = new boolean[n+1];
        back(result, visited, 0, n);
        return result;
    }

    private static boolean back(int[] result, boolean[] visited, int index, int number) {
        if (index == result.length) {
            return true;
        }
        if (result[index] != 0) {
            return back(result, visited, index + 1, number);
        }

        for (int i = number; i >= 1; i--) {
            if (visited[i]) {
                continue;
            }
            if (i == 1) {
                result[index] = i;
                visited[i] = true;
                if (back(result, visited, index + 1, number)) {
                    return true;
                }
                result[index] = 0;
                visited[i] = false;
            } else {
                if (index + i < result.length && result[index + i] == 0) {
                    result[index] = result[index + i] = i;
                    visited[i] = true;
                    if (back(result, visited, index + 1, number)) {
                        return true;
                    }
                    result[index] = result[index + i] = 0;
                    visited[i] = false;
                }
            }
        }

        return false;
    }
}
