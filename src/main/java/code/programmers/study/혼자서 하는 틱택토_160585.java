package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/160585
 *
 * 제한)
 *  board의 길이 = board[i]의 길이 = 3
 *      board의 원소는 모두 "O", "X", "."으로만 이루어져 있습니다.
 *  board[i][j]는 i + 1행 j + 1열에 해당하는 칸의 상태를 나타냅니다.
 *      "."은 빈칸을, "O"와 "X"는 해당 문자로 칸이 표시되어 있다는 의미입니다.
 *
 * 문제)
 *  1. 틱택토는 두 사람이 하는 게임으로 3x3의 빈칸으로 이루어진 게임판에 선공이 "O", 후공이 "X"를 번갈아 가면서 빈칸에 표시하는 게임
 *  2. 가로,세로,대각선 으로 3개가 같은 표시가 만들어지면 같은 표시를 만든 사람이 승리하고 게임이 종료
 *  3. 9칸이 모두 차서 더 이상 표시를 할 수 없는 경우엔 무승부로 표시
 *  4. 혼자서 이 게임을 진행했을 때 이 게임판이 규칙을 지켜서 틱택토를 진행 했을 때 나올 수 있는 게임 상황이면 1 아니면 0을 반환
 *
 * 풀이)
 *  구현
 *      1. 3x3 보드를 1차원 배열로 변환
 *      2. O와 X의 개수를 세고, 완성된 경우를 확인
 *      3. 완성된 경우에 따라 규칙을 지키는지 확인
 *
 */
class Lesson160585 {
    public static void main(String[] args) {
        String[] board = {"O.X", ".O.", "..X"};
        System.out.println(solution(board));

        String[] board2 = {"OOO", "...", "XXX"};
        System.out.println(solution(board2));

        String[] board3 = {"...", ".X.", "..."};
        System.out.println(solution(board3));

        String[] board4 = {"...", "...", "..."};
        System.out.println(solution(board4));
    }

    private final static int[][] COMPLETION_LIST = new int[][]{
             {0,1,2},{3,4,5},{6,7,8} // 가로
            ,{0,3,6},{1,4,7},{2,5,8} // 세로
            ,{0,4,8},{2,4,6} // 대각선
    };

    public static int solution(String[] board) {
        char[] map = new char[9]; // 3x3 틱택토 보드
        int oCount = 0; // O의 개수
        int xCount = 0; // X의 개수
        for (int i = 0; i < 9; i++) {
            char c = board[i / 3].charAt(i % 3);
            map[i] = c;
            if (c == 'O') {
                oCount++;
            } else if (c == 'X') {
                xCount++;
            }
        }

        boolean isOCompletion = isCompletion(map, 'O');
        boolean isXCompletion = isCompletion(map, 'X');

        // O와 X가 동시에 완성된 경우
        if (isOCompletion && isXCompletion) {
            return 0;
        } else if (isOCompletion) {
            return (oCount == xCount + 1) ? 1 : 0;
        } else if (isXCompletion) {
            return (oCount == xCount) ? 1 : 0;
        } else if (oCount == xCount || oCount == xCount + 1) {
            return 1;
        } else {
            return 0; // O와 X의 개수가 올바르지 않은 경우
        }
    }

    private static boolean isCompletion(char[] map, char player) {
        for (int[] COMPLETION : COMPLETION_LIST) {
            if (map[COMPLETION[0]] == player && map[COMPLETION[1]] == player && map[COMPLETION[2]] == player) {
                return true;
            }
        }
        return false;
    }
}
