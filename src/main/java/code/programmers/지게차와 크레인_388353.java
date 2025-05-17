package code.programmers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/388353
 *
 * 제한)
 *  2 ≤ storage의 길이 = n ≤ 50
 *  2 ≤ storage[i]의 길이 = m ≤ 50
 *  storage[i][j]는 위에서 부터 i + 1번째 행 j + 1번째 열에 놓인 컨테이너의 종류를 의미합니다.
 *  storage[i][j]는 알파벳 대문자입니다.
 *  1 ≤ requests의 길이 ≤ 100
 *  1 ≤ requests[i]의 길이 ≤ 2
 *  requests[i]는 한 종류의 알파벳 대문자로 구성된 문자열입니다.
 *  requests[i]의 길이가 1이면
 *
 * 문제)
 *  1. 회사 물류창고에는 알파벳 대문자로 종류를 구분하는 컨테이너가 세로로 n 줄, 가로로 m줄 총 n x m개 놓여 있다.
 *  2. requests에는 요청이 들어온다.
 *  3. requests[i]는 한 종류의 알파벳 대문자로 구성된 문자열이다.
 *  4. requests[i]의 길이가 1이면 외부와 연결된 컨테이너만 꺼낼 수 있다.
 *  5. requests[i]의 길이가 2이면 모든 컨테이너를 꺼낼 수 있다.
 *  6. 모든 요청을 완료하고 남은 컨테이너 수를 반환하기
 *
 * 풀이)
 *  구현 + DFS
 *   문자 하나하나 비교를 편하게 하기 위해 String[] storage 데이터 타입을 String[][] 로 변경해서 풀이
 *  1. requests 순회
 *   - request의 문자가 1개, 2개 일때를 분리 해서 컨테이너 맵을 최신화 (꺼낸 컨테이너는 "."으로 변경)
 *   - 외부와 연결된 컨테이너 자리는 "0"으로 변경
 *  2. 1번 사항을 반복해서 컨테이너를 꺼낼때 마다 꺼낸 컨테이너 수를 저장후 출력
 *
 */
class Lesson388353 {
    public static void main(String[] args) {
        String[] storage = new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests = new String[]{"A", "BB", "A"};
        System.out.println(solution(storage, requests));

        String[] storage2 = new String[]{"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] requests2 = new String[]{"C", "B", "B", "B", "B", "H"};
        System.out.println(solution(storage2, requests2));

        return;
    }

    private static int ROW;
    private static int COL;
    private static int dx[] = new int[]{1,0,-1,0};
    private static int dy[] = new int[]{0,1,0,-1};

    public static int solution(String[] storage, String[] requests) {
        ROW = storage.length;
        COL = storage[0].length();
        String[][] container = new String[ROW][COL];
        int containerCount = ROW * COL;
        for (int i = 0; i < ROW; i++) {
            container[i] = storage[i].split("");
        }
        for (String request : requests) {
            if (request.length() == 2) {
                containerCount -= pullDouble(container, request.substring(1));
            } else {
                containerCount -= pull(container, request);
            }
            updateExternalLink(container);
        }
        return containerCount;
    }

    private static int pullDouble(String[][] container, String alphabet) {
        int pullCount = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (container[i][j].equals(alphabet)) {
                    pullCount++;
                    container[i][j] = ".";
                }
            }
        }
        return pullCount;
    }

    private static int pull(String[][] container, String alphabet) {
        int pullCount = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (container[i][j].equals(alphabet)) {
                    if (checkContainer(container, i, j)) {
                        pullCount++;
                    }
                }
            }
        }
        return pullCount;
    }

    private static boolean checkContainer(String[][] container, int x, int y) {
        for (int k = 0; k < 4; k++) {
            int moveX = x + dx[k];
            int moveY = y + dy[k];
            if (moveX < 0 || moveX >= ROW || moveY < 0 || moveY >= COL || container[moveX][moveY].equals("0")) {
                container[x][y] = ".";
                return true;
            }
        }
        return false;
    }

    private static void updateExternalLink(String[][] container) {
        boolean[][] visited = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (container[i][j].equals(".")) {
                    visited[i][j] = true;
                    dfs(container, visited, i, j);
                }
            }
        }
    }

    private static void dfs(String[][] container, boolean[][] visited, int x, int y) {
        List<int[]> updateList = new LinkedList<>();
        boolean result = false;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        updateList.add(queue.peek());
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int queueX = poll[0];
            int queueY = poll[1];
            for (int k = 0; k < 4; k++) {
                int moveX = queueX + dx[k];
                int moveY = queueY + dy[k];
                if (moveX < 0 || moveX >= ROW || moveY < 0 || moveY >= COL || container[moveX][moveY].equals("0")) {
                    result = true;
                } else if (container[moveX][moveY].equals(".") && !visited[moveX][moveY]) {
                    visited[moveX][moveY] = true;
                    queue.add(new int[]{moveX, moveY});
                    updateList.add(queue.peek());
                }
            }
        }
        if (result) {
            for (int[] point : updateList) {
                container[point[0]][point[1]] = "0";
            }
        }
    }
}
