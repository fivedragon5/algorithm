package code.programmers;

import java.util.HashSet;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/388352
 *
 * 제한)
 *  10 ≤ n ≤ 30
 *  1 ≤ (q의 길이 = m) ≤ 10
 *  ans의 길이 = m
 *  비밀 코드가 존재하지 않는(답이 0인) 경우는 주어지지 않습니다.
 *
 * 문제)
 *  1. 시스템은 1 부터 n까지의 정수로 이루어진 비밀코드 5자리가 있다.
 *  2. 이 비밀코드를 맞추기 위해 m번 시도했을 경우 그중 몇개가 비밀코드에 포함되어 있는지 알려준다.
 *  3. 이 때 비밀코드로 가능한 조합의 개수를 반환하기
 *
 * 풀이)
 *  1. 주어진 n 만큼 비밀 코드의 조합 전부 구하기
 *  2. 각 조합에 대해 주어진 q의 조합과 ans의 정답을 비교하여 맞는지 확인하기
 *  3. 맞는 조합의 개수를 세기
 */
class Lesson388352 {
    public static void main(String[] args) {
        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans = {2, 3, 4, 3, 3};
        System.out.println(solution(n, q, ans));

        int n2 = 15;
        int[][] q2 = {{2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}};
        int[] ans2 = {2, 1, 3, 0, 1};
        System.out.println(solution(n2, q2, ans2));
    }
    private static int N; // 비밀코드의 숫자 범위 ex) N=10 일 경우 1~10 까지의 숫자
    private static int[][] Q; // 비밀코드 조합
    private static int[] ANS; // 비밀코드 조합의 정답
    private static int ANSWER = 0;

    public static int solution(int n, int[][] q, int[] ans) {
        // 1. 모든 조합의 경우의수 추가
        N = n;
        Q = q;
        ANS = ans;

        getAllCombination(1, 0, new HashSet<>()); // 1부터 n까지의 숫자 중 5개를 뽑는 조합

        return ANSWER;
    }

    private static void getAllCombination(int start, int currentDepth, HashSet<Integer> set) {
        if (currentDepth == 5) {
            checkAnswer(set);
            return;
        }
        for (int i = start; i <= N; i++) {
            set.add(i);
            getAllCombination( i + 1, currentDepth + 1, set);
            set.remove(i);
        }
    }

    // 조건에 만족하는지 확인
    private static void checkAnswer(HashSet<Integer> set) {
        for (int i = 0; i < Q.length; i++) {
            int count = 0;
            int[] q = Q[i];
            for (int code : q) {
                if (set.contains(code)) {
                    count++;
                }
            }
            if (count != ANS[i]) {
                return;
            }
        }
        ANSWER++;
    }
}
