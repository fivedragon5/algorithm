package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem19598 {

    public static void main(String args[]) throws IOException {
        /**
         4
         10 30
         0 40
         15 30
         5 10

         3
         5 20
         0 10
         15 25

         7
         0 1
         0 5
         10 11
         10 90
         6 10
         5 7
         1 6
         */

        // 1 <= N <= 1000000
        // 0 <= time <= N^31-1 보다 작음

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] meetingList = new int[N][2];

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetingList[i][0] = start;
            meetingList[i][1] = end;
        }


        // 오름차순 정렬
        Arrays.sort(meetingList, (m1, m2) -> {
            if(m1[0] == m2[0]) {
                return m1[1] - m2[1];
            }
            else {
                return m1[0] - m2[0];
            }
        });
        
        //최초풀이
        //solve(meetingList);
        
        //우선순위 큐 사용
        solve2(meetingList);

    }

    public static void solve2(int[][] meetingList) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int room = 0;

        queue.add(meetingList[0][1]);

        for (int i = 1; i < meetingList.length; i++) {
            while (!queue.isEmpty() && queue.peek() <= meetingList[i][0]) {
                queue.poll();
            }
            queue.add(meetingList[i][1]);
            room = queue.size() > room ? queue.size() : room;
        }

        System.out.println(room);
    }

    public static void solve(int[][] meetingList) {
        ArrayList<Integer> meetingRooms = new ArrayList<>();

        for (int i = 0; i < meetingList.length; i++) {
            int meetingRoomsNumber = checkMeetingRoom(meetingList[i], meetingRooms);
            if(meetingRoomsNumber > -1) {
                meetingRooms.set(meetingRoomsNumber, meetingList[i][1]);
            }
            else {
                meetingRooms.add(meetingList[i][1]);
            }
        }
        System.out.println(meetingRooms.size());
    }

    public static int checkMeetingRoom(int[] meeting, ArrayList<Integer> meetingRooms) {
        for (int i = 0; i < meetingRooms.size(); i++) {
            if (meeting[0] >= meetingRooms.get(i)) {
                return i;
            }
        }
        return -1;
    }
}
