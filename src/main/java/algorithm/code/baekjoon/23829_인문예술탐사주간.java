package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * TODO : 다시풀기
 *
 * 1 <= N,Q <= 100,000
 * 1 <= 나무 위치, 태영 위치, <= 10,000,000
 */
class Problem23829 {

    static int N;
    static int[] trees;
    static int[] sumArray;
    static int leftTreeCount, rightTreeCount;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        trees = new int[N];
        int[] shots = new int[Q];
        sumArray = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            sum += trees[i];
            sumArray[i] = sum;
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            shots[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        // 찍는 지점이 사이에 있는경우와 나무에 있는경우만 찾아보자.
        // 우측 : 우측시작점, 끝까지의 합 - (우측 나무 수 * 찍는 지점)
        // 좌측 : 찍는 지점 * 좌측 나무 수 - 0 ~ 좌측 시작점 까지의 합

        for (int shot : shots) {
            if (shot < trees[0]) {
                System.out.println(sumArray[N-1] - (shot * N));
            }
            else if (shot > trees[N-1]) {
                System.out.println(shot * N - sumArray[N-1]);
            }
            else {
                checkTreeCount(shot);
                System.out.println(calcPoint(shot));
            }
        }
    }

    static void checkTreeCount(int shot) {
        for (int i = 0; i < N; i++) {
            int tree = trees[i];
            if (shot == tree) {
                leftTreeCount = i;
                rightTreeCount = N - i - 1;
                return;
            }
            else if (shot < tree) {
                leftTreeCount = i;
                rightTreeCount = N-i;
                return;
            }
        }
    }

    static int calcPoint(int shot) {
        int point = 0;
        point += shot * leftTreeCount - sumArray[leftTreeCount-1];
        point += (sumArray[N-1] - sumArray[N-rightTreeCount]) - (rightTreeCount * shot);
        return point;
    }
}

/*
5 2
1 3 7 9 10
4
12

18
30

5 3
2 3 7 9 10
1
2
12

18
30

1 1
3
1

 */

