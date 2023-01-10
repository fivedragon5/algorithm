package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * TODO: 20230107 변수인 x일의 범위가 넓기 때문에 이분탐색을 활용하자. 추가로 필요한 재료, 필요하지 않는 재료를 정렬하고 K개를 뺀 상태에서 이분탐색을 진행하는것이 좋아보임
 *
 * 제한)
 * 1 <= N <= 200,000
 * 1 <= G <= 10^9
 * 0 <= K <= N
 * 1 <= Si <= 10^9
 * 1 <= Li <= 10^9
 * O = {0,1}
 * 세균 수 : S(i) * max(1, x - L(i))
 */
class Problem24041 {
    public static void main(String args[]) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<int[]> ingredientItems = new ArrayList<>();
        ArrayList<int[]> items = new ArrayList<>();

        // 부패속도, 유통기한, 중요도
        int S = 0;
        int L = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            if (st.nextToken().equals("0")) {
                ingredientItems.add(new int[]{S, L});
            }
            else {
                items.add(new int[]{S, L});
            }
        }

        //x일을 정해놓고 이분탐색 진행 이때 그냥 재료 리스트를 계산하여 우선순위큐에 넣어두고 K만큼 빼서 계산해보자.
        int left = 0;
        int right = 2000000000;
        long germCount = 0;
        long germ = 0;
        boolean isOver = false;

        PriorityQueue<Long> germQueue;

        while (left < right) {
            int mid = (left + right) / 2;
            germCount = 0;
            germQueue = new PriorityQueue<>(Collections.reverseOrder());
            isOver = false;

            for (int[] item : items) {
                germ = calcGerm(item[0], item[1], mid);
//                germCount += germ;
                germQueue.add(germ);
            }

            for (int i = 0; i < K; i++) {
                if (germQueue.isEmpty()) {
                    break;
                }
                germQueue.poll();
            }

            //필수 재료들의 세균 수 계산
//            for (int[] item : ingredientItems) {
//                germCount += calcGerm(item[0], item[1], mid);
//                if (G < germCount) {
//                    isOver = true;
//                    break;
//                }
//            }

            for (int[] item : ingredientItems) {
                germQueue.add(calcGerm(item[0], item[1], mid));
            }

            int size = germQueue.size();

            for (int i = 0; i < size; i++) {
                long temp = germQueue.poll();
                if (temp > G || germCount > G) {
                    isOver = true;
                    break;
                }
                germCount += temp;
            }

            if (!isOver) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        System.out.println(answer);
    }

    static long calcGerm(int s, int l, int x) {
        if (l >= x) {
            return s;
        }
        return (long) s * (long) Math.max(1, x - l);
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

