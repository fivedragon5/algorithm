package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. 최소 힙 사용
 * 2. 가로와 세로를 구분하여 품
 *
 *
 * 제한)
 * 2 <= R, C <= 20
 */
class Problem1706 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[][] crossWords = new String[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            crossWords[i] = st.nextToken().split("");
        }

        PriorityQueue<String> queue = new PriorityQueue<>();

        // 가로
        for (int i = 0; i < R; i++) {
            String[] wordList = String.join("", crossWords[i]).split("#");
            for (String word : wordList) {
                if (word.length() > 1) {
                    queue.add(word);
                }
            }
        }

        if (queue.size() > 0) {
            String temp = queue.poll();
            queue = new PriorityQueue<>();
            queue.add(temp);
        }

        // 세로
        for (int i = 0; i < C; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < R; j++) {
                sb.append(crossWords[j][i]);
            }
            String[] wordList = sb.toString().split("#");
            for (String word : wordList) {
                if (word.length() > 1) {
                    queue.add(word);
                }
            }
        }
        System.out.println(queue.peek());
    }
}
/*
4 5
adaca
da##b
abb#b
abbac

abb
 */