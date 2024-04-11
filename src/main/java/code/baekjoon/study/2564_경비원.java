package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Problem2564 {

    static int width;
    static int height;

    public static void main(String args[]) throws IOException {
        /**
         * 1: 북     0,~
         * 2: 남     height,~
         * 3: 서     ~,0
         * 4: 동     ~,widht
         *
            1차원 배열로 만들어서 풀어보자.
            북 -> 동 -> 남 -> 서
            direction
            1:value
            4:width + value
            2:width + height + (width-value) -> 2width + height - value
            3:2width + height + height-value -> 2(width+height)-value
         예제)
             10 5
             3
             1 4
             3 2
             2 8
             2 3
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        int distance = 2 * (width + height);

        st = new StringTokenizer(br.readLine());

        int storeCount = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> storeMap = new HashMap<Integer, Integer>();

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < storeCount + 1; i++) {
            storeMap.put(i, distanceCalc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            st = new StringTokenizer(br.readLine());
        }
        int dg =  distanceCalc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int sum = 0;

        for (Integer p : storeMap.values()) {
            int shortDistance = Math.abs(p-dg);
            if (shortDistance > width + height) {
                shortDistance = distance - shortDistance;
            }
            sum += shortDistance;
        }

        System.out.println(sum);
    }

    static int distanceCalc(int direction, int position) {
        if (direction == 1) {
            return position;
        }
        else if (direction == 2) {
            return 2 * width + height - position;
        }
        else if (direction == 3) { //30 28 12
            return (2 * width) + (2 * height) - position;
        }
        else if (direction == 4) {
            return width + position;
        }
        return 0;
    }
}
