package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 큐
 *
 * 풀이)
 *  1. 다리에 트럭을 하나씩 올린다 (2 ~ 3를 순차적으로 반복)
 *   - Queue에 add, 데이터는 트럭의 무게, 올린 시간(sec) class
 *   - 마지막 트럭이 올라갈때까지 시간을 1sec씩 증가시키면서 while문 실행
 *
 *  2. 트럭을 올릴때 큐에 데이터를 peek해서 현재 시간 - 트럭을 올린시간을 계산해서 그값이
 *     다리의 길이보다 길면(다리를 지나침) queue에서 poll
 *
 *  3. 다음 올라갈 트럭의 무게 + 다리위에 있는 트럭의 무게 <= 다리의 하중
 *   - 위의 식을 만족할 경우 트럭을 다리에 올린다 (queue.add(Bradge(track weight, current sec))
 *   
 *  4. 마지막 트럭이 올라가면 while문을 종료시키고 현재 시간(sec)에서 다리의 길이를 더해준 값을 출력
 *
 * 제한)
 *  n (1 ≤ n ≤ 1,000)
 *  w (1 ≤ w ≤ 100)
 *  L (10 ≤ L ≤ 1,000)
 */
class Problem13335 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] tracks = new int[n];

        for (int i = 0; i < n; i++) {
            tracks[i] = Integer.parseInt(st.nextToken());
        }

        int sec = 1;
        int totalWeight = tracks[0];
        int next = 1;

        Queue<Bridge> queue = new LinkedList<>();
        queue.add(new Bridge(tracks[0], 1));

        while (next < tracks.length) {
            sec++;
            if (sec - queue.peek().distance >= w) {
                Bridge outTrack = queue.poll();
                totalWeight -= outTrack.weight;
            }
            if (l >= totalWeight + tracks[next]) {
                queue.add(new Bridge(tracks[next], sec));
                totalWeight += tracks[next++];
            }
        }
        System.out.println(sec + w);
    }

    static class Bridge {
        int weight;
        int distance;

        Bridge(int w, int d) {
            this.weight = w;
            this.distance = d;
        }
    }
}
/*
4 2 10
7 4 5 6
 */

