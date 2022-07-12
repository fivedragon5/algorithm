package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem17182 {
    public static void main(String srgs[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] planet = new ArrayList[N];

        for(int i = 0; i < N + 1; i++) {
            planet[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N ; j++) {
                int distance = Integer.parseInt(st.nextToken());
                if (i != j) {
                    planet[i].add(new int[]{j, distance});
                }
            }
        }
    }
}
