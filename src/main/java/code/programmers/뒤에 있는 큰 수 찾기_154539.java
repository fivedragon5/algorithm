package code.programmers;

import java.util.Stack;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/154539
 *
 * 제한)
 *  4 ≤ numbers의 길이 ≤ 1,000,000
 *  1 ≤ numbers[i] ≤ 1,000,000
 *
 * 문제)
 *  1. 정수로 이루어진 배열 numbers가 주어진다.
 *  2. 배열의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수 라고 한다.
 *  3. 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 반환하기
 *   - 단 뒷 큰수가 존재하지 않을 경우 -1 반환
 *
 * 풀이)
 *   스택을 이용한 풀이
 *      1. 정답 배열의 마지막 원소는 뒷 큰수가 없으므로 -1로 초기화
 *      2. 스택에 마지막 원소를 추가
 *      3. 뒤에서부터 순회하면서 현재 숫자와 스택의 top을 비교
 *      - 현재 숫자보다 큰 경우, 뒷 큰수를 찾은 것이므로 정답 배열에 추가
 *      - 현재 숫자보다 작거나 같은 경우, 스택에서 제거
 *    * 주의
 *      일반적인 탐색으로 풀 경우, 시간초과 발생
 *          - Test Case 20, 22
 *
 */
class Lesson154539 {
    public static void main(String[] args) {
        int[] numbers = new int[]{2, 3, 3, 5};
        System.out.println(solution(numbers)); // 3,5,5,-1

        int[] numbers2 = new int[]{9, 1, 5, 3, 6, 2};
        System.out.println(solution(numbers2)); // -1,5,6,6,-1,-1
    }

    public static int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        answer[n-1] = -1; // 마지막 원소는 뒷 큰수가 없으므로 -1
        Stack<Integer> stack = new Stack<>();
        stack.add(numbers[n - 1]); // 마지막 원소를 스택에 추가

        for (int i = n - 2; i >= 0; i--) {
            int currentNumber = numbers[i];
            boolean found = false;
            while (!stack.isEmpty()) {
                if (stack.peek() > currentNumber) {
                    answer[i] = stack.peek(); // 뒷 큰수 찾음
                    found = true;
                    break;
                } else {
                    stack.pop(); // 현재 숫자보다 작거나 같은 수는 제거
                }
            }
            stack.add(currentNumber); // 현재 숫자를 스택에 추가
            if (!found) {
                answer[i] = -1; // 뒷 큰수가 없으면 -1
            }
        }

        return answer;
    }
}
