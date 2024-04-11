package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * 접근)
 *  1.정렬
 *  2.최소값
 *
 * 풀이)
 *  1. 입력받은 테스트 케이스에 서류심사 순위 기준 오름차순 정렬
 *  2. 서류심사 1등부터 순회하면서 면접결과 순위를 가지고 min값을 계속 생신해 준다.
 *   - 이때, 현재 사원과 면접결과 순위와 min값을 비교하며
 *      min보다 클때, 작을때를 기준으로 합격여부 및 min값 갱신을 해준다.
 *
 * 제한)
 *  1 <= T <= 20
 *  1 <= N <= 100,000
 */
class Problem1946 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        List<int[][]> tests = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] cases = new int[N][2];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                cases[j][0] = Integer.parseInt(st.nextToken());
                cases[j][1] = Integer.parseInt(st.nextToken());
            }
            tests.add(cases);
        }

        List<Integer> answers = new LinkedList();

        for (int[][] test : tests) {

            List<int[]> sorted = Arrays.stream(test).sorted((o1, o2) ->
                 o1[0] - o2[0]
            ).collect(Collectors.toList());

            int count = sorted.size();
            int min = sorted.get(0)[1];

            for (int i = 1; i < sorted.size(); i++) {
                int current = sorted.get(i)[1];
                if (min < current) {
                    count--;
                }
                else {
                    min = current;
                }
            }
            answers.add(count);
        }
        answers.forEach(System.out::println);
    }
}

/*
2
5
3 2
1 4
4 1
2 3
5 5
7
3 6
7 3
4 2
1 4
5 7
2 5
6 1

4
3
 */

