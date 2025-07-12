package code.programmers.study;

import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/17679
 *
 * 제한)
 *  입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
 *  2 ≦ n, m ≦ 30
 *  board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.
 *
 * 문제)
 *  1. 같은 모양의 카카오프렌즈 블록이 2x2로 모여 있으면, 해당 블록을 제거하면서 점수를 얻는다.
 *  2. 같은 블록은 여러 2x2에 포함될 수 있으며, 지워지는 조건에 만족하는 2x2 모양이 여러 개 있다면 한꺼번에 지워진다.
 *  3. 블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
 *  4. 각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)으로 이루어져 있다.
 *  5. 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하기
 *
 * 풀이)
 *  구현
 *  1. 2x2 같은 블럭을 탐색편의성을 위해 주어진 String[] board 를 String[][] 으로 변환
 *  2. 아래 순서에 맞게 게임 진행
 *   1. eraseBlock()
 *          - 2x2 같은 블럭을 찾아 시작 지점을 List<Integer[]> 에 담는다
 *          - 찾을때마다 지울 경우, 겹치는 블럭을 지우기 힘듬
 *          - ex) (0,1), (0,2), (1,1), (1,2) 가 같을 경우 가장 좌측 상단 시작점만 담는다.
 *   2. eraseBoards(List<Integer[]> list)
 *          - 지울 블럭의 시작 지점을 순회하면서 블럭들을 지워준다("." 으로 변경)
 *   3. 1번에서 블럭을 1개라도 지웠다면 블럭을 아래로 내리는 처리 필요 downBlock()
 *          - 블럭을 세로로 한줄씩 탐색하면서 아래로 내려준다. 메소드 참고
 *          - 주의할점) 빈 공간만큼 블럭을 내려주지만 중간에 블럭이 있고 공간이 또 있을 경우,
 *                    한번이라도 블럭을 옮겼으면 블럭이 더이상 움직이지 않을때 까지 반복 호출
 *   4. 더이상 블럭을 지울 수 없을 경우 빈칸을 카운트하여 반환
 */
class Lesson17679 {
    public static void main(String[] args) {
        int m = 4; int n = 5;
        String[] board = {
            "CCBDE",
            "AAADE",
            "AAABF",
            "CCBBF"
        };
        System.out.println(solution(m, n, board)); // 14

        int m2 = 6; int n2 = 6;
        String[] board2 = {
                "TTTANT",
                "RRFACC",
                "RRRFCC",
                "TRRRAA",
                "TTMMMF",
                "TMMTTJ"
        };
        System.out.println(solution(m2, n2, board2)); // 15
    }

    private static int HEIGHT, WIDTH; // M:높이, N:폭
    private static String[][] BOARDS; // 빈 칸 일경우 (.) 표시
    private static int[] dx = new int[]{1, 0, 1}; // 우, 하, 대각선 아래
    private static int[] dy = new int[]{0, 1, 1}; // 우, 하, 대각선 아래

    public static int solution(int m, int n, String[] board) {
        HEIGHT = m; WIDTH = n;
        BOARDS = new String[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            BOARDS[i] = board[i].split("");
        }
        while (!eraseBlock()) {
            downBlock();
        }
        return countEmpty();
    }

    private static boolean eraseBlock() {
        List<Integer[]> eraseStartPoint = new ArrayList<>();
        for (int i = 0; i < HEIGHT - 1; i++) {
            for (int j = 0; j < WIDTH - 1; j++) {
                String current = BOARDS[i][j];
                boolean isErase = true;
                if (current.equals(".")) {
                    continue;
                }
                for (int k = 0; k < 3; k++) {
                    int moveX = j + dx[k];
                    int moveY = i + dy[k];
                    if (!current.equals(BOARDS[moveY][moveX])) {
                        isErase = false;
                        break;
                    }
                }
                if (isErase) {
                    eraseStartPoint.add(new Integer[]{i, j});
                }
            }
        }
        eraseBoards(eraseStartPoint);
        return eraseStartPoint.isEmpty();
    }

    private static void eraseBoards(List<Integer[]> list) {
        for (Integer[] point : list) {
            int y = point[0]; int x = point[1];
            BOARDS[y][x] = ".";
            for (int i = 0; i < 3; i++) {
                BOARDS[y + dy[i]][x + dx[i]] = ".";
            }
        }
    }

    private static void downBlock() {
        boolean isDown = false;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = HEIGHT - 1; j >= 0; j--) {
                // 아래에서 부터 빈 공간이 있는지 확인
                if (BOARDS[j][i].equals(".")) {
                    int downCount = 1;
                    // 빈 공간이 있을 경우 빈 공간으로 부터 위에 빈 공간이 아닌 곳 찾기
                    for (int k = j - 1; k >= 0; k--) {
                        if (BOARDS[k][i].equals(".")) {
                            downCount++;
                        } else {
                            // 채워 넣기
                            isDown = true;
                            for (int l = j; l - downCount >= 0 ; l--) {
                                BOARDS[l][i] = BOARDS[l - downCount][i];
                                BOARDS[l - downCount][i] = ".";
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if (isDown) {
            downBlock();
        }
    }

    private static int countEmpty() {
        int count = 0;
        for (String[] b : BOARDS) {
            for (String block : b) {
                if (block.equals(".")) {
                    count++;
                }
            }
        }
        return count;
    }
}
