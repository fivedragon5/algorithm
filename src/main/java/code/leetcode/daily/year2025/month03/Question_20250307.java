package code.leetcode.daily.year2025.month03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/closest-prime-numbers-in-range/description/?envType=daily-question&envId=2025-03-07
 *
 * 제한)
 *  1 <= left <= right <= 10^6
 *
 * 문제)
 *  1. 양의정수 left, right가 주어진다
 *  2. left, right 사이에 있는 prime 수를 찾아라
 *   - prime 수 : 1과 자기 자신으로만 나누어지는 수를 찾아라 (소수)
 *  3. 소수 2개 (num2 > num1)를 num2 - num1 연산해서 나온 수를 작은 차이를 가지는 쌍을 반환 하기
 *  4. 차이가 같다면 num1이 가장 작은 쌍을 반환 하면 됨
 *  5. 반환 할 수 없다면 [-1, -1] 을 반환하기
 *
 * 풀이)
 *  1. 주어진 수 left ~ right 를 순회하면서 소수일 경우 List에 add 해준다.
 *  2. 소수배열의 길이가 2 미만일 경우 -1, -1 반환
 *   - 소수 배열의 길이가 2 이상일 경우
 *      - int gap = Integer MAX 값으로 설정
 *      - index = 1 부터 array[i] - array[i-1] 를 해주면서 gap이 작은 값으로 갱신
 *      - gap이 같을 경우 작은 값으로 순회하기 때문에 작은경우에만 갱신해 주면 됨
 *  3. 소수 찾기 로직
 *   - 소수의 패턴 6k, 6k+1, 6k+2, 6k+3, 6k+4, 6k+5을 기억하자
 *   - 2,3은 예외로 두기
 *   - i*i 부터 찾기
 *   - 시간 복잡도 O(√n)
 */

public class Question_20250307 {
    public static void main(String args[]) throws IOException {
        int left = 24; int right = 26; //11, 13
        System.out.println(closestPrimes(left, right));
    }

    public static int[] closestPrimes(int left, int right) {
        List<Integer> primeNumberList = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (findPrimeNumber(i)) {
                primeNumberList.add(i);
            }
        }

        if (primeNumberList.size() < 2) {
            return new int[]{-1, -1};
        }

        int minGap = Integer.MAX_VALUE;
        int num1 = left;
        int num2 = right;

        for (int i = 1; i < primeNumberList.size(); i++) {
            if (minGap > primeNumberList.get(i) - primeNumberList.get(i - 1)) {
                num1 = primeNumberList.get(i - 1);
                num2 = primeNumberList.get(i);
                minGap = primeNumberList.get(i) - primeNumberList.get(i - 1);
            }
        }

        return new int[]{num1, num2};
    }

    // 소수 찾기
    private static boolean findPrimeNumber(int number) {

        if (number == 1) return false;
        if (number == 2 || number == 3) return true; // 2, 3은 소수
        if (number % 2 == 0 || number % 3 == 0) return false; // 2 또는 3의 배수는 소수 아님

        // 6의 배수 ± 1에 대해서만 검사 (빠른 소수 판별)
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }

        return true;
    }
}

/*
 소수의 패턴
  - 모든 소수는 6의 배수 +- 1 의 형태로 나타 낼 수 있다
    1. 단 2, 3은 예외로 소수
    2. 6k, 6k+1, 6k+2, 6k+3, 6k+4, 6k+5
     - 6k   : 소수 아님
     - 6k+2 : 소수 아님(짝수)
     - 6k+3 : 소수 아님(3의 배수)
     - 6k+4 : 소수 아님(짝수)
     - 6k+1, 6k+5 : 소수 가능성 있음
 */
