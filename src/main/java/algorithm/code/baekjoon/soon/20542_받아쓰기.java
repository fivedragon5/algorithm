package algorithm.code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 제한)
 * 1 ≤ n ≤ 1,000,000
 * 1 ≤ m ≤ 1,000,000
 * 1 ≤ n × m ≤ 10,000,000
 */
class Problem20542 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String write = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String answer = st.nextToken();

        int result = check(write, answer);
    }

    static int check(String write, String answer) {
        //i - > i, j, l
        //v - > v, w
        int count = 0;

        int index = 0;

        StringBuffer sb = new StringBuffer(write);

        return count;
    }
}

