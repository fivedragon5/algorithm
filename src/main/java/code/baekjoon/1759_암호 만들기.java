package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/1759
 * 3 <= L <= C <= 15
 * All alphabet is lowercase
 *
 * 1. 서로다른 L개의 알파벳
 * 2. 최소 1개의 모음
 * 3. 최소 2개의 자음
 * 4. 알파벳은 오름차순
 */
class Problem1759 {

    private static int L;
    private static int C;
    private static Character[] alphabets;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[C];

        st = new StringTokenizer(br.readLine());

        alphabets = new Character[C];

        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabets); // 문자열 정렬

        StringBuilder sb = new StringBuilder();
        bfs(sb, 0, visited, 0, 0);

        return;
    }

    private static void bfs(
            StringBuilder sb,
            int start,
            boolean[] visited,
            int vowelCount,
            int consonantCount
    ) {
        if (sb.length() == L) {
            if (vowelCount >= 1 && consonantCount >= 2) {
                System.out.println(sb);
            }
        }
        for (int i = start; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(alphabets[i]);
                if (isVowel(alphabets[i])) {
                    bfs(sb, i + 1, visited, vowelCount + 1, consonantCount);
                } else {
                    bfs(sb, i + 1, visited, vowelCount, consonantCount + 1);
                }
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
/*
4 6
a t c i s w

acis
acit
aciw
acst
acsw
actw
aist
aisw
aitw
astw
cist
cisw
citw
istw
 */
