package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1615
 * 제한)
 * 1 <= N <= 2000
 * 1 ≤ M ≤ N×(N-1) / 2
 *
 * 1 1 3 6 10 15 21
 *
 * 문제)
 * 1. 간선이 교차하는 총 갯수를 출력
 *  - 간선이 교차하는 조건
 *      - (A1,B1), (A2,B2) 일때
 *          - A1 > A2 AND B1 < B2
 *          - A1 < A2 ABD B1 > B2
 *
 * 풀이)
 *
 */
class Problem1615 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 갯수
        int M = Integer.parseInt(st.nextToken()); // 간선의 갯수

        // 입력
        List<Integer>[] lines = input(N, st);

        // 오름차순으로 정렬
        for (int i = 1; i < N + 1; i++) {
            if ( lines[i] != null ) {
                Collections.sort(lines[i]);
            }
        }

        int result = 0;

        for (int i = 1; i < N + 1; i++) {
            List<Integer> lineInfo = lines[i];
            if (lineInfo != null) {
                for (int node : lineInfo) {
                    for (int j = i + 1; j < N + 1; j++) {
                        List<Integer> searchLine = lines[j];
                        if (searchLine != null) {
                            result +=  binarySearch(node, searchLine);
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static List<Integer>[] input(int lineCount, StringTokenizer st) throws IOException {
        List<Integer>[] lines = new ArrayList[lineCount + 1];

        for (int i = 0; i < lineCount + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());
            if (lines[leftNode] == null) {
                lines[leftNode] = new ArrayList<>();
            }
            lines[leftNode].add(rightNode);
        }
        return lines;
    }

    private static int binarySearch(int target, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            mid = (left + right) / 2;
            if (target == list.get(mid)) {
                 break;
            }
            else if (target > list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("target : " + target);
        System.out.println("list : " + list + " | index : " + mid);

        if (list.get(mid) == target) {
            return mid;
        }

        return mid + 1;
    }
}
/*
5 6
1 5
2 2
3 2
4 3
5 1
5 3

5 6
1 1
1 2
2 2
2 3
2 4
2 5
 */
