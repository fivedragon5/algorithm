package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17305
 * 제한)
 * 1 <= N <= 250,000
 * 0 <= w <= 5N
 * 1 <= 당도 <= 1,000,000,000
 *
 * 문제)
 * 1. 무게 제한에 맞춰 최대 당도합 구하기
 *
 * 풀이)
 * 1. 무게 제한에 맞춰 3g 사탕을 가방에 최대로 넣음
 * 2. 가방에서 3g가방을 1개씩 꺼내면서 5g 사탕을 넣음
 */
class Problem17305 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사탕의 개수
        int w = Integer.parseInt(st.nextToken()); // 무게 제한
        List<Integer> candy3 = new ArrayList<>();
        List<Integer> candy5 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int sugar = Integer.parseInt(st.nextToken());
            if (weight == 3) {
                candy3.add(sugar);
            } else {
                candy5.add(sugar);
            }
        }

        // 사탕 내림 차순 저장
        candy3.sort(Collections.reverseOrder());
        candy5.sort(Collections.reverseOrder());

        long[] sugarSum3 = new long[candy3.size() + 1];
        long[] sugarSum5 = new long[candy5.size() + 1];

        // 각 사탕 누적합
        for (int i = 1; i <= candy3.size(); i++) {
            sugarSum3[i] = candy3.get(i-1) + sugarSum3[i-1];
        }

        for (int i = 1; i <= candy5.size(); i++) {
            sugarSum5[i] = candy5.get(i-1) + sugarSum5[i-1];
        }

        // 최대로 넣을수 있는 3사탕의 갯수
        int candy3Count = Math.min(candy3.size(), w/3);

        long sum = 0L;
        while(candy3Count >= 0){
            // 5사탕의 index
            int fiveIndex = Math.min((w - 3 * candy3Count)/5, candy5.size());
            // 현재 당도의 합
            long temp = sugarSum3[candy3Count] + sugarSum5[fiveIndex];
            sum = Math.max(sum, temp);
            candy3Count--;
        }

        System.out.println(sum);
    }
}
/*
10 11
3 10
3 20
3 30
3 40
3 50
5 20
5 40
5 60
5 80
5 100
 */
