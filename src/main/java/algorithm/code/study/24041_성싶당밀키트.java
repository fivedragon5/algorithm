package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
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
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //세균 수 : S(i) * max(1, x - L(i))
        int[][] itemsArray = new int[N][3];

        ArrayList<int[]> ingredientItems = new ArrayList<>();
        ArrayList<int[]> items = new ArrayList<>();
//        ArrayList<int[]> itemsArray = new ArrayList<>();
        // 부패속도, 유통기한, 중요도
        int S = 0;
        int L = 0;
        int O = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());

            if (O == 0) {
                ingredientItems.add(new int[]{S, L});
            }
            else {
                items.add(new int[]{S, L});
            }
        }
        System.out.println("필수 재료 리스트");
        for (int[] item : ingredientItems) {
            System.out.println(Arrays.toString(item));
        }

        System.out.println("그냥 재료 리스트");
        for (int[] item : items) {
            System.out.println(Arrays.toString(item));
        }
        
        //x일을 정해놓고 이분탐색 진행 이때 그냥 재료 리스트를 계산하여 우선순위큐에 넣어두고 K만큼 빼서 계산해보자.
        int left = 0;
        int right = Integer.MAX_VALUE;
        int mid = 0;
        long germCount = 0;
        long germ = 0;

        PriorityQueue<Integer> germQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        while (left < right) {
            mid = (left + right) / 2;
            germCount = 0;
            germQueue.clear();
            //필수 재료들의 세균 수 계산
            germCount += calcIngredientItemTotalGermCount(ingredientItems, mid);

            //빼도 되는 재료들의 세균 수 계산
            for (int[] item : items) {
                germ = calcGerm(item[0], item[1], mid);
                germCount += germ;
                germQueue.add(germ);
            }

            for (int i = 0; i < K; i++) {
                germCount -= germQueue.poll();
            }

            if (G >= germCount) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
            else {
                right = mid;
            }

        }

        System.out.println(answer);
    }
    
    //필수 재료들을 모은 list에서 x일 후에 세균 수를 계산
    static long calcIngredientItemTotalGermCount(ArrayList<int[]> items, int day) {
        long count = 0;
        for (int[] item : items) {
            count += calcGerm(item[0], item[1], day);
        }
        return count;
    }

    static long calcGerm(int s, int l, int x) {
        return s * Math.max(1, x - l);
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

