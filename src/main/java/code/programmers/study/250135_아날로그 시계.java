package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/250135
 *
 * 제한)
 *  0 ≤ h1, h2 ≤ 23
 *  0 ≤ m1, m2 ≤ 59
 *  0 ≤ s1, s2 ≤ 59
 *  h1시 m1분 s1초부터 h2시 m2분 s2초까지 알람이 울리는 횟수를 센다는 의미입니다.
 *      h1시 m1분 s1초 < h2시 m2분 s2초
 *      시간이 23시 59분 59초를 초과해서 0시 0분 0초로 돌아가는 경우는 주어지지 않습니다.
 *
 * 문제)
 *  1. 시,분,초 침이 있는 아날로그 시계가 있다.
 *  2. 시계의 시침은 12시간마다, 분침은 60분 마다, 초침은 60초마다 시계를 한 바퀴 돈다.
 *  3. 시,분,초 침이 움직이는 속도는 일정하며 각각 다르다
 *  4. 이 시계는 초침이 시,분 침과 겹칠 때마다 알람이 울린다.
 *  5. 특정 시간동안 알람이 울린 횟수 구하기.
 *
 * 풀이)
 *  - 1분에 초침은 1바퀴 = 1바퀴 시첨,분침 만남 = 알람 2번
 *      = 1 : 120 = 24 : 2880 -> 2852 (28)
 *  - 59분 -> 0분 으로 넘어갈 때, 분침과 초침은 만나지 않음
 *      -> 1시간 마다 1번의 알림 생략 24시간 -> 24번 생략
 *  - 시침, 분침, 초침이 정각으로 모였을때의 소실 -> 0시 0분 0초, 12시, 0분, 0초 2곳
 *      -> 시침,분침 * 2 약 4번 소실
 *  00:00:00 ~ h2:m2:s2 까지 알람이 울린 수 - 00:00:00 ~ h1:m1:s1 알람이 울린 수
 *  1. 주어진 시간을 초로 변환
 *  2. 초침이 시,분 침과 만나는 수를 각각 더해준다.
 *   - 1시간 기준 분침:초침 = 59, 시침:초침 = 719
 *  3. 12시가 넘었을 경우 시,분,초가 하나로 모이는 케이스로 1을 뺴준다.
 *  4. 시작시간이 정각에서 시작하는 경우 알람이 1번 울리고 시작하기에 정답에서 +1을 해준다.
 *
 */
class Lesson250135 {
    public static void main(String[] args) {
        int h1 = 0; int m1 = 5; int s1 = 30;
        int h2 = 0; int m2 = 7; int s2 = 0;
        System.out.println(solution(h1, m1, s1, h2, m2, s2)); // 2

        h1 = 12; m1 = 0; s1 = 0;
        h2 = 12; m2 = 0; s2 = 30;
        System.out.println(solution(h1, m1, s1, h2, m2, s2)); // 1

        h1 = 0; m1 = 6; s1 = 1;
        h2 = 0; m2 = 6; s2 = 6;
        System.out.println(solution(h1, m1, s1, h2, m2, s2)); // 0

        h1 = 11; m1 = 59; s1 = 30;
        h2 = 12; m2 = 0; s2 = 0;
        System.out.println(solution(h1, m1, s1, h2, m2, s2)); // 2

        h1 = 0; m1 = 0; s1 = 0;
        h2 = 23; m2 = 59; s2 = 59;
        System.out.println(solution(h1, m1, s1, h2, m2, s2)); // 2852
    }

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int count = getAlarmCount(h2, m2, s2) - getAlarmCount(h1, m1, s1);
        if ((h1 == 0 || h1 == 12) && m1 == 0 && s1 == 0) {
            count++;
        }
        return count;
    }

    private static int getAlarmCount(int h, int m, int s) {
        int second = h * 3600 + m * 60 + s;
        int count = 0;
        count += second * 59  / 3600;  // 초침:분침 | 1 시간 : 시침 59  바퀴
        count += second * 719 / 43200; // 초침:시침 | 1 시간 : 시침 719 바퀴
        if (h >= 12) {
            count--;
        }
        return count;
    }
}
