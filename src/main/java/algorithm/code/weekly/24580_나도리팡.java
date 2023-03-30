package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 제한)
 *  2 <= N, K <= 10000
 *  0 <= T <= 10^9
 */
class Problem24580 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nadoris = new int[N];

        for (int i = 0; i < N; i++) {
            nadoris[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nadoris);

        int count = 0;
        int left = 0;
        int right = N-1;
        int temp = 0;

        while (left < right && count <= T) {
            int nadoriCount = nadoris[left] + nadoris[right];
            if (nadoriCount > K) {
                temp = K - nadoris[right--];
                count += temp;
                nadoris[left] -= K - temp;
            }
            else if(nadoriCount == K) {
                count += nadoris[left];
                left++;
                right--;
                temp = 0;
            }
            else {
                count += nadoris[left];
                nadoris[right] += nadoris[left++];
            }
        }

        if (count > T || temp != 0) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
        }


    }
}
/*
2 2 1
2 1

3 5 2
1 2 2
 */

