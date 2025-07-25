package code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2110
 *
 * 제한)
 *  2 ≤ N ≤ 200,000
 *  2 ≤ C ≤ N
 *  0 ≤ xi ≤ 1,000,000,000
 *
 * 문제)
 *  1. 도현이의 집 N개가 수직선 위에 있다.
 *  2. 각각의 집 좌표는 x1, x2, ..., xN으로 주어진다 (집 여러개가 같은 좌표일 순 없음)
 *  3. 도현이는 C개의 공유기를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는
 *     하나의 공유기를 하나만 설치할 수 있다.
 *  4. 가장 인접한 두 공유기 사이의 거리를 가능한 최대로 하려고 한다.
 *  5. C개의 공유기를 설치했을 때, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 값을 구하시오.
 *
 * 풀이)
 * 이분탐색
 *  1. 집 좌표를 정렬 (이분탐색)
 *  2. 최소 거리와 최대 거리를 설정
 *  3. 이분탐색을 통해 가능한 최대 거리를 찾는다.
 *  4. 현재 거리(mid)를 기준으로 공유기를 설치할 수 있는지 확인하는 함수를 만든다.
 *   - 설치할 수 있다면, 거리를 늘려서 다시 확인
 *   - 설치할 수 없다면 거리를 줄여서 확인
 */
class Problem2110 {

    private static int N, C;
    private static int[] HOUSES;

    public static void main(String args[]) throws IOException {
        input();

        Arrays.sort(HOUSES); // 이분탐색을 위한 정렬 진행

        int left = 1; // 최소 거리
        int right = HOUSES[N - 1] - HOUSES[0]; // 최대 거리
        while (left <= right) {
            int mid = (left + right) / 2; // 현재 거리
            if (canInstall(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    private static boolean canInstall(int distance) {
        int count = 1; // 첫 번째 집에 공유기 설치
        int lastPosition = HOUSES[0]; // 마지막 설치 위치
        for (int i = 1; i < N; i++) {
            if (HOUSES[i] - lastPosition >= distance) {
                count++;
                lastPosition = HOUSES[i];
            }
        }
        return count >= C; // C개 이상의 공유기를 설치할 수 있는지 확인
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        HOUSES = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            HOUSES[i] = Integer.parseInt(st.nextToken());

        }
    }
}
/*
5 5
1
2
8
4
9

3

 */
