package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  풀이)
 *  투포인터 사용
 *
 *  1.주어진 번호를 int형 배열 numbers(길이 N)에 넣고 오름차순으로 정렬.
 *
 *  2.numbers의 index를 나타내는 left, right를 선언하고
 *    left:0, right:1 부터 시작
 *
 *  3.right가 주어진N 보다 커지거나, 조건에 맞는M을 찾을경우 반복 종료하는 while문 작성
 *
 *  4.두수의 차(numbers[rigiht], numbers[left])와 주어진 M을 비교
 *   - 두수의 차가 M보다 작을경우 두수의 차를 크게 하기 위해 right증가 
 *   - 두수의 차가 M보다 클경우 두수의 차를 작게 하기 위해 left증가
 *     - 두수의 차가 M보다 커야 조건에 만족하기 때문에 이때 두수의 차를 최소값과 비교하여 갱신
 *   - 두수의 차가 M일경우 조건에 부합 return 해줌
 *
 *  5.최소값 출력
 *
 * 제한)
 *     1 ≤ N ≤ 100,000
 *     0 ≤ M ≤ 2,000,000,000
 *     0 ≤ |A[i]| ≤ 1,000,000,000
 */

class Problem2230 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        //조건에 같은 수를 고를 수 있기때문에 해당 조건을 추가해줌
        if (M == 0) {
            System.out.println(0);
            return;
        }

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        //오름차순 정렬
        Arrays.sort(numbers);
        
        //최소값을 큰 값으로 셋팅
        long min = 2000000000;
        long calc = 0;
        int left = 0;
        int right = 1;

        while (right < N) {
            calc = numbers[right] - numbers[left];
            if (calc > M) {
                left++;
                min = Math.min(min, calc);
            }
            else if (calc < M || left == right) {
                right++;
            }
            else if (calc == M) {
                min = calc;
                break;
            }
        }
        System.out.println(min);
    }
}
/*
3 3
1
5
3

4
 */

