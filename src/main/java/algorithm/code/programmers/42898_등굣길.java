package algorithm.code.programmers;

import java.util.Arrays;

/**
 * 격자의 크기 m, n은 1 이상 100 이하인 자연수입니다.
 * m과 n이 모두 1인 경우는 입력으로 주어지지 않습니다.
 * 물에 잠긴 지역은 0개 이상 10개 이하입니다.
 * 집과 학교가 물에 잠긴 경우는 입력으로 주어지지 않습니다.
 * 
 * 집 : (1,1), 학교 : (m,n) 
 *
 * 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를
 * 1,000,000,007로 나눈 나머지를 return
 * 하도록 solution 함수를 작성해주세요.
 */
class Lesson42898 {

    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static int M = 0;
    static int N = 0;
    static int[][] PUDDLES;
    //static long answer = 0;

//    static int solution(int m, int n, int[][] puddles) {
//
//        M = m;
//        N = n;
//        PUDDLES = puddles;
//
//        check(1,1);
//
//        return (int) answer % 1000000007;
//    }
//
//    static void check(int x, int y) {
//        for (int i = 0; i < PUDDLES.length; i++) {
//            if (x == PUDDLES[i][0] && y == PUDDLES[i][1]) return;
//        }
//        if (x == M && y == N) {
//            answer++;
//            if (answer > 1000000007) {
//                answer %= 1000000007;
//            }
//            return;
//        }
//        if (x+1 <= M) {
//            int rightX = x + dx[0];
//            int rightY = y + dy[0];
//            check(rightX, rightY);
//        }
//        if (y+1 <= N) {
//            int downX = x + dx[1];
//            int downY = y + dy[1];
//            check(downX, downY);
//        }
//        return;
//    }

    static int solution2(int m, int n, int[][] puddles) {
        M = m;
        N = n;
        PUDDLES = puddles;

        //m = x, n = y
        int[][] map = new int[n][m];

        map[0][0] = 1;

        for (int i = 0; i < PUDDLES.length; i++) {
            //DP로 접근 해보자.
            map[puddles[i][1]-1][puddles[i][0]-1] = -1;
        }

        for (int i = 1; i < m; i++) {
             int point = map[0][i];
             if (point != -1 && map[0][i-1] > 0) {
                 map[0][i] = 1;
             }
        }

        for (int i = 1; i < n; i++) {
            int point = map[i][0];
            if (point != -1 && map[i-1][0] > 0) {
                map[i][0] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int point = map[i][j];
                if (point == -1) continue;
                else {
                    int left = map[i][j-1] == -1 ? 0 : map[i][j-1];
                    int up = map[i-1][j] == -1 ? 0 : map[i-1][j];
                    int sum = left + up >= 1000000007 ? (left+up)%1000000007 : left + up;
                    map[i][j] = sum;
                }
            }
        }

//        for (int[] c : map) {
//            System.out.println(Arrays.toString(c));
//        }

        return map[n-1][m-1];
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}}; // 행렬 반대
        //answer : 4
        System.out.println("===START===");
        //시간초과 탐색
        //System.out.println(solution(m, n, puddles));
        System.out.println(solution2(m, n, puddles));
        System.out.println("===END===");
    }



//    @Test
//    public void 정답(){
//        Assert.assertEquals(4, solution(4, 3, new int[][]{{2,2}}));
//        Assert.assertEquals(7, solution(4, 4, new int[][]{{3,2}, {2,4}}));
//        Assert.assertEquals(7, solution(5, 3, new int[][]{{4,2}}));
//        Assert.assertEquals(0, solution(2, 2, new int[][]{{2,1}, {1, 2}}));
//        Assert.assertEquals(0, solution(3, 1, new int[][]{{2,1}}));
//    }

}
