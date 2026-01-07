package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1010
 *
 * 제한)
 *  0 <= N <= M <= 30
 *
 * 문제)
 *  1. 서쪽의 사이트와 동쪽의 사이트를 다리로 연결
 *    - 이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.
 *    - 다리는 서로 겹쳐질 수 없다.
 *  3. 서쪽의 사이트 개수만큼 (N개) 다리를 지을 때, 다리를 지을 수 있는 경우의 수 반환하기
 *
 * 풀이)
 *  조합
 *  1. 조합 공식을 사용하여 경우의 수 계산
 *   = mCn = m! / (n! * (m-n)!)
 *   -> 이대로 계산하면 m! 이 너무 커지므로 다른 방법으로 계산(팩토리얼 사용X)
 *  2. n이 m-n 보다 크면 n = m-n 으로 변경
 *  3. 1부터 n까지 반복문을 돌면서 result = result * (m - n + i) / i 계산
 */
class Problem1010 {

    private static List<int[]> SITES = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        input();

        StringBuffer sb = new StringBuffer();

        for (int[] site : SITES) {
            sb.append(comb(site[0], site[1])).append("\n");
        }

        System.out.println(sb);
    }

    private static long comb(int n, int m) {
         if (n > m - n) {
             n = m - n;
         }

         long result = 1;
         for (int i = 1; i <= n; i++) {
             result = result * (m - n + i) / i;
         }
        return result;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int testCaseCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testCaseCount; i++) {
            st = new StringTokenizer(br.readLine());
            SITES.add(
                    new int[]{
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    }
            );
        }
        br.close();
    }
}
/*
3
2 2
1 5
13 29

 */
