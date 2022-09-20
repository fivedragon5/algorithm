package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 예제
 3 2
 1 2 1 2 1 2
 답 2

 3 6
 10 10 10 10 10 10
 답 31

 4 5
 10 1 10 6 3 4 8 2
 답 24

 5 8
 100 99 60 80 30 20 10 89 99 100
 답 472
 */
class Problem20055 {

    public static void main(String args[]) throws IOException {
        /**
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> velts = new ArrayList<>();
        ArrayList<Boolean> robots = new ArrayList<>();

        for (int i = 0; i < 2*N; i++) {
            velts.add(Integer.parseInt(st.nextToken()));
            if (i < N-1) robots.add(false);
        }

        int disabledVelt = 0;
        int step = 0;

        while(disabledVelt < K) {
            step++;

            //1단계 시작
            //벨트 이동
            int last = velts.get(2 * N - 1);
            velts.remove(2 * N - 1);
            velts.add(0, last);

            //로봇 움직여짐
            robots.add(0, false);
            robots.set(N-1, false); //N번째의 로봇을 떨어뜨림

            //2단계
            //로봇 이동(N부터 .... 1번째 컨테이너 벨트 까지 확인)
            for (int i = N-2; i > 0; i--) {
                //로봇이 있을경우
                if (robots.get(i)) {
                    //앞에 로봇이 없고, velt의 내구도가 0보다 클 경우 로봇을 이동시킨다.
                    int velt = velts.get(i+1);
                    if(!robots.get(i+1) && velt > 0) {
                        robots.set(i, false);
                        robots.set(i+1, true);
                        velts.set(i+1, velt-1); //내구도 감소
                        if(velt <= 1) disabledVelt++;
                    }
                }
            }
            
            //N번째에 있는 컨테이너 벨트의 로봇을 떨어뜨림
            robots.remove(N-1);
            
            //3단계
            //로봇 올리기
            int velt = velts.get(0);
            if (velt > 0) {
                robots.set(0, true);
                velts.set(0, velt-1);
                if(velt <= 1) disabledVelt++;
            }
        }

        System.out.println(step);
    }
}
