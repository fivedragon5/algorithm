package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12886
 *
 * 제한)
 *  1 ≤ A, B, C ≤ 500
 *
 * 문제)
 *  1. 돌은 3개의 그룹으로 나누어져 있으며 각각 돌이 A,B,C개 있다.
 *  2. 모든 그룹에 있는 돌의 개수를 같게 만드려고 한다.
 *  3. 각 돌은 단계별로 움직일 수 있다.
 *   - 크기가 같지 않은 두 그룹을 선택한다.
 *   - 돌의 개수가 큰 쪽을 X, 작은 쪽을 Y라고 했을 때, X에 있는 돌의 갯수를 X+X, Y에 있는 돌의 갯수를 Y-X개로 만든다.
 *  4. 이렇게 해서 돌을 같은 개수로 만들 수 있으면 1, 없으면 0 출력하기
 *
 * 풀이)
 *  BFS
 *  - 3개의 그룹을 오름차순으로 정렬해서 큐에 넣는다.
 *  - 큐에서 꺼낸 그룹을 기준으로
 *  1. 2개의 그룹을 선택해서 X,Y로 나눈다.
 *  2. X,Y를 기준으로 새로운 그룹을 만들어서 큐에 넣는다.
 *  3. 큐에 넣을때는 방문한 그룹인지 체크한다. (오름차순으로 정렬한 돌을 넣기)
 *   - 방문한 그룹확인 3차원 배열이아닌 2차원 배열을 사용한 이유
 *    - 3차원 배열을 사용하면 메모리 초과 발생
 *    - 식을 보면 X+X, Y-X로 전체 돌 갯수는 변하지 않기에 2차원 배열로 구현 가능
 *
 */
class Problem12886 {
    private static int A;
    private static int B;
    private static int C;

    public static void main(String args[]) throws IOException {
        input();
        boolean[][] visited = new boolean[1001][1001];
        Queue<int[]> queue = new LinkedList<>();
        // 오름차순 정렬
        int[] arr = sortAsc(A, B, C);
        queue.add(arr);
        visited[arr[0]][arr[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int min = current[0];
            int mid = current[1];
            int max = current[2];

            if (min == max) {
                System.out.println(1);
                return;
            }

            if (min != mid) {
                int[] array = sortAsc(min + min, mid - min, max);
                if (!visited[array[0]][array[1]]) {
                    queue.add(array);
                    visited[array[0]][array[1]] = true;
                }
            }

            if (mid != max) {
                int[] array = sortAsc(min, mid + mid, max - mid);
                if (!visited[array[0]][array[1]]) {
                    queue.add(array);
                    visited[array[0]][array[1]] = true;
                }
            }

            int[] array = sortAsc(min + min, mid, max - min);
            if (!visited[array[0]][array[1]]) {
                queue.add(array);
                visited[array[0]][array[1]] = true;
            }
        }
        System.out.println(0);
        return;
    }

    private static int[] sortAsc(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }
}
/*
10 15 35
1

1 1 2
0

498 499 500
 */

