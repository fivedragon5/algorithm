package code.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java
 * 제한)
 * 1 <= targets <= 500,000
 * [s, e]
 * 0 <= s < e<= 100,000,000
 *
 * 문제)
 * 1. 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수의 최소값
 * 풀이)
 */
class Lesson181188 {
    public static void main(String[] args) {
        System.out.println("===START===");
        int[][] parameter = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        System.out.println(solution(parameter) + "");
        System.out.println("===END===");
    }

    private static int solution(int[][] targets) {
        // 1. 끝 지점을 기준으로 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int missileCount = 0; // 최소 요격 미사일 수
        double lastInterceptPoint = -1; // 마지막 요격 지점

        // 2. 각 폭격 미사일을 순회
        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            // 3. 현재 요격 지점이 미사일의 시작 지점보다 작다면 요격이 필요함
            if (lastInterceptPoint < start) {
                missileCount++; // 새로운 요격 미사일 발사
                lastInterceptPoint = end - 0.5; // 요격 지점을 현재 미사일의 끝 지점의 직전으로 갱신
            }
        }
        return missileCount;
    }
}
