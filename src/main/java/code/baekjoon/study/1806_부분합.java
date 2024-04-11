package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 투포인터 알고리즘
 *
 * 풀이)
 *  1. N, S, numbers를 입력 받는다
 *  2. numbers의 index를 가리키는 변수 left, right를 0으로 설정한다
 *      - left, right: 구간의 합을 나타내기 위한 범위
 *      - ex) left:1, right:3 = numbers[1] + numbers[2] + numbers[3]
 *  3. left가 N보다 작거나 같을때 까지 while문 실행
 *      3-1.구간의 합이 S 보다 작을 경우
 *          - right를 증가 시켜 구간의 합을 증가시켜준다
 *      3-2.구간의 합이 S 보다 클 경우
 *          - 현재 구간의 길이를 min과 비교해 최소값을 같는 값으로 갱신
 *          - left를 증가 시켜 구간의 합을 작게 한다
 *  4. left가 N보다 작거나 같을때 OR
 *      구간합이 S보다 작고 right값이 numbers의 길이보다 커질경우
 *      while을 종료시킨다.
 *
 * 제한)
 *  10 ≤ N < 100,000
 *  0 < S ≤ 100,000,000
 *  10,000 이하의 자연수
 */
class Problem1806 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 0;
        int sum = numbers[right];
        int min = 100000;

        while (left <= N) {
            if (sum < S) {
                if (right >= N-1) break;
                sum += numbers[++right];
            }
            else {
                min = Math.min(min, right - left + 1);
                sum -= numbers[left++];
            }
        }
        System.out.println(min == 100000 ? 0 : min);
    }
}
/*
10 15
5 1 3 5 10 7 4 9 2 8

2

10 10
1 1 1 1 1 1 1 1 1 10
 */

