package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem19845 {

    static int N;
    static int[] squares;

    public static void main(String args[]) throws IOException {
        /**
            1 < N,Q <= 250,000
            1 <= a <= 10^9
            a1 >= a2 >= ... > an
            1 <= x <= 10^9, 1 <= y <= n
         
            Q 레이져 설치 갯수

            레이저 (x,y)
            1. x,y+n 전부 삭제
            2. x+n, y 전부 삭제

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        squares = new int[N];
        
        //네모 입력
        for (int i = 0; i < N; i++) {
            squares[i] = Integer.parseInt(st.nextToken());
        }

        //레이저 계획 시작
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int laser_x = Integer.parseInt(st.nextToken());
            int laser_y = Integer.parseInt(st.nextToken());
            if(N <= laser_y) {
                System.out.println(0);
                continue;
            }
            else {
                int remove_x = squares[laser_y - 1] - laser_x + 1;
                System.out.println("square:" + squares[laser_y-1] + "/laser_x:" + laser_x + "/remove:" + remove_x);
                int find_y = findRemoveY(laser_x, laser_y);
                int remove_y = N - laser_y + 1 - (N - find_y);
                System.out.println(remove_x + remove_y - 1);
            }
        }
    }

    static int findRemoveY(int x, int y) {
        // squares[n] = x-1 && squares[n-1] = x 를 만족하는 n을 찾는다.
        int left = 0;
        int right = N-1;
        int middle = 0;

        System.out.println("===findY start===");

        while (left <= right) {

            middle = (left + right) / 2;

            System.out.println("left:" + left + "/right:" + right + "/middle:" + middle);

            if (squares[middle] < x) {
                right = middle;
            }
            else {
                left = middle;
            }
        }

        return middle;
    }
}

/**

 oo
 ooo
 ooo

 ex)
 3 11
 3 3 2
 1 1
 1 2
 1 3
 2 1
 2 2
 2 3
 3 1
 3 2
 4 1
 4 2
 3 3

 ans)
 5
 4
 2
 4
 3
 1
 2
 1
 0
 0
 0
 */
