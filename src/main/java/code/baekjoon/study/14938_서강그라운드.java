package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 접근)
 * 각각의 도시(0 ~ n)에서 주어진 범위내에 갈 수 있는 모든 도시들을 저장한 후 item들을 모두 더함
 *
 * 풀이)
 * 기본정보 외의 방문처리를 위한 boolean[] vistied, 수색이 가능한 범위의 도시정보를 가질 set 선언
 *  - set으로 선언한 이유 : 도시의 순서는 중요하지 않음, 해당 도시의 아이템 갯수를 더할것임
 *  출발 지점을 각각의 도시 (0 ~ n)까지 순회하면서 탐색가능한 도시를 set에 add해줌
 *  set에 있는 데이터를 꺼내면서 item을 더해줌 -> 출력
 *
 *  check(): 주어진 범위내 수색할 수 있는 도시의 리스트를 set에 add
 *  addCitiesItem(): set에 있는 도시의 item들을 더해줌
 *
 * 조건)
 * 1 <= n <= 100 지역의 개수
 * 1 <= m <= 15 수색범위
 * 1 <= r <= 100 길의 수
 * 1 <= items <= 30 각 지역의 아이템 수
 * 1 <= l <= 15 길의 길이
 */
class Problem14938{

    static int n,m,r;
    static int[] items;
    static ArrayList<ArrayList<int[]>> map = new ArrayList<>();
    static boolean[] visited;
    static Set<Integer> set;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int city1 = Integer.parseInt(st.nextToken()) - 1;
            int city2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            map.get(city1).add(new int[]{city2, distance});
            map.get(city2).add(new int[]{city1, distance});
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            set = new HashSet<>();
            visited[i] = true;
            set.add(i);
            check(i, 0);
            answer = Math.max(answer, addCitiesItem(set));
            visited[i] = false;
        }

        System.out.println(answer);
    }

    static void check(int currentCity, int findCount) {
        ArrayList<int[]> list = map.get(currentCity);

        for(int[] city: list) {
            int nextCity = city[0];
            int nextDistance = city[1];
            if(!visited[nextCity] && m >= findCount + nextDistance) {
                visited[nextCity] = true;
                set.add(nextCity);
                check(nextCity,findCount + nextDistance);
                visited[nextCity] = false;
            }
        }
    }

    static int addCitiesItem(Set<Integer> set) {
        int sum = 0;
        for (int item : set) {
            sum = sum + items[item];
        }
        return sum;
    }
}
/*
5 5 4
5 7 8 2 3
1 4 5
5 2 4
3 2 3
1 2 3

23
 */
