package code.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/388351
 *
 * 제한)
 *  1 ≤ schedules의 길이 = n ≤ 1,000
 *      schedules[i]는 i + 1번째 직원이 설정한 출근 희망 시각을 의미합니다.
 *      700 ≤ schedules[i] ≤ 1100
 *  1 ≤ timelogs의 길이 = n ≤ 1,000
 *      timelogs[i]의 길이 = 7
 *      timelogs[i][j]는 i + 1번째 직원이 이벤트 j + 1일차에 출근한 시각을 의미합니다.
 *      600 ≤ timelogs[i][j] ≤ 2359
 *  1 ≤ startday ≤ 7
 *      1은 월요일, 2는 화요일, 3은 수요일, 4는 목요일, 5는 금요일, 6은 토요일, 7은 일요일에 이벤트를 시작했음을 의미합니다.
 *      출근 희망 시각과 실제로 출근한 시각을 100으로 나눈 나머지는 59 이하입니다.
 *
 * 문제)
 *  1. 일주일동안 각자 설정한 출근 희망 시각에 늦지 않고 출근한 직원들에게 상품을 주는 이벤트를 진행한다.
 *  2. 직원들은 일주일동안 자신이 설정한 "출근 희망 시각 + 10분" 까지 어플로 출근해야 한다.
 *  3. 단, 토요일, 일요일 출근 시각은 이벤트에 영향을 끼치지 않는다.
 *  4. 직원들은 매일 한번씩만 어플로 출근하고, 모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현
 *  5. 상품을 받을 수 있는 직원이 몇명인지 반환하기
 *
 * 풀이)
 *  1. 각 직원의 출근 희망 시각을 분으로 변환
 *  2. 각 직원의 timelogs를 순회하며, 출근 희망 시각 + 10분 이내에 출근했는지 확인
 *  3. 토요일, 일요일은 제외하고, 출근 시각이 출근 희망 시각 + 10분 이내인 경우에만 카운트
 *  4. 카운트된 직원 수를 반환
 *
 */
class Lesson388351 {
    public static void main(String[] args) {
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {
            {710, 2359, 1050, 700, 650, 631, 659},
            {800, 801, 805, 800, 759, 810, 809},
            {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };
        int startday = 5;
        System.out.println(solution(schedules, timelogs, startday)); // 1
    }

    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int len = schedules.length;
        int count = 0;
        int excludeIndex1 = 7 - startday;
        int excludeIndex2  = excludeIndex1 == 0 ? 6 : excludeIndex1 - 1;
        for (int i = 0; i < len; i++) {
            int expectedStartTime = schedules[i];
            int expectedStartMinute = (expectedStartTime / 100) * 60 + expectedStartTime % 100;
            boolean flag = true;
            for (int j = 0; j < 7; j++) {
                if (j == excludeIndex1 || j == excludeIndex2) {
                    continue;
                }
                // 시간 변환
                int time = timelogs[i][j];
                int minute = (time / 100) * 60 + time % 100;
                if (minute - expectedStartMinute > 10) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }
}
