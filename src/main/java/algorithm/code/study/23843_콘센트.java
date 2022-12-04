package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  1 ≤ N ≤ 10,000
 *  1 ≤ M ≤ 10
 */
class Problem23843 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Integer[] devices = new Integer[N];

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            devices[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(devices, (device1, device2) -> device2 - device1);

        for (int i = 0; i < M; i++) {
            queue.add(devices[i]);
        }

        ArrayList<Integer> queueTempList = new ArrayList<>();

        int nextDeviceIndex = M;
        int chargingTime = 0;

        while (nextDeviceIndex < N) {

            int minTime = queue.poll();
            chargingTime += minTime;

            while (queue.size() > 0) {
                int temp = queue.poll();
                if (temp - minTime > 0) {
                    queueTempList.add(temp - minTime);
                }
            }

            for (int time : queueTempList) {
                queue.add(time);
            }

            queueTempList.clear();

            while (queue.size() < M) {
                if (nextDeviceIndex >= N) break;
                queue.add(devices[nextDeviceIndex]);
                nextDeviceIndex++;
            }
        }

        int max = 0;

        for (int a : queue) {
            max = a;
        }

        System.out.println(chargingTime + max);

    }
}
/*
5 2
1 4 4 8 1

9

5 1
1 1 1 1 1
 */

