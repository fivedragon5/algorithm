package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13913
 *
 * 제한)
 *  첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *  0 <= N, K <= 100,000
 *
 *
 * 문제)
 *  1. 수빈이는 동생과 숨바꼭질 하고 있다.
 *  2. 수빈이는 점N, 동생은 점 K에 있다.
 *  3. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 *  4. 순간이동을 하는 경우에는 1초후에 2*X의 위치로 이동하게 된다.
 *  5. 수빈,동생 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후 인지 구하기
 *
 * 풀이)
 *  BFS
 *  time[point] = 수빈이가 point에 도착했을 때 지난 시간
 *  trace[point] = 수빈이가 point에 도착 했을때, 이전 지점(point)
 *  1. queue 에 수빈이가 이동할 수 있는 지점을 add해준다 (+1, -1, *2)
 *  2. 처음 도착한 지점일 경우 (time[current] 로 판단)
 *      time[point] = 시간 갱신
 *      trace[point] = 이전 지점 갱신
 *  3. 이미 도착한 지점일 경우, CONTINUE
 *  4. 이동한 지점이 K일 경우 반복문 종료
 *  5. track[K] 부터 역추적을 통해 track[point] = N 지점을 찾는다.
 *   - 찾을때 Stack에 넣어 반대로 꺼낼 수 있도록 함
 *  6. Stack 길이 = 수빈이가 N 지점에서 K 지점까지 이동하는데에 걸린 시간
 *   - Stack K ~ N 까지 이동 경로
 *
 */
class Problem13913 {

    private static int N, K;
    public static void main(String args[]) throws IOException {
        input();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        int[] time = new int[100001];
        int[] trace = new int[100001];
        int now = 1;
        time[N] = now++;
        int movePoint;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            movePoint = current;
            if (current == K) {
                break;
            }
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    movePoint = current + 1;
                }
                else if (i == 1) {
                    movePoint = current - 1;
                } else {
                    movePoint = current * 2;
                }

                if (movePoint < 0 || movePoint > 100000) continue;

                if (time[movePoint] == 0) {
                    queue.add(movePoint);
                    time[movePoint] = now;
                    trace[movePoint] = current;
                }
                now++;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int parent = K;
        while (parent != N) {
            stack.push(parent);
            parent = trace[parent];
        }
        stack.push(N);

        StringBuilder sb = new StringBuilder();
        System.out.println(stack.size() - 1);
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
}
/*
5 17

 */

