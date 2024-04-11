package code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 접근)
 * 주어진 숫자를 받고 중간값들 우선 출력으로 뽑아보자.
 *
 * 제한)
 * 1 <= 노드 수 <= 10,000
 * 1 <= 노드 값 <= 10^6
 */
class Problem5639 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        while (true) {
            line = br.readLine();
            if (line == null || line.equals(""))
                break;

            list.add(Integer.parseInt(line));
        }

        int root = list.get(0);

    }
}
/*
50
30
24
5
28
45
98
52
60
 */
