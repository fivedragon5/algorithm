package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
  예제)
  4
  3 5
  8 14
  5 20
  1 16

 4
 2 6
 1 6
 3 6
 1 16
 */
class Problem1263 {

    public static void main(String args[]) throws IOException {
        /**
         * 하루에 해야할 일 N
         * 걸리는 시간  끝내야할 시간 /// t,s
         *
         * 0시부터 활동 시작가능
         * 두개이상의 일을 같은 시간에 처리할 수 없음
         * 
         * 진영이가 일을 모두 끝마칠 수 있는 가장 늦은 시간을 출력한다. 만약0시부터 시작해도 일을 끝마칠 수 없다면 -1 출력
         *
         * 1 <= N,T <= 1,000
         * 1 <= S <= 1,000,000
         * 
         * 24시간이 아니라 1000000 시간을 기준으로 해야함
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] work = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        //빨리 처리해야 할 일, 같을경우 일의 크기가 큰 것부터 정렬
        Arrays.sort(work, (a1, a2) -> {
            if(a1[1] == a2[1]) {
                return a2[0] - a1[0];
            }
            return a1[1] - a2[1];
        });

        boolean isFlag = true;
        int answer = -1;

        for(int t = work[0][1] - work[0][0]; t >= 0; t--) {
            isFlag = true;
            int currentTime = t;
            for (int i = 0; i < work.length; i++) {
                currentTime += work[i][0];
                if (currentTime > work[i][1]) {
                    isFlag = false;
                    break;
                }
            }
            if(isFlag) {
                answer = t;
                break;
            }
        }
        System.out.println(answer);
    }
}
