package code.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 *
 * 제한)
 *  number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
 *  1 <= k < number.length (k는 자연수 이다)
 *
 * 문제)
 *  1. 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자 구하기
 *   ex) 1924에서 k=2를 제거하면 94가 가장 큰 숫자
 *
 * 풀이)
 *  1. 왼쪽부터 차례때로 각 숫자를 StringBuilder(Stack 처럼)에 넣기
 *  2. StringBuilder의 마지막 숫자가 현재 숫자보다 작고, k가 남아 있을 경우 가장 마지막 숫자 제거
 *  3. 이 과정을 반복하며 앞자리가 더 큰 숫자가 오도록 만든다.
 *  4. 마지막에 k가 남아있을 경우 뒤에서 부터 k개 만큼 자르기
 *
 */

class Lesson42883 {
    public static void main(String[] args) {
        String number5 = "44444";
        int k5 = 2;
        System.out.println(solution(number5, k5));


        String number = "1924";
        int k = 2;
        System.out.println(solution(number, k));

        String number2 = "1231234";
        int k2 = 3;
        System.out.println(solution(number2, k2));

        String number3 = "4177252841";
        int k3 = 4;
        System.out.println(solution(number3, k3));
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length();
        for (int i = 0; i < len; i++) {
            char currentNumber = number.charAt(i);
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < currentNumber) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(currentNumber);
        }
        return sb.substring(0, sb.length() - k);
    }
}
