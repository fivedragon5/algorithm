package algorithm.code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 접근)
 * 1. 그래프
 * 2. DFS
 * 3. 문제에 제시된 "같은 도시를 여러 번 방문하는 것도 가능하다."를 활용하여 시간을 줄였다.
 *  - 첫번째 도시부터 방문 할 수 있는 도시를 먼저 탐색 한뒤 여행계획을 조사하는 식으로 품
 *
 * 풀이)
 * 1. 각각의 도시들에 대해 연결된 도시의 정보를 ArrayList에 담는다.
 * 2. 제시된 여행 계획의 첫번째 도시를 가지고 온다.
 * 3. 첫번째 도시를 통해 갈 수 있는 모든 도시들을 방문한뒤 방문할때마다 방문 체크 표시를 해준다.
 *  - DFS
 * 4. 여행 계획의 두번째 도시부터 마지막 도시까지 방문 체크를 확인한다.
 *  - 방문하지 않은 도시가 있을경우 "NO"출력 후 종료
 *  - 반복문이 조건에 걸리지않고 종료될 시 "YES"출력 후 종료
 *
 * 제한)
 * 1 <= N <= 200
 * M <= 1000
 */
class Problem1976 {

    static ArrayList<ArrayList<Integer>> cities;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        cities = new ArrayList<>();
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            cities.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N; i++) {
            String[] array = br.readLine().split(" ");
            for (int j = i; j < N; j++) {
                if (array[j].equals("1")) {
                    cities.get(i).add(j);
                    cities.get(j).add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken()) - 1;
        visited[start] = true;
        bfs(start);

        //시작점을 기준으로 갈수 있는 도시를 전부 탐색.
        for (int i = 0; i < M - 1; i++) {
            if(!visited[Integer.parseInt(st.nextToken()) - 1]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        return;
    }

    static void bfs(int start) {
        for (int city : cities.get(start)) {
            if (!visited[city]) {
                visited[city] = true;
                bfs(city);
            }
        }
    }
}
/*
3
3
0 1 0
1 0 1
0 1 0
1 2 3

YES
 */

