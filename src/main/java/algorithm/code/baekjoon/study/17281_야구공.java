package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem17281 {

    /**
     * 풀이)
     * 타자를 배정할 수 있는 모든 경우의 수로 게임을 진행시켜 점수를 계산한 뒤 가장 높은 점수를 출력
     * 1. * 1번 선수는 4번타자에 고정 *
     * 2. 순열알고리즘을 통해 타자를 배정할 수 있는 경우의 수를 구함
     * 3. 9명의 타자가 전부 배정 되는 시점에 게임을 진행시켜 나오는 점수로 최대값 갱신
     *
     * 설명)
     * int[N][9] hitters : 이닝마다 타자들의 타격? 정보
     * permutation(int cnt) : 타자 순서 조합
     * playBaseball(int[] currentGameHitterOrder) : 게임 진행
     * 
     * 문제 조건)
     * 2 <= N <= 50
     * 1:안타, 2:2루타, 3:3루타, 4:홈런, 0:아웃
     * 1번 선수는 4번타자 고정
     * *** 한번 정한 타순은 게임이 시작되면 변경 할 수 없음. ***
     */

    static int[][] hitters;
    static boolean[] selected = new boolean[9];
    static int[] currentGameHitterOrder = new int[9];
    static int totalInnings = 0;
    static int maxPoint = 0;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        hitters = new int[N][9];
        totalInnings = N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9 ; j++) {
                hitters[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //4번타자는 1번 선수로 고정한다.
        selected[3] = true;
        currentGameHitterOrder[3] = 0;

        permutation(1);

        System.out.println(maxPoint);
    }


    // 타순 정하기
    private static void permutation(int cnt) {

        if(cnt == 9) {
            // 순서가 정해지면 게임 시작
            maxPoint = Math.max(maxPoint, playBaseball(currentGameHitterOrder));
            return;
        }

        for(int i = 0; i < 9; i++) {
            if(!selected[i]) {
                selected[i] = true;
                currentGameHitterOrder[i] = cnt;
                permutation(cnt+1);
                selected[i] = false;
            }
        }
    }

    static int playBaseball(int[] currentGameHitterOrder) {

        int point = 0;
        boolean[] bases = new boolean[3]; //1,2,3 루
        int currentHitter = 0;

        for (int i = 0; i < totalInnings; i++) {

            int outCount = 0; // 아웃카운트 초기화
            bases[0] = bases[1] = bases[2] = false; // 베이스 초기화

            while (outCount < 3) {
                int swing = hitters[i][currentGameHitterOrder[currentHitter]];
                currentHitter++;
                currentHitter%=9;

                if (swing == 0) {
                    outCount++;
                }

                else if (swing == 4) {
                    point++;
                    for (boolean base : bases) {
                        if (base) {
                            point++;
                        }
                    }
                    bases[0] = bases[1] = bases[2] = false;
                }

                else if (swing == 3) {
                    for (boolean base : bases) {
                        if(base) {
                            point++;
                        }
                    }
                    bases[0] = bases[1] = bases[2] = false;
                    bases[2] = true;
                }

                else if (swing == 2) {
                    if (bases[2]) {
                        point++;
                        bases[2] = false;
                    }
                    if (bases[1]) {
                        point++;
                        bases[1] = false;
                    }
                    if (bases[0]) {
                        bases[0] = false;
                        bases[2] = true;
                    }
                    bases[1] = true;
                }

                else {
                    if (bases[2]) {
                        point++;
                        bases[2] = false;
                    }
                    if (bases[1]) {
                        bases[1] = false;
                        bases[2] = true;
                    }
                    if (bases[0]) {
                        bases[0] = false;
                        bases[1] = true;
                    }
                    bases[0] = true;
                }
            }
        }
        return point;
    }
}

/**
 2
 4 0 0 0 0 0 0 0 0
 4 0 0 0 0 0 0 0 0

 1

 2
 4 0 0 0 1 1 1 0 0
 0 0 0 0 0 0 0 0 0

 4

 2
 0 4 4 4 4 4 4 4 4
 0 4 4 4 4 4 4 4 4

 43

 9
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0
 4 4 4 4 4 4 4 4 0

 216
*/