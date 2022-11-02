package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem15900 {

    /**
        2 ≤ N ≤ 500,000
        1 <= a,b <= N
        a != b (a,b 사이에 간선 존재)
        ROOT 1번 정점
        성원이가 먼저 시작

        자식이 없는 노드 = 리프노드
        리프노드에서 루트까지의 거리의 총합 : sum
        sum == 홀수 ? Yes : No

     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> roads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            roads.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            roads.get(Integer.parseInt(st.nextToken())-1).add(Integer.parseInt(st.nextToken())-1);
        }

        for(ArrayList<Integer> al : roads) {
            System.out.println(al.toString());
        }
    }
}

/**
 2
 2 1

 Yes

 4
 1 2
 2 3
 2 4

 No

 8
 8 1
 1 4
 7 4
 6 4
 6 5
 1 3
 2 3

 No
 */
