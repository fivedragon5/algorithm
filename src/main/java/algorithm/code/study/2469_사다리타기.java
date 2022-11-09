package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem2469 {

    /**
     *
     *
     * 첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26).
     * 그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000).
     * 그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.
     * k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다.
     * 그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        String resultPlayer = st.nextToken();

        String[][] ladders = new String[n][k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladders[i] = st.nextToken().split("");
        }
        for (String a[] : ladders) {
            System.out.println(Arrays.toString(a));
        }
    }
}

/**
 10
 5
 ACGBEDJFIH
 *-***-***
 -*-******
 ?????????
 -**-***-*
 **-*-*-*-

 **-*-***-
*/