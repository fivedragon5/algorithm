package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * TODO: 20230107 변수인 x일의 범위가 넓기 때문에 이분탐색을 활용하자. 추가로 필요한 재료, 필요하지 않는 재료를 정렬하고 K개를 뺀 상태에서 이분탐색을 진행하는것이 좋아보임
 *
 *
 *
 * 제한)
 * 1 <= N <= 200,000
 * 1 <= G <= 10^9
 * 0 <= K <= N
 * 1 <= Si <= 10^9
 * 1 <= Li <= 10^9
 * O = {0,1}
 */
class Problem24041 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //세균 수 : S(i) * max(1, x - L(i))
        int[][] items = new int[N][3];
//        ArrayList<int[]> items = new ArrayList<>();
        // 부패속도, 유통기한, 중요도
        int S = 0;
        int L = 0;
        int O = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());

            items[i][0] = S;
            items[i][1] = L;
            items[i][2] = O;
        }

        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }

        Arrays.sort(items, (i1, i2) -> {
            if (i1[1] == i2[1]) {
                return i2[0] - i1[0];
            }
            return i1[1] - i2[1];
        });

        System.out.println("정렬");

        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }
        
        // 시작일
        int day = items[0][1] + 1;
        // 부패가 시작된 배열의 인덱스
        int pointer = 0;
        // 현재까지의 세균 수
        int totalGerm = 0;
        // 늘어나는 세균 수
        int increaseGerm = 0;

        while (totalGerm <= G) {
            for (int i = pointer; i < items.length; i++) {
                if (items[i][1] < day) {
                    pointer++;
                    increaseGerm += items[i][0];
                }
                else {
                    break;
                }
            }
            totalGerm += increaseGerm;
            System.out.println("day : " + day + "/ totalGerm" + totalGerm);
            day++;
        }
        System.out.println(day-1);
    }
}
/*
4 36 0
2 14 1
3 8 1
5 12 1
7 10 0

12

4 36 1
2 14 1
3 8 1
5 12 1
7 10 0

13

4 36 2
2 14 1
3 8 1
5 12 1
7 10 0

14
 */

