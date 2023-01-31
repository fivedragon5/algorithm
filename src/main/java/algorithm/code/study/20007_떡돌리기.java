package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 접근)
 *  1. 최단거리 알고리즘 Dijkstra
 *
 * 풀이)
 *  1. 성현이의 집에서 부터 모든집 까지의 최단거리를 가지는 배열을 만듬
 *      - 다익스트라자 사용
 *  2. 최단거리 배열을 오름차순으로 정렬
 *  3. 정렬한 배열을 순회하면서 왕복 거리를 생각하며 계산
 *      - 다른 집을 방문한 후에는 다시 집으로 돌아와야 함 (떡은 한번에 한개씩)
 *      - 왕복거리가 X일을 넘어가는 집이 있을경우 -1을 return 해줌 
 *      
 * 제한)
 * 2 ≤ N ≤ 1,000
 * 1 ≤ M ≤ 100,000
 * 1 ≤ X ≤ 10,000,000
 * 0 ≤ Y < N
 * 0 ≤ A,B < N
 * 1 ≤ C ≤ 10,000
 */
class Problem20007 {

    static ArrayList<home>[] list;
    static int[] distanceArray;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수
        int X = Integer.parseInt(st.nextToken()); // 하루에 걸을 수 있는 거리
        int Y = Integer.parseInt(st.nextToken()); // 성현이의 집

        list = new ArrayList[N]; // 그래프 연걸 정보
        distanceArray = new int[N]; // 성현이의 집으로부터 다른집들까지의 거리 
        visited = new boolean[N]; // 방문 확인용

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            list[h1].add(new home(h2, distance));
            list[h2].add(new home(h1, distance));
        }

        Arrays.fill(distanceArray, Integer.MAX_VALUE); // 최대값으로 초기화

        dijkstra(Y);

        Arrays.sort(distanceArray);

        int day = 1;
        int sum = 0;
        for (int d : distanceArray) {
            if (d * 2 > X) {
                System.out.println(-1);
                return;
            }
            sum += d * 2;
            if(sum > X) {
                day++;
                sum = d * 2;
            }
        }

        System.out.println(day);
    }

    static void dijkstra(int start) {

        PriorityQueue<home> pq = new PriorityQueue<>();

        distanceArray[start] = 0;
        pq.add(new home(start, 0));

        while(!pq.isEmpty()) {
            home nodeInfo = pq.poll();
            int node = nodeInfo.number;
            int distance = nodeInfo.dis;

            if (visited[node]) continue;

            for (int i = 0; i < list[node].size(); i++) {
                int nextNode = list[node].get(i).number;
                int nextDistance = list[node].get(i).dis;

                if (!visited[nextNode] && distanceArray[nextNode] > nextDistance + distance) {
                    distanceArray[nextNode] = nextDistance + distance;
                    pq.add(new home(nextNode, distanceArray[nextNode]));
                }
            }

            visited[node] = true;
        }
    }

    public static class home implements Comparable<home> {
        int number, dis;
        public home(int number, int dist) {
            this.number = number;
            this.dis = dist;
        }
        @Override
        public int compareTo(home o) {
            return this.dis - o.dis;
        }
    }
}
/*
5 6 21 0
0 1 6
0 2 3
0 3 10
1 2 2
2 4 7
3 4 8

3

6 5 10 4
0 4 6
0 5 2
1 3 1
1 5 8
2 3 1

-1
 */

