package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/14226
 * 제한)
 * 2 <= S <= 1,000
 */

class Problem14226 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[1001][1001];
        visited[1][0] = true; // <-- 시작

        Queue<Emoticon> queue = new LinkedList<>();
        queue.offer(new Emoticon(0, 1, 0));


        while (!queue.isEmpty()) {
            Emoticon current = queue.poll();

            if (current.screen == N) {
                System.out.println(current.time);
                return;
            }

            // copy
            if (!visited[current.screen][current.screen]) {
                queue.offer(new Emoticon(current.screen, current.screen, current.time + 1));
                visited[current.screen][current.clipboard] = true;
            }

            // paste
            if (current.screen + current.clipboard < 2000 && !visited[current.screen + current.clipboard][current.clipboard]) {
                queue.offer(new Emoticon(current.clipboard, current.screen + current.clipboard, current.time + 1));
                visited[current.screen + current.clipboard][current.clipboard] = true;
            }

            // delete
            if (current.screen - 1 > 0 && !visited[current.screen - 1][current.clipboard]) {
                queue.offer(new Emoticon(current.clipboard, current.screen - 1, current.time + 1 ));
                visited[current.screen - 1][current.clipboard] = true;
            }
        }
    }

    public static class Emoticon {
        int clipboard;
        int screen;
        int time;

        public Emoticon(int clipboard, int screen, int time) {
            this.clipboard = clipboard;
            this.screen = screen;
            this.time = time;
        }
    }
}

