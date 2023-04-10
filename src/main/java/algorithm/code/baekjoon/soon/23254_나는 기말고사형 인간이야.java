package algorithm.code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 우선순위 큐
 *
 * 풀이)
 *  1. 우선순위 큐에 현재 점수, 시간당 점수 정보를 시간당 점수 내림차순으로 넣는다
 *  2. 큐에서 하나씩 빼면서 주어신 시간당 가장 높은 점수를 올릴 수 있게 계산 후
 *     시간당 점수를 갱신하여 다시 우선순위 큐에 넣는다.
 *  3. 점수를 갱신하면서 시간을 누적함
 *
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
        int answer = 0;

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
            answer += score[0][i];
            pq.add(new Subject(score[0][i], score[1][i]));
        }

        while (hours > 0 && !pq.isEmpty()) {
            Subject subject = pq.poll();
            int maxTime = (100 - subject.score) / subject.up;
            if (maxTime <= hours) {
                hours -= maxTime;
            }
            else {
                maxTime = hours;
                hours = 0;
            }
            int changeUp = (100 - subject.score) % subject.up;
            int upScore = subject.up * maxTime;
            answer += upScore;
            if (upScore + subject.score != 100) {
                pq.add(new Subject(subject.score + upScore, changeUp));
            }
        }
        System.out.println(answer);
    }

    static class Subject {
        int score;
        int up;

        Subject(int score, int up) {
            this.score = score;
            this.up = up;
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

