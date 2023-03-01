package algorithm.code.programmers.study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 접근)
 *  1. String타입의 시간("[HH:MM]")을 Int형태의 시간으로 변경 
 *      - 여기서는 시간에 60을 곱해 분단위로 통일
 *  2. 체크인 시간을 오름차순으로 정렬
 *  3. 체크아웃 시간을 PriorityQueue에 저장
 *
 * 풀이)
 *  1. 문자열 형태의 시간을 subString으로 잘라 시간, 분 형태로 만든뒤 시간에 60을 곱해
 *     분단위로 통일
 *      - 결과적으로 int형 2차원 배열 {입실, 퇴실}형태의 타입. bookTime[][]
 *      - 이때 퇴실 시간에 10분을 더해 청소시간도 미리 더해준다
 *      - method convertTimeToInt()
 *  2. bookTime을 입실 오름차순으로 정렬
 *  3. 현재 호텔의 손님이 있는 방 중 가장 빠른 퇴실 시간을 알 수 있는 PriorityQueue<Integer> 생성
 *  4. 정렬한 bookTime을 순회하며 가장빠른 퇴실의 방과 입실 시간을 비교하여 입실할 수 있다면
 *     PriorityQueue에서 삭제 처리를 해두고 그방에 입실 이때 PriorityQueue에 기록할것은 현재
 *     입실한 손님의 퇴실 시간을 기록
 *  5. 순회 할 떄마다 PriorityQueue의 size를 확인하여 최대값을 갱신
 *
 * 제한)
 *  1 ≤ book_time.length ≤ 1,000
 */
class Lesson155651 {
    static int solution(String[][] book_time) {
        int maxRoom = 0;
        int[][] bookTime = convertTimeToInt(book_time);
        Arrays.sort(bookTime, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int[] book : bookTime) {
            int startTime = book[0];
            int endTime = book[1];
            if (!queue.isEmpty()) {
                int earlyEndTime = queue.peek();
                if (startTime >= earlyEndTime) {
                    queue.poll();
                }
            }
            queue.add(endTime);
            maxRoom = Math.max(maxRoom, queue.size());
        }
        return maxRoom;
    }

    static int[][] convertTimeToInt(String[][] strArray) {
        int[][] books = new int[strArray.length][2];
        for (int i = 0; i < strArray.length; i++) {
            books[i][0] = Integer.parseInt(strArray[i][0].substring(0,2)) * 60 + Integer.parseInt(strArray[i][0].substring(3,5));
            books[i][1] = Integer.parseInt(strArray[i][1].substring(0,2)) * 60 + Integer.parseInt(strArray[i][1].substring(3,5)) + 10;
        }
        return books;
    }

    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"},{"16:40", "18:20"},{"14:20", "15:20"},{"14:10", "19:20"},{"18:20", "21:20"}};
        System.out.println("===START===");
        System.out.println(solution(book_time));
        System.out.println("===END===");

        String[][] book_time1 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        System.out.println(solution(book_time1));

        String[][] book_time2 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println(solution(book_time2));
    }
}
//
