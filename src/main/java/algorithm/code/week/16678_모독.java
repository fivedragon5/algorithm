package algorithm.code.week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이)
 *  1. 입력받은 수를 오름차순으로 정렬
 *  2. int형 변수(start)에 1을 넣고 정렬한 수를 순회하면서 start와 비교 후 필요한 해커 수(sum)에 누적
 *   - 정렬된 수가 1,2,3,4....n 1씩 증가하는 등차수열 형태로 되도록 필요한 수를 sum에 누적해서 더해준다
 *     이때, i번재 수가 i-1번째 수랑 같을경우 넘기는 예외처리를 해준다
 *      - ex) 1,2,2,2,3,4...n
 * 
 * 제한)
 *  1 ≤ N ≤ 100,000
 */
class Problem16678 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int start = 1;
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if (numbers[i] >= start) {
                sum += numbers[i] - start++;
            }
        }

        System.out.println(sum);
    }
}
/*
5
7
3
6
2
4

7

3
1
1
5
 */

