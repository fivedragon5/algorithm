package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 * 누적합
 * 주의 : 마지막 y번째 악보에서는 실수 안함 
 * 주의 : 피아노 체조 첫번째 x악보 처리
 *
 * 풀이)
 * 1. n번째 악보의 난이도 < n+1번째 악보의 난이도가 높을경우 실수 처리
 *  - 이때 누적합 증가 및 실수하는 시점 기록 (boolean[])
 * 2. 질문을 순회 하면서 x ~ y 누적합배열[y] - 누적합배열[x] 해주기
 *  - 문제 조건중 마지막 y번째 악보에서는 실수 하지 않기 때문에 y번째에서 실수 했다면 실수 카운트를 -1 해준다
 *  - x번째 에서 실수했을경우 실수 카운트+1
 * 
 * 제한)
 * 1 ≤ N ≤ 100,000
 * 1 ≤ Q ≤ 100,000
 * x, y(1 ≤ x ≤ y ≤ N)
 */
class Problem21318 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] sheets = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            sheets[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());

        int prefixSum = 0;

        int[] prefixSumArray = new int[N];
        boolean[] isMistake = new boolean[N];

        for (int i = 0; i < N-1; i++) {
            if (sheets[i] > sheets[i+1]) {
                isMistake[i] = true;
                prefixSumArray[i] = ++prefixSum;
            } else {
                prefixSumArray[i] = prefixSum;
            }
        }

        prefixSumArray[N-1] = prefixSum;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int mistakeCount = prefixSumArray[end] - prefixSumArray[start];
            if (isMistake[start]) {
                mistakeCount++;
            }
            if (isMistake[end]) {
                mistakeCount--;
            }
            System.out.println(mistakeCount);
        }
    }
}
/*
9
1 2 3 3 4 1 10 8 1
5
1 3
2 5
4 7
9 9
5 9
 */

