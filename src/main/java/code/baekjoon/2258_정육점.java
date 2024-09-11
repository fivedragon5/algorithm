package code.baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2258
 * 1 <= N <= 100,000
 * 1 <= M <= 2,147,483,647
 * 1 <= weight, price
 *
 * 1. 원하는 양의 고기 M을 얻기 위해 지불해야 하는 최소 금액을 출력
 * 2. 불가능한 경우 -1 출력
 *
 * 풀이)
 * 1. 가격 오름차순, 무게 내림차순 정렬
 * 2. 같은 가격에대한 예외처리
 * 3. 무게가 M보다 크거나 같은 조건을 만족하더라도 더 좋은 조건이 있을 수 있음
 *    따라서 마지막까지 확인해야 함
 */
class Problem2258 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list.add(new int[]{weight, price});
        }

        Collections.sort(list, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });

        int sum = 0;
        int sumPrice = 0;
        int beforePrice = -1;
        int resultPrice = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            sum += list.get(i)[0];

            if (beforePrice == list.get(i)[1]) {
                sumPrice += list.get(i)[1];
            } else {
                sumPrice = list.get(i)[1];
                beforePrice = list.get(i)[1];
            }

            if (sum >= M) {
                resultPrice = Math.min(resultPrice, sumPrice);
            }

        }

        System.out.println(sum >= M ? resultPrice : -1);
    }
}
/*
4 20
4 8
1 2
2 4
3 6

10 10
2 3
2 4
2 5
3 1
1 3
7 9
7 3
8 4
10 3
3 10

3
 */
