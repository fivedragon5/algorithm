package code.leetcode.daily.year2025.month02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/?envType=daily-question&envId=2025-02-15
 *
 * 제한)
 *  1 <= num <= 1000
 *
 * 문제)
 *  1. 주어진 1 ~ num 사이의 정수중 아래의 조건을 만족하는 원소의 제곱을 모두 더해서 반환하기
 *   - number = number^2 | number^2을 분할해서 모두 더한 값이 number를 만족
 *   ex) 1 : (1^2 = 1) 1 = 1 (O)
 *   ex) 2 : (2^2 = 4) 2 = 4 (X)
 *   ex) 9 : (9^2 = 81) 9 = 8+1 (O)
 *   ex) 11 : (11^2 = 121) 11 = (1+2+1 , 12+1, 1+21) (X)
 *
 * 풀이)
 *  1. 주어진 num만큼 2부터 반복문
 *   - 1은 무조건 포함되기에 항상 1을 더해주고 시작
 *  2. 반복되는 수의 제곱을 canPartition() 함수를 호출하여 조건을 만족하는지 판별
 *   - 제곱한 수를 백트래킹으로 모든 경우의수를 만족하는지 확인
 *  3. 만족할 경우 제곱한 수를 반환 만족하지 못 할 경우 0 반환
 *  4. 1번 사항에 호출한 함수에서 반환된 값을 누적해서 더해준다
 *  5. 더해준 값을 반환
 */

public class Question_20250215 {
    public static void main(String args[]) throws IOException {
        int num = 1;
        System.out.println(punishmentNumber(num));

        num = 37;
        System.out.println(punishmentNumber(num));

    }

    public static int punishmentNumber(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result += getPunishmentNumberSquare(i);
        }
        return result;
    }

    private static int getPunishmentNumberSquare(int num) {
        int square = num * num;
        String squareText = String.valueOf(square);
        if(canPartition(squareText, num, 0, 0)) {
            return square;
        }
        return 0;
    }

    private static boolean canPartition(String numText, int target, int index, int sum) {
        if (numText.length() == index) {
            return target == sum;
        }
        int currentNumber = 0;
        for(int i = index; i < numText.length(); i++) {
            currentNumber = currentNumber * 10 + (numText.charAt(i) - '0');
            // 이미 값을 초과했을 경우 종료
            if (sum + currentNumber > target) break;
            if (canPartition(numText, target, i + 1, currentNumber + sum)) {
                return true;
            }
        }
        return false;
    }
}
