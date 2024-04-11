package code.programmers.study;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 접근)
 *  1. BFS
 *      - 시작 -> 레버 -> 도착 순으로 각각의 단계에서 넘어갈때 BFS 사용 (총 2번사용)
 *  
 * 풀이)
 *  1. 문자로된 1차원 배열을 2차원 배열로 만듬 maze[][]
 *      - 만들면서 시작(S), 레버(L), 도착(E) 지점의 좌표를 기억
 *  2. 저장한 시작지점부터 레버지점 까지 BFS 탐색
 *      - 방문배열(visited) 초기화
 *  3. 레버지점부터 도착지점까지 BFS 탐색
 *  4. 각각의 지점들을 더해서 return
 *      - 하나라도 0이 나온다면 -1을 return
 *  
 * 제한)
 *  5 ≤ maps.length ≤ 100
 *  5 ≤ maps[i].length ≤ 100
 */
class Lesson159993 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static String[][] maze;
    static boolean[][] visited;
    static boolean[][] iniVisited;
    static int maxX;
    static int maxY;
    static int startX = 0;
    static int startY = 0;
    static int leverX = 0;
    static int leverY = 0;
    static int endX = 0;
    static int endY = 0;
    static int sec = 0;
    static int answer = 0;

    static int solution(String[] maps) {
        setMaze(maps);

        visited[startY][startX] = true;
        bfs(startX, startY, leverX, leverY);
        if (sec == 0) return -1;

        answer = sec;
        sec = 0;
        visited = iniVisited;
        visited[leverY][leverX] = true;
        bfs(leverX, leverY, endX, endY);

        if (sec == 0) return -1;
        return answer + sec;
    }

    static void bfs(int startX, int startY, int targetX, int targetY) {
        int[] start = new int[]{startY, startX, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[1];
            int y = current[0];
            if (x == targetX && y == targetY) {
                sec = current[2];
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= maxX || nextY >= maxY) {
                    continue;
                }
                else if (maze[nextY][nextX].equals("X")) {
                    continue;
                }
                else if (!visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    queue.add(new int[]{nextY, nextX, current[2] + 1});
                }
            }
        }

    }

    static void setMaze(String[] maps) {
        maxX = maps[0].length();
        maxY = maps.length;
        maze = new String[maxY][maxX];
        visited = new boolean[maxY][maxX];
        iniVisited = new boolean[maxY][maxX];

        boolean isFindStart = false;
        boolean isFindLever = false;
        boolean isFindEnd = false;

        for (int i = 0; i < maxY; i++) {
            maze[i] = maps[i].split("");
            if (!isFindStart && maps[i].indexOf("S") > -1) {
                isFindStart = true;
                startY = i;
                startX = maps[i].indexOf("S");
            }
            if (!isFindLever && maps[i].indexOf("L") > -1) {
                isFindLever = true;
                leverY = i;
                leverX = maps[i].indexOf("L");
            }
            if (!isFindEnd && maps[i].indexOf("E") > -1) {
                isFindEnd = true;
                endY = i;
                endX = maps[i].indexOf("E");
            }
        }
    }

    public static void main(String[] args) {
//        String[] map2 = {"SOOOO","XXXXX","LOOOO","XXXXX","EOOOO"};
//        System.out.println(solution(map2));
//
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
//
//        String[] maps2 = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
//        System.out.println(solution(maps2));
    }
}
