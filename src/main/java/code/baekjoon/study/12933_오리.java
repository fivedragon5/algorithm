package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12933
 *
 * 제한)
 *  5 <= input.length <= 2500
 *
 * 문제)
 *  1. 오리의 울음소리를 나타낸 input 이 주어진다.
 *  2. input 여러 오리들의 울음소리가 섞여있다.
 *  3. input에 섞여있는 오리울음소리를 판별해서 총 몇마리에 울음소리가 섞였는지 반환하기
 *
 * 풀이)
 *  동시에 우는 오리를 찾는 것이 핵심
 *   - while문이 2개 존재(out, in)
 *      - 내부(in)에서 quack는 한마리의 오리로 취급
 *      - 외부(out)에서 오리를 찾을 경우 동시에 우는 것으로 취급
 *
 */
class Problem12933 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String input = st.nextToken();

        if (input.length() % 5 != 0 || input.charAt(0) != 'q') {
            System.out.println(-1);
            return;
        }

        char[] quack = new char[]{'q', 'u', 'a', 'c', 'k'};
        boolean[] visited = new boolean[input.length()];
        int remain = input.length();
        int count = 0;

        while (remain != 0) {
            int quackIndex = 0;
            int idx = 0;
            boolean check = false;
            int[] quackIndexArray = new int[5];
            while (idx < input.length()) {
                if (!visited[idx] && input.charAt(idx) == quack[quackIndex]) {
                    quackIndexArray[quackIndex++] = idx;
                    if (quackIndex == 5) {
                        check = true;
                        remain -= 5;
                        quackIndex = 0;
                        for (int i : quackIndexArray) {
                            visited[i] = true;
                        }
                    }
                }
                idx++;
            }
            if (check) count++;
            else break;
        }
        System.out.println(remain == 0 ? count : -1);
    }
}
/*
quqacukqauackck
2

kcauq
-1

quackquackquackquackquackquackquackquackquackquack
1

qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk
10

quqaquuacakcqckkuaquckqauckack
3
 */
