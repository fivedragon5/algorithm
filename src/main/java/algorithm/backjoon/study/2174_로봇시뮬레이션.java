package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Probleam2174 {

    static ArrayList<int[]> robots = new ArrayList<>();
    static int a = 0;
    static int b = 0;

    static int move_y[] = { 1, 0, -1, 0 };
    static int move_x[] = { 0, 1, 0, -1 };

    //N:0, E:1, S:2, W:3
    /**
     5 4
     2 2
     1 1 E
     5 4 W
     1 F 7
     2 F 7
     Robot 1 crashes into the wall

     3 3
     1 9
     2 2 W
     1 F 1
     1 L 1
     1 F 1
     1 L 1
     1 F 2
     1 L 5
     1 F 2
     1 R 3
     1 F 2
     OK
    */
    public static void main(String srgs[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken()); // width
        b = Integer.parseInt(st.nextToken()); // height

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // robot count
        int m = Integer.parseInt(st.nextToken()); // command count

        int map[][] = new int[b][a];

        //로봇 위치
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            String direction = st.nextToken();
            int d = 0;

            if (direction.equals("E")) d = 1;
            else if (direction.equals("S")) d = 2;
            else if (direction.equals("W")) d = 3;

            map[y][x] = i+1;
            robots.add(new int[]{x, y, d});
        }
        
        //명령
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNumber = Integer.parseInt(st.nextToken());
            String commandDirection = st.nextToken();
            int repeat = Integer.parseInt(st.nextToken());

            if (commandDirection.equals("F")) {
                int result = commandF(robotNumber, repeat, map);
                if (result == -1) {
                    System.out.println("Robot " + robotNumber + " crashes into the wall");
                    return;
                }
                else if (result > 0) {
                    System.out.println("Robot " + robotNumber + " crashes into robot " + result);
                    return;
                }
            }
            else {
                turnRobot(robotNumber, repeat, commandDirection);
            }
        }

        System.out.println("OK");
    }

    static int commandF(int robotNumber, int repeat, int[][] map) {
        int x = robots.get(robotNumber-1)[0];
        int y = robots.get(robotNumber-1)[1];
        int direction = robots.get(robotNumber-1)[2]%4;
        map[y][x] = 0;

        int dy = move_y[direction];
        int dx = move_x[direction];

        for (int i = 0 ; i < repeat ; i++) {
            x += dx;
            y += dy;
            if (x < 0 || y < 0 || x >= a || y >= b) {
                return -1;
            }
            else if (map[y][x] > 0) {
                return map[y][x];
            }
        }
        map[y][x] = robotNumber-1;
        robots.get(robotNumber-1)[0] = x;
        robots.get(robotNumber-1)[1] = y;
        return 0;
    }

    static void turnRobot(int robotNumber, int repeat, int[][] map, String direction) {
        int dRepeat = repeat % 4;
        int robotDirection = robots.get(robotNumber-1)[2];
        if (direction.equals("R")) {
            robots.get(robotNumber-1)[2] = robotDirection + dRepeat;
        }
        else {
            robots.get(robotNumber-1)[2] = robotDirection + (4-dRepeat);
        }
    }
}
