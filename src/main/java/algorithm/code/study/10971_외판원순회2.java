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
    static int[][] cities; //각 도시를 이동하는대에 필요한 비용
    static int[][] dp; //[a][b] a:도시, b:방문 확인 : 방문하지 않은 도시
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
            Arrays.fill(dp[i], -1);
        }

        System.out.println(playTrip(0, 1));

        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
    }

    static int playTrip(int city, int bitMask) {

        if (bitMask == (1 << N) - 1) {
            if (cities[city][0] == 0) {
                return INF;
            }
            return cities[city][0];
        }

        if (dp[city][bitMask] != -1) { // 이부분을 dp[city][bitMask] != INF 에서 -1로 변경
            return dp[city][bitMask];
        }

        dp[city][bitMask] = INF; // 최대값으로 초기화

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
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1
0 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1
0 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1
0 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
0 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1
0 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1
0 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1
0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0

16
 */

