package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 접근)
 * 석순과 종류석을 정렬
 * 석순과 종류석을 탐색할때 시간을 단축시키기위해 시작위치를 기억해서 시간을 줄여준다.
 *
 * 제한)
 * N%2 == 0
 * 2 <= N <= 200,000
 * 2 <= H <= 500,000
 */
class Problem3020 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[N/2];
        int[] up = new int[N/2];
        int height = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                down[i/2] = height;
            }
            else {
                up[i/2] = H - height + 1;
            }
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int startDown = 0;
        int startUp = 0;
        int destoryCount = 0;

        int nextDown = 0;
        int nextUp = 0;
        int min = 200000;
        int minCount = 0;

        //1구간의 장애물이 파괴한 개수
        //석순의 길이가 1이랑 같거나 큰 개수 + 전체길이 - 종유석 높이 + 1의 길이가 1보다 큰 개수
        for (int i = 0; i < H; i++) {
            for (int j = nextDown; j < N/2; j++) {
                if (down[j] >= i+1) {
                    nextDown = j;
                    break;
                }
                else {
                    nextDown++;
                }
            }
            destoryCount = N/2 - nextDown;

            for (int j = nextUp; j < N/2; j++) {
                if (up[j] > i+1) {
                    break;
                }
                else {
                    nextUp++;
                }
            }
            destoryCount += nextUp;

            if (min == destoryCount) {
                minCount++;
            }
            else if (min > destoryCount) {
                min = destoryCount;
                minCount = 1;
            }
        }

        System.out.println(min + " " + minCount);
    }
//    public static void main(String args[]) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int H = Integer.parseInt(st.nextToken());
//
//        int[] crack = new int[H];
//        int height = 0;
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            height = Integer.parseInt(st.nextToken());
//            if (i % 2 == 0) {
//                for (int j = 0; j < height; j++) {
//                    crack[j]++;
//                }
//            }
//            else {
//                for (int j = H - 1; j >= H - height; j--) {
//                    crack[j]++;
//                }
//            }
//        }
//        Arrays.sort(crack);
//        int min = crack[0];
//        int count = 0;
//        for (int wall : crack) {
//            if (min != wall) {
//                break;
//            }
//            count++;
//        }
//        System.out.println(min + " " + count);
//    }
}
/*
14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3

6 7
1
5
3
3
5
1

2 3

14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3

7 2
 */

