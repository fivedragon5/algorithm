package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Probleam13199 {

    /*
        1 <= P <= 50,000
        1 <= M <= 1,000,000
        2 <= F <= 1,000
        1 <= C < F

        상언이와 두영이는 둘 다 M 원을 가지고 있고, 치킨의 가격은 P 원이다. 이때, 상언이는 두영이보다 치킨을 얼마나 더 시켜먹을 수 있을까?

        2
        10000 50000 5 1
        10000 250000 5 1

        2
        674 50000 5 1
        9512 250000 5 1

        0
        1
     */


    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        int P = 0;
        int M = 0;
        int F = 0;
        int C = 0;

        for (int i = 0; i < T; i++) {

            st = new StringTokenizer(br.readLine());

            P = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            int taotalCoupon = M / P * C;
            int couponChicken = taotalCoupon / F;
            int plus = 0;

            if (taotalCoupon >= F) {
                plus = (taotalCoupon - F) / (F - C) + 1;
            }

            plus -= couponChicken;
//시간초과
//            while (couponRemainder >= F) {
//
//                int temp = couponRemainder / F;
//                plus += temp;
//                couponRemainder = couponRemainder % F;
//                couponRemainder += temp * C;
//
//            }
            System.out.println(plus);
        }
    }
}
