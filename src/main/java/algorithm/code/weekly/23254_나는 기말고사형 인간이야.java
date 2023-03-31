package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 제한)
 *  1 <= N, M <= 200,000
 *  1 <= a, b <= 100
 */
class Problem23254 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int hours = N * 24;

        int[][] score = new int[2][M];
        PriorityQueue<Subject> pq = new PriorityQueue<>((o1, o2) -> o2.up - o1.up);

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            pq.add(new Subject(score[0][i], score[1][i]));
        }

        while (hours < 0) {
            Subject subject = pq.poll();
            int maxTime = (100 - subject.score) / subject.up;
            if (maxTime <= hours) {
                hours -= maxTime;
            }
            else {
                maxTime = hours;
            }
            int changeUp = (100 - subject.score) % subject.up;
            pq.add(new Subject(subject.score + (subject.up * maxTime), changeUp));
        }
    }

    static class Subject {
        int score;
        int up;

        Subject(int score, int up) {
            this.score = score;
            this.up = up;
        }

        @Override
        public String toString() {
            return this.score + "," + this.up;
        }
    }
}
/*
1 2
50 60
4 3

194

8 7
30 15 70 50 40 40 50
2 2 1 3 1 2 1

627
 */

