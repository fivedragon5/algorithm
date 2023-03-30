package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 정렬
 *  2. 투포인터
 * 풀이)
 *  1. 나도리 바구니들을 나도리 갯수 오름차순 정렬
 *  2. 바구니 index[0...N] left: 0, right: N 설정
 *  3. 반복문 시작
 *   - 조건) left < right or 나도리를 옮긴 횟수 < K
 *   - nadoriCount = 바구니[left] + 바구니[right]
 *     - nadoriCount > K
 *       - right--
 *       - left에서 옮긴 나도리 수를 카운트에 추가
 *     - nadoriCount < K
 *       - left++
 *       - left에서 옮긴 나도리 수를 카운트에 추가
 *     - nadoriCount%L == 0
 *       - left++
 *       - right++
 *       - left에서 옮긴 나도리 수를 카운트에 추가
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

        while (left <= right && count <= T) {
            if (left == right) {
                if (nadoris[right]%K != 0) temp = 1;
                break;
            }
            int nadoriCount = nadoris[left] + nadoris[right];
            if (nadoriCount > K) {
                temp = K - nadoris[right--];
                count += temp;
                nadoris[left] -= temp;
            }
            else if(nadoriCount%K == 0) {
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

2 2 0
0 0
 */

