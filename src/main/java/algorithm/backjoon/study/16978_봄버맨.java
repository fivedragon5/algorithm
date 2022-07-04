package algorithm.backjoon.study;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Problem16918 {

    static int r,c,n;
    static int map[][];
    static int[] i = {1,-1,0,0};
    static int[] j = {0,0,1,-1};

    public static void main(String srgs[]) throws IOException{

        solve();

        System.out.println("");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    bw.write('O');
                }
                else {
                    bw.write('.');
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void solve() throws IOException {
        /**
         6 7 3
         .......
         ...O...
         ....O..
         .......
         OO.....
         OO.....
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();

            for (int j = 0; j < c; j++) {
                if (str.charAt(j) == 'O') {
                    map[i][j] = 3;
                }
                else {
                    map[i][j] = 0;
                }
            }

            if (n == 0) return;

            second();
            --n;

            if (n == 0) return;

            boolean bomb = true;

            for (int s = 0; s < n; s++) {
                if (bomb) {
                    second();
                    setup();
                    explode();
                }
                else {
                    second();
                    explode();
                }
            }
            bomb = !bomb;
        }
    }

    public static void second() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] > 0) {
                    --map[i][j];
                    if(map[i][j] == 0) {
                        map[i][j] = -1;
                    }
                }
            }
        }
    }

    public static void explode() {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] < 0) {
                    map[i][j] = 0;
                    queue.add(new int[]{i,j});
                }
            }
        }

        for (int[] point: queue) {
            explode(point[0], point[1]);
        }
    }

    public static void explode(int x, int y) {
        map[x][y] = 0;
        for (int index = 0; index < j.length; index++) {
            int ey = y + i[index];
            int ex = x + j[index];
            if ((0 <= ey) && (ey < r) && (0 <= ex) && (ex < c)) {
                map[ey][ex] = 0;
            }
        }
    }

    public static void setup() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 3;
                }
            }
        }
    }
}
