package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * TODO: 2022.11.22 다시풀기
 *
 * 조건)
 * 1 <= N <= 50
 * 1 <= Height <= 1,000,000,000 (10억)
 */
class Problem1027 {

    static int N;
    static long[] bulidingHeights;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        bulidingHeights = new long[N];

        for (int i = 0; i < N; i++) {
            bulidingHeights[i] = Long.parseLong(st.nextToken());
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            answer = Math.max(check(i), answer);
        }

        System.out.println(answer);
    }
    
    //현재 빌딩에서 좌,우를 봣을때 보이는 빌딩의 수
    static int check(int viewPositionIndex) {
        long currentBulidingHeight = bulidingHeights[viewPositionIndex];
        return leftView(viewPositionIndex, currentBulidingHeight) + rightView(viewPositionIndex, currentBulidingHeight);
    }
    /* 기울기 = y2 - y1 / x2 - x1 */

    //현재 빌딩에서 왼쪽을 봤을때 보이는 빌딩의 수
    static int leftView(int index, long height) {
        int count = 0;
        long currentGradient_x = 0;
        long currentGradient_y = 0;
        long minGradient_x = 1;
        long minGradient_y = -1000000001;

        //Max 기울기 갱신, Max 기울기보다 크면 카운트 증가
        for (int i = index - 1; i >= 0; i--) {

            currentGradient_y = bulidingHeights[i] - height;
            currentGradient_x = i - index;

            if (minGradient_y * currentGradient_x > minGradient_x * currentGradient_y) {
                count++;
                minGradient_y = currentGradient_y;
                minGradient_x = currentGradient_x;
            }

        }
        return count;
    }

    //현재 빌딩에서 오른쪽을 봤을때 보이는 빌딩의 수
    static int rightView(int index, long height) {
        int count = 0;
        long currentGradient_x = 0;
        long currentGradient_y = 0;
        long maxGradient_x = 1;
        long maxGradient_y = -1000000001;

        for (int i = index + 1; i < N; i++) {

            currentGradient_y = bulidingHeights[i] - height;
            currentGradient_x = i - index;

            if (maxGradient_y * currentGradient_x < maxGradient_x * currentGradient_y) {
                count++;
                maxGradient_y = currentGradient_y;
                maxGradient_x = currentGradient_x;
            }
        }
        return count;
    }
}
/*
4
2 2 2 2
 */
