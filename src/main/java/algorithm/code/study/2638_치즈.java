package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem2638 {

    static int N,M;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static Stack<int[]> road;

    public static void main(String args[]) throws IOException {
        /**
         8 9
         0 0 0 0 0 0 0 0 0
         0 0 0 1 1 0 0 0 0
         0 0 0 1 1 0 1 1 0
         0 0 1 1 1 1 1 1 0
         0 0 1 1 1 1 1 0 0
         0 0 1 1 0 1 1 0 0
         0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0

         6 9
         0 0 0 0 0 0 0 0 0
         0 1 0 0 1 0 0 1 0
         0 1 0 0 0 0 0 1 0
         0 1 0 0 1 0 0 1 0
         0 1 1 1 1 1 1 1 0
         0 0 0 0 0 0 0 0 0
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int time = 0;

        // 5 <= N,M <= 100
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        Queue<int[]> cheese = new LinkedList<>();

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cheese.add(new int[]{i, j, 0});
                }
            }
        }

        while (true) {

            if (cheese.size() == 0 || time >= 1) break;

            //녹을치즈인지 안녹을 치즈인지 확인(안녹:0,녹:1)
            for (int i = 0; i < cheese.size(); i++) {
                int temp[] = cheese.poll();
                cheese.add(new int[]{temp[0], temp[1], isCheck(map, temp)});
            }

            Queue<int[]> tempCheese = new LinkedList<>();

            //치즈 큐 갱신, 맵 갱신
            for (int[] cheesePoint : cheese) {
                if (cheesePoint[2] == 1) map[cheesePoint[0]][cheesePoint[1]] = 0;
                else tempCheese.add(cheesePoint);
            }

            cheese.clear();

            for (int[] tempCheesePoint : tempCheese) {
                cheese.add(tempCheesePoint);
            }

            tempCheese.clear();

//            for(int[] m : map) {
//                System.out.println(Arrays.toString(m));
//            }
            time++;
        }
        System.out.println(time);

    }
    
    //return 1: 녹을치즈 / 0: 안녹을 치즈
    static int isCheck(int[][] map, int[] point) {
        int turn = 0;
        int[] direction = {0,0,0,0}; //우,하,좌,상
        
        //빈공간이 상하좌우 2개 이상
        for (int i = 0; i < 4; i++) {
            int x = point[1] + dx[i];
            int y = point[0] + dy[i];
            if (x < 0 || y < 0 || x >= M || y >= N) {
                turn += 1;
            }
            else if (map[y][x] == 0) {
                turn += 1;
                direction[i] = 1;
            }
        }

        //공기와의 접촉
        for (int i = 0; i < 4; i++) {
            if(turn <= 1) return 0;
            if(direction[i] == 1) {
                road = new Stack<>();
                if (meetCheese(map, point[1], point[0], i)) {
                    while(!road.isEmpty()) {
                        int[] temp = road.peek();
                        if (meetWall(map, temp[1], temp[0], temp[2])) {
                            turn -= 1;
                            break;
                        }
                    }
                }
            }
        }

        return 1;
    }

    static boolean meetCheese(int[][] map, int x, int y, int dir) {
        int moveX = x + dx[dir];
        int moveY = y + dy[dir];

        if (moveX < 0 || moveY < 0 || moveX >= M || moveY >= N) {
            return false;
        }
        else if (map[moveY][moveX] == 1) {
            return true;
        }
        else {
            road.push(new int[]{moveY, moveX, 0});
            road.push(new int[]{moveY, moveX, 1});
            road.push(new int[]{moveY, moveX, 2});
            road.push(new int[]{moveY, moveX, 3});
            return meetCheese(map, moveX, moveY, dir);
        }
    }
    static boolean meetWall(int[][] map, int x, int y, int dir) {
        int moveX = x + dx[dir];
        int moveY = y + dy[dir];

        System.out.println("road (" + moveX + "," + moveY + ")");

        if (moveX < 0 || moveY < 0 || moveX >= M || moveY >= N) {
            System.out.println("벽!");
            return true;
        }
        else if (map[moveY][moveX] == 0) {
            System.out.println("통로!");
            road.push(new int[]{moveY, moveX, 0});
            road.push(new int[]{moveY, moveX, 1});
            road.push(new int[]{moveY, moveX, 2});
            road.push(new int[]{moveY, moveX, 3});
        }
        return false;
    }
}
