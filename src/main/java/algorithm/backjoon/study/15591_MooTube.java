package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem15591 {
    private static class Video {
        int num;
        int usado;

        public Video(int num, int usado) {
            this.num = num;
            this.usado = usado;
        }

    }
    public static void main(String srgs[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());
        }
        System.out.print(sb);
    }
}
