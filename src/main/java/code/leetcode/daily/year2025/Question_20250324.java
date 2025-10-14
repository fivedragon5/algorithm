package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-days-without-meetings/description/?envType=daily-question&envId=2025-03-24
 *
 * 제한)
 *  1 <= days <= 10^9
 *  1 <= meetings.length <= 10^5
 *  meetings[i].length == 2
 *  1 <= meetings[i][0] <= meetings[i][1] <= days
 *
 * 문제)
 *  meeting[i] : [start(i),end(i)]
 *  1. 미팅 시작일, 종료일을 나타내는 2차원 배열이 주어진다.
 *  2. 1일부터 days까지 미팅이 없는 날 수를 반환하기
 *
 * 풀이)
 *  1. meetings 를 오름차순 정렬
 *  2. 정렬한 미팅을 반복하면서 주어진 days에서 미팅있는 날짜를 빼준다
 *   - 반복하면서 이전 미팅의 종료일중 가장 미래날짜를 갱신해줌
 *   - 미팅 사이에 텀이 있는 경우, 미팅 사이에 텀이 없는 경우
 *   - 텀이 있는 경우 -1 추가 (중복 뺄셈 방지)
 *
 */

public class Question_20250324 {
    public static void main(String args[]) throws IOException {
        int days4 = 8; int[][] meetings4 = {{3,4},{4,8},{2,5},{3,8}};
        System.out.println(countDays(days4, meetings4));

        int days = 10; int[][] meetings = {{5,7},{1,3},{9,10}};
        System.out.println(countDays(days, meetings));

        int days2 = 5; int[][] meetings2 = {{2,4},{1,3}};
        System.out.println(countDays(days2, meetings2));

        int days3 = 6; int[][] meetings3 = {{1,6}};
        System.out.println(countDays(days3, meetings3));
    }

    public static int countDays(int days, int[][] meetings) {
        // meetings 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int count = days;

        int beforeMeetingStart = 0;
        int beforeMeetingEnd = 0;

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            if (beforeMeetingStart > end) {
                continue;
            }

            // 미팅 사이에 텀이 있는 경우
            if (start > beforeMeetingEnd) {
                beforeMeetingStart = start;
                beforeMeetingEnd = end;
                count -= end - start + 1;
            }
            // 미팅 사이에 텀이 없는 경우
            else if (end > beforeMeetingEnd) {
                beforeMeetingStart = Math.max(beforeMeetingEnd, start);
                beforeMeetingEnd = end;
                count -= beforeMeetingEnd - beforeMeetingStart;
            }
        }
        return count;
    }
}
