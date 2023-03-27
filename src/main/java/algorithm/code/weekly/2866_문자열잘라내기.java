package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 풀이)
 *  1. 문자열을 세로로 잘라 Set에 넣는다
 *  2. Set의 size가 C의 수보다 작아지면 return
 *   - C랑 같다면 가장 앞의 문자열을 잘라서 Set에 넣고 size비교를 반복해준다
 *
 * 제한)
 * 2 ≤ R, C ≤ 1000
 */
class Problem2866 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[][] list = new String[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = st.nextToken().split("");
        }

        String[] colString = new String[C];
        Set<String> set = new HashSet();

        for (int i = 0; i < C; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < R; j++) {
                sb.append(list[j][i]);
            }
            colString[i] = sb.toString();
            set.add(sb.toString());
        }

        if (set.size() < C) {
            System.out.println(0);
            return;
        }

        int count = 0;

        for (int i = 1; i < R; i++) {
            Set<String> subStringSet = new HashSet();
            for (String str : set) {
                subStringSet.add(str.substring(i, R));
            }
            if (subStringSet.size() < C) {
                System.out.println(count);
                return;
            }
            count++;
        }
        System.out.println(count);
    }
}
/*
2 6
dobarz
adatak

0
 */

