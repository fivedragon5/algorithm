package code.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/161988
 *
 * 제한)
 *  1 ≤ sequence의 길이 ≤ 500,000
 *  -100,000 ≤ sequence의 원소 ≤ 100,000
 *      sequence의 원소는 정수입니다.
 *
 * 문제)
 *  1. 어떤 수열의 연속 부분 수열에 같은 길이의 펄수 수열을 각 원소끼리 곱해 연속 펄스 부분 수열을 만드려고 한다.
 *  2. 정수 수열 sequence가 주어질 때, 연속 펄스 부분 수열의 합 중 가장 큰 것을 반환하기
 *
 * 풀이)
 *  부분합 (카데인 알고리즘)
 *  1. sequence의 원소를 순회하며, 짝수 인덱스와 홀수 인덱스에 따라 두 개의 스택을 유지
 *  2. 짝수 인덱스에서는 원소를 더하고, 홀수 인덱스에서는 원소를 빼는 방식으로 스택을 업데이트
 *  3. 각 스택의 최대 합을 유지하며, 최종적으로 두 스택 중 최대 값을 반환
 *  - max : 초기값 설정 주의
 *
 */
class Lesson161988 {
    public static void main(String[] args) {
        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        System.out.println(solution(sequence)); // 10
    }

    public static long solution(int[] sequence) {
        long max = Math.max(sequence[0], sequence[0] * -1);
        int len = sequence.length;
        long[] stackSum1 = new long[sequence.length];
        long[] stackSum2 = new long[sequence.length];
        stackSum1[0] = sequence[0];
        stackSum2[0] = sequence[0] * -1;

        for (int i = 1; i < len; i++) {
            int s = sequence[i];
            if (i % 2 == 0)  {
                stackSum1[i] = Math.max(stackSum1[i - 1] + s, s);
                stackSum2[i] = Math.max(stackSum2[i - 1] - s, s * -1);
            } else {
                stackSum1[i] = Math.max(stackSum1[i - 1] - s, s * -1);
                stackSum2[i] = Math.max(stackSum2[i - 1] + s, s);
            }
            max = Math.max(max, Math.max(stackSum1[i], stackSum2[i]));
        }

        return max;
    }
}
