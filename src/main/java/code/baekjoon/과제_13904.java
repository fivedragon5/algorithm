package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13904
 * 제한)
 * 1 <= N <= 1,000
 * 1 <= d <= 1,000
 * 1 <= w <= 100
 * 문제)
 * 1. 하루에 하나의 과제만 할 수 있음
 * 2. 마감일이 지난 과제는 점수를 받을 수 없음
 * 3. 가장 점수를 많이 받을 수 있도록 과제를 구성한 최대 점수 구하기
 * 풀이)
 * 1. 과제 점수 내림차순
 *  - 과제 마감일 오름차순
 * 2. 과제리스트를 반복돌면서 과제 마감일에 과제를 끝내도록 캘박
 * 3. 캘박해둔 날이 겹칠경우 마감기한일 전날에 배정하도록 로직구성
 */
class Problem13904 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 과제 수
        List<int[]> tasks = new ArrayList<>();
        int lastDay = 0;
        // d: 마감 기한, w: 과제 점수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tasks.add(new int[]{d, w});
            lastDay = Math.max(lastDay, d);
        }

        Collections.sort(tasks, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int point = 0;
        boolean[] calender = new boolean[lastDay];

        for (int i = 0; i < N; i ++) {
            int[] task = tasks.get(i);
            point += checkCalender(task, calender, task[0]);
        }

        System.out.println(point);
    }

    private static int checkCalender(int[] task, boolean[] calender, int day) {
        if (day < 1) {
            return 0;
        }
        if (calender[day - 1]) {
            return checkCalender(task, calender, day-1);
        } else {
            calender[day - 1] = true;
            return task[1];

        }
    }
}
/*
7
4 60
4 40
1 20
2 50
3 30
4 10
6 5

4 60
2 50
4 40
3 30
1 20
4 10
6 5
 */
