package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1 ≤ N ≤ 1,000,000,000
 * 1 ≤ M ≤ 100,000
 * 2 ≤ 열 정보의 길이 ≤ 20
 *
 * 1번 열에 물이 한 칸 이상 있다.
 * M번 열에 물이 한 칸 이상 있다.
 * 어떤(적어도 하나) 1번 열의 물에서 출발하면 위 · 아래 · 오른쪽으로 인접한 물로 0번 이상 이동해 M번 열의 물에 도달할 수 있다.
 */

class Problem25712 {

    static int N, M;
    static boolean[][] map;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 행 갯수
        N = Integer.parseInt(st.nextToken());
        // 열 갯수
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        for (int i = 0; i < M; i ++) {
            String[] splitStr = br.readLine().split(" ");
            for (int j = 1; j < splitStr.length; j+=2) {
                int start = Integer.parseInt(splitStr[j]);
                int end = Integer.parseInt(splitStr[j+1]);
                for (int k = start-1; k < end; k++) {
                    if (i == 0) startList.add(k);
                    else if (i == M-1) endList.add(k);
                    map[k][i] = true;
                }
            }
        }
        
        // * 1, M번 열에 물이 한 칸 이상 있다. 조건 확인
        if (startList.size() < 1 || endList.size() < 1) {
            System.out.println("NO");
            return;
        }

        for (int start : startList) {
            List<Integer> temp = new ArrayList<>();
            temp.add(start);
            checkPossibleRiver(temp, 0);
        }

        for (boolean[] m : map) {
            System.out.println(Arrays.toString(m));
        }
    }


    //시작점 끝점 도달가능?
    static void checkPossibleRiver(List<Integer> list, int step) {
        // 마지막 줄 일 경우 종료
        if (step >= M-1) {
            System.out.println("종료 및 성공");
        }

        Set<Integer> set = new HashSet<>();

        //1. 오른쪽에 물이 있는지 확인
        for (int start : list) {
            if (map[start][step+1]) {
                //set에 추가하고 위아래 탐색 시작
                set.add(start);
                isCheckWater(step+1, start);
            }
        }
        //2. 있을경우 위아래 탐색 (우측에 강이 있는 좌표만 탐색)
        // - list에 add해놓음
        
        //add해놓은 부분만 다시 checkPossible 넣기
    }

    static void isCheckWater(int step, int point) {

        for (int i = point; i < N; i++) {

        }

    }

}

/**
 8 10
 2 4 2 2 2 2 4 4 4 2
 4 4
 2 2 4 5
 5 5
 4 5
 3 4
 3 6
 2 4 6 6
 2 2 4 6
 2 2 5 5
 4 7
 */
