package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2632
 *
 * 제한)
 *  1 <= 손님이 구매하고자 하는 피자 크기 <= 2,000,000
 *  3 <= m, n <= 1000
 *  1 <= 피자조각크기 <= 1000
 *
 * 문제)
 *  1. 두 종류의 피자 A,B 를 취급하는 피자가게에서 피자를 판매할 수 있는 방법을 모두 구하기
 *  2. 한 종류의 피자를 2조각 이상 판매할때는 반드시 연속된 조각들을 잘라서 판매해야한다.
 *
 * 풀이)
 *  1. A,B 각각의 피자에서 나올 수 있는 조각의 합을 전부 구해 Map<Integer, Integer> 에 저장
 *  2. 피자에서 손님이 구매하고자 하는 피자 크기의 갯수를 구한다
 *   - A 피자에서 (+)
 *   - B 피자에서 (+)
 *   - A+B 피자에서 (A*B)
 *
 */
class Problem2632 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int pizzaSize = Integer.parseInt(st.nextToken()); // 손님이 구매하고자 하는 피자 사이즈
        st = new StringTokenizer(br.readLine());
        int aPizzaSliceCount = Integer.parseInt(st.nextToken()); // a 피자의 조각 수
        int bPizzaSliceCount = Integer.parseInt(st.nextToken()); // b 피자의 조각 수

        int[] pizzaA = new int[aPizzaSliceCount];
        int[] pizzaB = new int[bPizzaSliceCount];

        for (int i = 0; i < aPizzaSliceCount; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            pizzaA[i] = size;
        }

        for (int i = 0; i < bPizzaSliceCount; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            pizzaB[i] = size;
        }

        int count = 0;
        Map<Integer, Integer> aSum = getAllSum(pizzaA, pizzaSize);
        Map<Integer, Integer> bSum = getAllSum(pizzaB, pizzaSize);

        // A피자
        count += aSum.getOrDefault(pizzaSize, 0);

        // B피자
        count += bSum.getOrDefault(pizzaSize, 0);

        // A+B 피자
        for (int key : aSum.keySet()) {
            int sumA = key;
            int sumACount = aSum.get(key);
            int sumBCount = bSum.getOrDefault(pizzaSize - sumA, 0);
            count += sumACount * sumBCount;
        }

        System.out.println(count);
    }

    private static Map<Integer, Integer> getAllSum(int[] pizza, int maxSize) {
        int n = pizza.length;
        int[] extended = new int[n * 2]; // 원형 고려
        System.arraycopy(pizza, 0, extended, 0, n);
        System.arraycopy(pizza, 0, extended, n, n);

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += extended[j + k];
                }
                // 원하는 사이즈보다 클 경우 저장 X
                if (sum <= maxSize) {
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        int total = 0;
        for (int size : pizza) {
            total += size;
        }
        map.put(total, map.getOrDefault(total, 0) + 1);
        return map;
    }
}
/*
7
5 3
2
2
1
7
2
6
8
3
 */
