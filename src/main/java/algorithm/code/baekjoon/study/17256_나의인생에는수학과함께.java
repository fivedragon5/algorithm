package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem17256 {

    /**
     *  접근)
     *  (1,1)에서 (N,N)까지 최단거리로 가면서 계산후 (N,N)에 도착했을경우 max, min값을 최신화 시켜준다.
     *  1. 숫자, 연산기호를 저장할 String이차원배열을 선언 후 저장 [y][x]
     *  2. (0,0) 부터 최단거리로 가기위해
     *
     *  풀이)
     *
     *
     * 문제 조건)
     * 첫 번째 줄에는 지도의 크기 N이 주어진다. (3 ≤ N ≤ 5, N은 홀수)
     * 그 다음 N 줄에는 N개의 숫자 또는 연산자가 빈칸을 사이에 두고 주어지며, 세현이네 집 (1, 1)과 학교 (N, N)는 반드시 숫자로 주어진다.
     * 그리고 숫자 다음에는 연산자, 연산자 다음에는 숫자가 나오도록 주어진다.
     * 주어지는 숫자는 0이상 5이하의 정수, 연산자는 (‘+’, ‘-’, ‘*’) 만 주어진다.
     * 최대값, 최소값
     */

    static int N;
    static String map[][];
    static int max = -Integer.MAX_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new String[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }
        //시작
        moveRoad(0, 0, Integer.parseInt(map[0][0]), true);

        System.out.println(max + " " + min);
    }

    static void moveRoad(int x, int y, int sum, boolean isNumber) {

        for (int i = 0; i < 2; i++) {
            if (x+1 < N) {
                int rightSum = sum;
                if(!isNumber) {
                    rightSum = calc(sum, Integer.parseInt(map[y][x+1]), map[y][x]);
                }
                moveRoad(x + 1, y, rightSum, !isNumber);
            }
            if (y+1 < N) {
                int downSum = sum;
                if(!isNumber) {
                    downSum = calc(sum, Integer.parseInt(map[y+1][x]), map[y][x]);
                }
                moveRoad(x, y + 1, downSum, !isNumber);
            }

            if (x == N-1 && y == N-1) {
                max = Math.max(max, sum);
                min = Math.min(min, sum);
                return;
            }
        }
    }

    static int calc(int sum, int number, String operation) {
        if ("+".equals(operation)) {
            return sum + number;
        }
        else if ("-".equals(operation)) {
            return sum - number;
        }
        else {
            return sum * number;
        }
    }
}

/**
 5
 5 + 5 - 3
 * 3 - 1 -
 4 + 5 + 2
 - 2 * 3 -
 1 * 5 + 2

 127 3
*/