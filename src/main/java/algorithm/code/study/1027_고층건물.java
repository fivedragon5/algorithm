package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조건)
 * 1 <= N <= 50
 * 1 <= Height <= 1,000,000,000 (10억)
 */
class Problem1027 {

    static int BULIDING_COUNT;
    static long[] bulidingHeights;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BULIDING_COUNT = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        bulidingHeights = new long[BULIDING_COUNT];

        for (int i = 0; i < BULIDING_COUNT; i++) {
            bulidingHeights[i] = Long.parseLong(st.nextToken());
        }

        int answer = 0;

        for (int i = 0; i < BULIDING_COUNT; i++) {
            answer = Math.max(check(i, bulidingHeights[i]), answer);
        }

        System.out.println(answer);
    }

    static int check(int index, double height) {

        double maxGradient = -1000000000L;
        int rightCount = 0;
        for (int i = index+1; i < BULIDING_COUNT; i++) {
            long currentheight = bulidingHeights[i];
            double gradient = (currentheight - height) / (i - index);
            if (maxGradient < gradient) {
                rightCount++;
                maxGradient = gradient;
            }
        }

        double minGradient = 100000000L;
        int leftCount = 0;
        for (int i = index-1;  i >= 0; i--) {
            long currentheight = bulidingHeights[i];
            double gradient = (height - currentheight) / (index - i);
            if (minGradient > gradient) {
                leftCount++;
                minGradient = gradient;
            }
        }
        return rightCount + leftCount;
    }
}
/*
4
2 2 2 2
 */
