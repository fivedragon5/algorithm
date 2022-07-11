package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Problem8972 {

    static int[] move_x = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] move_y = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};

    static int[] jongSu;
    static ArrayList<int[]> crazyArduino = new ArrayList<>();

    /**
     4 5
     I....
     .....
     .R.R.
     .....
     6

     .I...
     .RR..
     .....
     .....
     */
    public static void main(String srgs[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()); //X
        int C = Integer.parseInt(st.nextToken()); //Y

        int[][] board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String[] array = st.nextToken().split("");
            for (int j = 0; j < C; j++) {
                if ("I".equals(array[j])) {
                    jongSu = new int[]{i,j};
                    board[i][j] = -1;
                }
                else if ("R".equals(array[j])) {
                    //이동 완료 확인
                    crazyArduino.add(new int[]{i,j,0});
                    board[i][j] = 2;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        String move = st.nextToken();

        for (int i = 0; i < move.length(); i++) {
            int direction = Character.getNumericValue(move.charAt(i));

            if (moveJongSu(board, direction)) {
                System.out.println("kraj 11"); //폭파
            }
            
            //미친 아두이노 이동

            //종수랑 만났을경우 게임 종료
            
            //아두이노2대 이상일 경우 폭파
            
            for (int[] c : board) {
                for (int d : c) {
                    System.out.print(d);
                }
                System.out.println("");
            }
            System.out.println(i + "-------------------------");
        }
    }
    public static boolean moveJongSu(int[][] board, int direction) {
        board[jongSu[0]][jongSu[1]] = 0;
        int x = jongSu[1] + move_x[direction];
        int y = jongSu[0] + move_y[direction];

        if (board[y][x] > 0) {
            return false;
        }

        board[y][x] = -1;
        jongSu[0] = y;
        jongSu[1] = x;
        return true;
    }

    public static boolean moveCrazyArduino(int[][] board) {
        int jongSu_x = jongSu[1];
        int jongSu_y = jongSu[0];
        return true;
    }
}
