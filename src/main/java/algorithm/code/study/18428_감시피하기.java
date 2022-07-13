package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem18428 {

    static int move_1[] = { -1, 1, 0, 0 };
    static int move_2[] = { 0, 0, -1, 1 };

    static ArrayList<int[]> teacherPoint = new ArrayList<>();
    static ArrayList<int[]> emptyPoint = new ArrayList<>();
    static Queue<ArrayList<int[]>> queue = new ArrayDeque<>();

    static int N = 0;

    /**
     * 3개의 장애물로 감시를 피할 수 있으면 YES 못피하면 NO출력
     5
     X S X X T
     T X S X X
     X X X X X
     X T X X X
     X X T X X

     5
     X S S S X
     T X X S X
     X T X S X
     X X T X S
     X X X T X

     5
     X T X T X
     T X S X T
     X S S S X
     T X S X X
     X T X X X

     5
     X X S X X
     X X X X X
     S X T X S
     X X X X X
     X X S X X

     */
    public static void main(String args[]) throws IOException {

        //Teacher:2, Student:1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[][] peopleMap = new int[N][N];

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String str = st.nextToken();
                if (str.equals("T")) {
                    peopleMap[i][j] = 2;
                    teacherPoint.add(new int[]{i,j});
                }
                else if (str.equals("S")) {
                    peopleMap[i][j] = 1;
                }
                else emptyPoint.add(new int[]{i,j});
            }
        }

        combination(emptyPoint, new boolean[emptyPoint.size()], 0, emptyPoint.size(), 3);

        boolean isFind = true;

        int[][] tempMap = peopleMap.clone();

        while (!queue.isEmpty()) {
            ArrayList<int[]> temp = queue.poll();

            int[] wall_1 = temp.get(0);
            int[] wall_2 = temp.get(1);
            int[] wall_3 = temp.get(2);

            tempMap[wall_1[0]][wall_1[1]] = 3;
            tempMap[wall_2[0]][wall_2[1]] = 3;
            tempMap[wall_3[0]][wall_3[1]] = 3;

            isFind = find(tempMap);

            if(!isFind) {
                isFind = false;
                break;
            }

            tempMap[wall_1[0]][wall_1[1]] = 0;
            tempMap[wall_2[0]][wall_2[1]] = 0;
            tempMap[wall_3[0]][wall_3[1]] = 0;

        }

        System.out.println(isFind ? "NO" : "YES");
    }

    static void combination(ArrayList<int[]> arrList, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            save(arrList, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arrList, visited, i+1, n, r-1);
            visited[i] = false;
        }
    }

    static void save(ArrayList<int[]> arrList, boolean[] visited, int n) {
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            if (visited[i]) {
                list.add(new int[]{arrList.get(i)[0], arrList.get(i)[1]});
            }
        }
        queue.add(list);
    }

    static boolean find(int[][] map) {

        for (int i = 0; i < teacherPoint.size(); i ++) {
            for (int j = 0; j < 4; j++) {
                int y = teacherPoint.get(i)[0];
                int x = teacherPoint.get(i)[1];

                while (true) {
                    y += move_1[j];
                    x += move_2[j];

                    if(x < 0 || x >= N || y < 0 || y >= N) break;
                    if(map[y][x] == 3) break;
                    if(map[y][x] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
