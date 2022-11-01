package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem19542 {

    /**
        1 <= N <= 100,000
        1 <= S <= N
        0 <= D <= N
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int S = Integer.parseInt(st.nextToken()); // 캐니 소프트 위치
        int D = Integer.parseInt(st.nextToken()); // 힘

        ArrayList<ArrayList<Integer>> roads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            roads.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            roads.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }



        for(ArrayList<Integer> al : roads) {
            System.out.println(al.toString());
        }
    }
}

/**
 6 1 1
 1 2
 2 3
 2 4
 3 5
 5 6

 6
 */
