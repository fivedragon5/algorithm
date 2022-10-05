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

        int x,y,indexY;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            //우측에 넴모가 없을경우 추가
            if (squares[y-1] < x) {
                System.out.println(0);
                continue;
            }
            if (squares[N-1] < x) {
                indexY = findIndexY(x, y);
            }
            else {
                indexY = N;
            }
            System.out.println(squares[y-1] - x + indexY - y + 1);
        }
    }

    /**
     * 이분탐색
     */
    static int findIndexY(int x, int y) {
        int left = 0;
        int right = N;
        int middle = 0;
        while (left < right) {
            middle = (left + right) / 2;
            if (squares[middle] < x) {
                right = middle;
            }
            else {
                left = middle + 1;
            }
        }
        return right;
    }
}

/**
 *
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
