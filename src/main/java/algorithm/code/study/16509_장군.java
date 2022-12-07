package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 제한)
 * 0 <= 행 <= 9
 * 0 <= 열 <= 8
 */
class Problem16509 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] sang = new int[2];
        int[] king = new int[2];

        sang[0] = Integer.parseInt(st.nextToken());
        sang[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        king[0] = Integer.parseInt(st.nextToken());
        king[1] = Integer.parseInt(st.nextToken());

    }
}

/**
 * 4 2
 * 2 5
 *
 * 1
 */

