package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP, BitMask 방식
 *
 * 1 ~ N 도시
 * 어느 한 도시에서 출발해 N개의 도시를 거쳐 다시 원래출발한 도시로 돌아오는 순회 여행 경로 계획
 * 단 한번갔던 도시로는 다시 갈 수 없다.
 *
 * 위 조건을 만족하는 가장 적은 비용을 들이는 순회 여행 경로 프로그램을 작성
 *
 * 2 <= N <= 10
 * 각 행렬의 성분은 1,000,000 이하 양의정수
 * 갈수없는 경우에는 0
 */

class Problem10971 {

    static int N; //도시의 수
    static int[][] cities; //
    static int[][] dp; //[a][b] a:도시, b:방문 확인
    static final int INF = 200000000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        cities = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(playTrip(0, 1));
    }

    static int playTrip(int city, int bitMask) {
        if (bitMask == (1 << N) - 1) {
            if (cities[city][0] == 0) {
                return INF;
            }
            return cities[city][0];
        }

        if (dp[city][bitMask] != INF) {
            return dp[city][bitMask];
        }

        for (int i = 0; i < N; i++) {
            if (cities[city][i] != 0 && (bitMask & (1 << i)) == 0) {
                dp[city][bitMask] = Math.min(dp[city][bitMask], playTrip(i, bitMask | (1 << i)) + cities[city][i]);
            }
        }

        return dp[city][bitMask];
    }
}

/*
4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

35

4
0 1 2 3
2 0 3 0
3 0 0 0
1 2 3 0

11

4
0 1 100 100
0 0 3 0
0 200 0 400
1 1 1 0

405

16
0 32769 32770 32771 32772 32773 32774 32775 32776 32777 32778 32779 32780 32781 32782 32783
1 0 16386 16387 16388 16389 16390 16391 16392 16393 16394 16395 16396 16397 16398 16399
1 1 0 8195 8196 8197 8198 8199 8200 8201 8202 8203 8204 8205 8206 8207
1 1 1 0 4100 4101 4102 4103 4104 4105 4106 4107 4108 4109 4110 4111
1 1 1 1 0 2053 2054 2055 2056 2057 2058 2059 2060 2061 2062 2063
1 1 1 1 1 0 1030 1031 1032 1033 1034 1035 1036 1037 1038 1039
1 1 1 1 1 1 0 519 520 521 522 523 524 525 526 527
1 1 1 1 1 1 1 0 264 265 266 267 268 269 270 271
1 1 1 1 1 1 1 1 0 137 138 139 140 141 142 143
1 1 1 1 1 1 1 1 1 0 74 75 76 77 78 79
1 1 1 1 1 1 1 1 1 1 0 43 44 45 46 47
1 1 1 1 1 1 1 1 1 1 1 0 28 29 30 31
1 1 1 1 1 1 1 1 1 1 1 1 0 21 22 23
1 1 1 1 1 1 1 1 1 1 1 1 1 0 18 19
1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 17
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0

32798

4
0 7 3 3
7 0 9 2
1 9 0 12
7 7 12 0

20
 */

