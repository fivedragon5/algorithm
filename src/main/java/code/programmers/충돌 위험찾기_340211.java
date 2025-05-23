package code.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/340211
 *
 * 제한)
 *  2 ≤ points의 길이 = n ≤ 100
 *      points[i]는 i + 1번 포인트의 [r 좌표, c 좌표]를 나타내는 길이가 2인 정수 배열입니다.
 *      1 ≤ r ≤ 100
 *      1 ≤ c ≤ 100
 *      같은 좌표에 여러 포인트가 존재하는 입력은 주어지지 않습니다.
 *  2 ≤ routes의 길이 = 로봇의 수 = x ≤ 100
 *      2 ≤ routes[i]의 길이 = m ≤ 100
 *      routes[i]는 i + 1번째 로봇의 운송경로를 나타냅니다. routes[i]의 길이는 모두 같습니다.
 *      routes[i][j]는 i + 1번째 로봇이 j + 1번째로 방문하는 포인트 번호를 나타냅니다.
 *      같은 포인트를 연속으로 방문하는 입력은 주어지지 않습니다.
 *      1 ≤ routes[i][j] ≤ n
 *
 * 문제)
 *  1. 물류 센터에는 (r, c)와 같이 2차원 좌표로 나타낼 수 있는 n개의 포인트가 존재합니다. 각 포인트는 1~n까지의 서로 다른 번호를 가집니다.
 *  2. 로봇마다 정해진 운송 경로가 존재합니다. 운송 경로는 m개의 포인트로 구성되고 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문합니다.
 *  3. 운송 시스템에 사용되는 로봇은 x대이고, 모든 로봇은 0초에 동시에 출발합니다.
 *     로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1만큼 감소하거나 증가한 좌표로 이동할 수 있습니다.
 *  4. 다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우,
 *     r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
 *  5. 마지막 포인트에 도착한 로봇은 운송을 마치고 물류 센터를 벗어납니다.
 *     로봇이 물류 센터를 벗어나는 경로는 고려하지 않습니다.
 *  6. 같은 시간에 같은 좌표에 로봇이 있을 경우 충돌위험이 발생할 수 있다.
 *  7. 충돌 위험이 발생할 수 있는 포인트의 수를 구하기
 *
 * 풀이)
 *  구현
 *   1. 1초마다 로봇들을 한칸씩 움직인다.
 *    - Map에 좌표, 로봇의 수 저장
 *   2. 움직인 로못들에 대해 충돌하는 칸이 있는지 확인
 *   3. Map을 확인해서 특정 칸에 로봇이 2개이상 있을 경우 충돌 카운트를 올려준다.
 *
 *   - 최종 목적지에 도착한 로봇은 완료 처리를 해서 불필요한 동작을 줄였다
 *   - Map에 좌표값을 저장할때 x,y값을 String으로 변환해서 저장
 *   - 로봇각각의 값의 저장의 편의성을 위해 Robot Class를 만들어서 진행했다.
 */
class Lesson389479 {
    public static void main(String[] args) {
        int[][] points5 = {{1, 1}, {1, 3}, {1, 4}, {3, 3}};
        int[][] routes5 = {{1, 3}, {3, 2}, {4, 2}};
        System.out.println(solution(points5, routes5));

        int[][] points4 = {{1, 1}, {1, 3}};
        int[][] routes4 = {{1, 2, 1, 2},{2, 1, 2, 1}};
        System.out.println(solution(points4, routes4));

        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        System.out.println(solution(points, routes));

        int[][] points2 = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes2 = {{2, 3, 4, 5},{1, 3, 4, 5}};
        System.out.println(solution(points2, routes2));

        int[][] points3 = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes3 = {{4, 2}, {1, 3}, {4, 2}, {4, 3}};
        System.out.println(solution(points3, routes3));
    }

    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int N = routes.length;
        int[][] map = new int[101][101];
        int processCount = N;
        boolean[] completed = new boolean[N];

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], -1);
        }

        List<Robot> robots = new LinkedList<>();
        Map<String, Integer> check = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] startPoint = points[routes[i][0] - 1];
            String key = startPoint[0] + "," + startPoint[1];
            robots.add(new Robot(new int[]{startPoint[0], startPoint[1]}, 1, routes[i]));
            check.put(key, check.getOrDefault(key, 0) + 1);
        }
        answer += collisionCount(check);

        while (processCount > 0) {
            check.clear();
            for (int i = 0; i < N; i++) {
                if (!completed[i]) {
                    Robot currentRobot = robots.get(i);
                    int[] destination = points[currentRobot.destinationPointList[currentRobot.currentDestination]];
                    setNextPoint(currentRobot.point, destination);
                    int moveX = currentRobot.point[0];
                    int moveY = currentRobot.point[1];
                    if (moveX == destination[0] && moveY == destination[1]) {
                        currentRobot.currentDestination++;
                        if (currentRobot.currentDestination >= currentRobot.destinationPointList.length) {
                            completed[i] = true;
                            processCount--;
                        }
                    }
                    String key = moveX + "," + moveY;
                    check.put(key, check.getOrDefault(key, 0) + 1);
                }
            }
            answer += collisionCount(check);
        }
        return answer;
    }

    private static int collisionCount(Map<String, Integer> map) {
        int count = 0;
        for (int value : map.values()) {
            if (value >= 2) {
                count++;
            }
        }
        return count;
    }

    private static void setNextPoint(int[] point, int[] destination) {
        int x1 = point[0];
        int y1 = point[1];
        int x2 = destination[0];
        int y2 = destination[1];
        int nextX = x1;
        int nextY = y1;
        // y값만 변경
        if (x1 == x2) {
            if (y1 < y2) nextY++;
            else nextY--;
        }
        // x값만 변경
        else {
            if (x1 < x2) nextX++;
            else nextX--;
        }
        point[0] = nextX;
        point[1] = nextY;
    }

    static class Robot {
        int[] point;
        int currentDestination;
        int[] destinationPointList;

        public Robot(int[] point, int currentDestination, int[] destinationPointList) {
            this.point = point;
            this.currentDestination = currentDestination;
            this.destinationPointList = new int[destinationPointList.length];
            for (int i = 0; i < destinationPointList.length; i++) {
                this.destinationPointList[i] = destinationPointList[i] - 1;
            }
        }
    }
}
