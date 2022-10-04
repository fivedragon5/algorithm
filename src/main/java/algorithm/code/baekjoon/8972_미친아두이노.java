package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem8972 {

    static int[] move_x = {0, -1, 0, 1, -1, 0, 1, -1,  0, 1};
    static int[] move_y = {0,  1, 1, 1,  0, 0, 0, -1, -1, -1};

    static int[] jongSu;
    static ArrayList<int[]> crazyArduino = new ArrayList<>();

    /**
     4 5
     I....
     .....
     .R.R.
     .....
     6
     */
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()); //X
        int C = Integer.parseInt(st.nextToken()); //Y

        Map<String, Integer> arduinoList = new HashMap<>();
        Character[][] board = new Character[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String[] array = st.nextToken().split("");
            for (int j = 0; j < C; j++) {
                board[i][j] = '.'; //보드 채워넣기
                if ("I".equals(array[j])) {
                    jongSu = new int[]{i, j};
                }
                else if ("R".equals(array[j])) {
                    //이동 완료 확인
                    crazyArduino.add(new int[]{i,j,0});
                    arduinoList.put(i+","+j, 0);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        String move = st.nextToken();

        for (int i = 0; i < move.length(); i++) {
            int direction = Character.getNumericValue(move.charAt(i));

            //종수 이동
            if (moveJongSu(direction, arduinoList)) {
                System.out.println("kraj " + (i+1)); //폭파
                return;
            }
            
            //미친 아두이노 이동
            if (moveCrazyArduino(arduinoList)) {
                System.out.println("kraj " + (i+1)); //폭파
                return;
            }
        }

        board[jongSu[0]][jongSu[1]] = 'I';

        for (String key : arduinoList.keySet()) {
            String[] point = key.split(",");
            board[Integer.parseInt(point[0])][Integer.parseInt(point[1])] = 'R';
        }

        for (Character[] chList : board) {
            for(Character ch : chList) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }
    public static boolean moveJongSu(int direction, Map<String, Integer> arduinoMap) {
        int x = jongSu[1] + move_x[direction];
        int y = jongSu[0] + move_y[direction];

        int check = arduinoMap.getOrDefault(y+","+x, -1);

        if (check >= 0) {
            return true;
        }

        jongSu[0] = y;
        jongSu[1] = x;
        return false;
    }

    public static boolean moveCrazyArduino(Map<String, Integer> arduinoMap) {

        Map<String, Integer> newArduinoList = new HashMap<>();

        // 0,0 <-- key : 0,0 value : 0

        for (String key : arduinoMap.keySet()) {
            String[] keyPoint = key.split(",");
            int arduino_x = Integer.parseInt(keyPoint[1]);
            int arduino_y = Integer.parseInt(keyPoint[0]);
            int direction = arduinoCal(arduino_x, arduino_y);

            if(direction == 0) return true;

            int moved_arduino_x = arduino_x + move_x[direction];
            int moved_arduino_y = arduino_y + move_y[direction];
            
            int check = newArduinoList.getOrDefault(moved_arduino_y+","+moved_arduino_x, 1);

            if (check == 1) {
                //빈칸 으로 이동
                newArduinoList.put(moved_arduino_y+","+moved_arduino_x, 2);
            }
            else {
                //아두이노 끼리 뭉침
                newArduinoList.put(moved_arduino_y+","+moved_arduino_x, 0);
            }
        }

        arduinoMap.clear();

        for (String key : newArduinoList.keySet()) {
            if (newArduinoList.get(key) > 0) {
                arduinoMap.put(key, 0);
            }
        }
        return false;
    }
    
    //아두이노 방향 계산
    public static int arduinoCal(int arduino_x, int arduino_y) {
        int jongSu_x = jongSu[1];
        int jongSu_y = jongSu[0];

        int min = 200;
        int minDirection = 0;

        for (int i = 1; i <= 9; i++ ) {
            int x = arduino_x + move_x[i];
            int y = arduino_y + move_y[i];
            int distance = distance(jongSu_x, jongSu_y, x, y);
            if (min >= distance) {
                min = distance;
                minDirection = i;
            }

            if(min == 0) return 0;

        }
        return minDirection;
    }

    //거리 계산
    public static int distance(int r1, int s1, int r2, int s2) {
        return Math.abs(r1-r2) + Math.abs(s2-s1);
    }
}
