package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 제한)
 * 1 ≤ S.length() ≤ 999
 * 2 ≤ T.length() ≤ 1000
 * S.length()< T.length()
 */
class Problem12904 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //S -> T
        //문자열 뒤에 A추가 || 문자를 뒤집고 B추가
        String S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String T = st.nextToken();

        StringBuilder sb = new StringBuilder();
        sb.append(S);

        int TLength = T.length();

        boolean isRevers = false;

        while (TLength > sb.length()) {
            int index = sb.length() - 1;

            if (sb.toString().charAt(index) == T.charAt(index)) {

            }
            else {
                sb.append('B');
                sb.insert(0,'A');
            }
        }
    }
}

/*
B
ABBA

1

AB
ABBA

0
 */

