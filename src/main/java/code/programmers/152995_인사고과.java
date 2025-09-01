package code.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/152995
 *
 * 제한)
 *  1 ≤ scores의 길이 ≤ 100,000
 *  scores의 각 행은 한 사원의 근무 태도 점수와 동료 평가 점수를 나타내며 [a, b] 형태입니다.
 *      scores[0]은 완호의 점수입니다.
 *      0 ≤ a, b ≤ 100,000
 *  완호가 인센티브를 받지 못하는 경우 -1을 return 합니다.
 *
 * 문제)
 *  1. 각 사원마다 근무 태도 점수와 동료 평과 점수가 기록되어 있다.
 *  2. 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한번이라도 있을 경우 그 사원은 인센티브를 받을 수 없다.
 *  3. 그렇지 않은 사원들에 대해 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브가 차등 지급된다.
 *   - 만약 점수의 합이 동일한 사원들일 경우, 동석차이며, 동석차의 수만큼 다음 석차는 건너뛴다.
 *  4. 완호의 인센티브 석차를 return 하기
 *
 * 풀이)
 *  1. 근무 태도 점수를 기준으로 내림차순 정렬, 동료 평가 점수를 기준으로 오름차순 정렬
 *   - 이렇게 정렬하면, 근무 태도 점수가 높은 사원부터 보면서 동료 평가 점수가 이전까지 본 동료 평가 점수의 최대값보다 낮으면 인센티브 불가
 *  2. 인센티브 받을 수 있는 사원들 중에서 완호보다 점수 합이 높은 사원이 몇 명인지 세기
 *  3. 완호의 석차는 (완호보다 점수 합이 높은 사원 수 + 1)
 *
 */

class Lesson152995 {
    public static void main(String[] args) {
        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        System.out.println(solution(scores));
    }

    public static int solution(int[][] scores) {
        int n = scores.length;
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        // 근무 태도 점수 내림차순, 동료 평가 점수 오름차순 정렬
        Arrays.sort(scores, (a,b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int maxPointB = 0; // 지금까지 본 동료평가 점수 최대값
        int rank = 1; // 완호 등수 초기값

        // 인센티브 받을 수 없는 사람 제외 및 완호 등수 결정
        for (int i = 0; i < n; i++) {
            int pointA = scores[i][0];
            int pointB = scores[i][1];
            int currentSum = pointA + pointB;

            // 만약 이전까지 본 동료평가 점수 최대값보다 낮으면 인센티브 불가
            if (pointB < maxPointB) {
                // 완호라면 -1 반환
                if (pointA == wanhoA && pointB == wanhoB) {
                    return -1;
                }
                continue; // 인센티브 대상에서 제외
            }

            // 동료 점수 최대값 갱신
            maxPointB = pointB;

            // 완호보다 점수 합이 높으면 완호의 등수 밀림
            if (currentSum > wanhoSum) {
                rank++;
            }
        }
        return rank;
    }
}
