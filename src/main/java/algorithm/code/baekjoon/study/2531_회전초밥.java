package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 접근)
 * 배열을 접시의 갯수 * 2 - 1 만큼 만들어 for문 돌리기 편하게 만들었다.
 *
 * 풀이)
 * 1. 접시의 시작지점 startIndex부터 행사에 참여 할 수 있는 접시의 수 만큼 index 범위를 설정 하여
 *    set함수에 add
 *    - 행사에 참여 했을때 받는 쿠폰초밥도 add
 * 2. 모든 경우의 수를 탐색하여 set에 들어있는 size로 최대값을 갱신
 *
 *
 * 제한)
 *  2 ≤ N ≤ 30,000 // 벨트에 놓인 접시 수
 *  2 ≤ d ≤ 3,000 // 초밥의 가짓 수
 *  2 ≤ k ≤ 3,000 (k ≤ N) // 연속해서 먹는 접시의 수
 *  1 ≤ c ≤ d // 쿠폰 초밥 번호
 */
class Problem2531 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[2*N-1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sushi[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i < 2*N-1; i++) {
            sushi[i] = sushi[i-N];
        }

        int startIndex = 0;
        int max = 0;

        Set<Integer> set = new HashSet<>();

        while (startIndex <= 2 * N - 1 - k) {
            set.add(c);

            for (int i = startIndex; i < startIndex + k; i++) {
                set.add(sushi[i]);
            }

            max = Math.max(max, set.size());

            if (max == k+1) {
                break;
            }

            set.clear();
            startIndex++;
        }

        System.out.println(max);

    }
}

/*
8 30 4 30
7
9
7
30
2
7
9
25

5

8 50 4 7
2
7
9
25
7
9
7
30

4
 */

