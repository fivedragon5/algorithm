package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1 ≤ Lx, Ly ≤ 1,000
 * 1 ≤ N ≤ 20,000
 * 1 ≤ lx
 * 0 ≤ px
 * px+lx ≤ Lx
 * 1 ≤ ly
 * 0 ≤ py
 * py+ly ≤ Ly
 * 1 ≤ lz ≤ 100,000
 */
class Problem1905 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Lx = Integer.parseInt(st.nextToken());
        int Ly = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] boxes = new int[N][5];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            boxes[i][0] = Integer.parseInt(st.nextToken());
            boxes[i][1] = Integer.parseInt(st.nextToken());
            boxes[i][2] = Integer.parseInt(st.nextToken());
            boxes[i][3] = Integer.parseInt(st.nextToken());
            boxes[i][4] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Box> boxList = new ArrayList<>();
        boxList.add(new Box(boxes[0][0], boxes[0][1], boxes[0][3], boxes[0][4], boxes[0][2]));

        for (int i = 1; i < 1; i++) {
            Box newBox = new Box(boxes[i][0], boxes[i][1], boxes[i][3], boxes[i][4], boxes[i][2]);
            boolean isAdd = false;
            for (int j = 0; j < boxList.size(); j++) {
                Box box = boxList.get(j);
                if (check(box, newBox)) {
                    newBox.currentHeight += box.currentHeight;
                    isAdd = true;
                    for (int k = 0; k <= j; k++) {
                        if (newBox.currentHeight > boxList.get(k).currentHeight) {
                            boxList.add(k, newBox);
                            break;
                        }
                    }
                    break;
                }
            }
            if (!isAdd) {
                boxList.add(newBox);
            }
        }
        System.out.println(boxList.get(0).currentHeight);
    }

    static boolean check(Box one, Box two) {
        boolean flag = false;
        for (int y = two.dy + 1; y <= two.dy + two.y - 1; y++) {
            if (y >= one.dy && y <= one.dy + y) {
                flag = true;
                break;
            }
        }

        if(!flag) return false;

        for (int x = two.dx + 1; x <= two.dx + two.x - 1; x++) {
            if (x >= one.dx && x <= one.dx + x) {
                return true;
            }
        }

        return false;
    }

    static class Box {
        int x;
        int y;
        int dx;
        int dy;
        int currentHeight;

        public Box(int x, int y, int dx, int dy, int currentHeight) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.currentHeight = currentHeight;
        }
    }
}
/*
7 5 4
4 3 2 0 0
3 3 1 3 0
7 1 2 0 3
2 3 3 2 2
*/

//    public static void main(String args[]) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int Lx = Integer.parseInt(st.nextToken());
//        int Ly = Integer.parseInt(st.nextToken());
//        int N = Integer.parseInt(st.nextToken());
//
//        int[][] layer = new int[Ly+1][Lx+1];
//
//        int answer = 0;
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            int z = Integer.parseInt(st.nextToken());
//            int dx = Integer.parseInt(st.nextToken()) + 1;
//            int dy = Integer.parseInt(st.nextToken()) + 1;
//
//            int max = 0;
//
//            for(int k = dy; k < dy + y; k++) {
//                for (int j = dx; j < dx + x; j++) {
//                    max = Math.max(max, layer[k][j] + z);
//                }
//            }
//            for(int k = dy; k < dy + y; k++) {
//                for (int j = dx; j < dx + x; j++) {
//                    layer[k][j] = max;
//                }
//            }
//            answer = Math.max(answer, max);
//        }
//        System.out.println(answer);
//    }
//}

