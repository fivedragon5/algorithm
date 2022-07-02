package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());

            for (int j = 0; j < s; j++) {
                int data = Integer.parseInt(st.nextToken());
                sb.append(data).append("\n");
            }
        }
        System.out.println("sdafasdf");

    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}
