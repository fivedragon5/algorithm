package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/389479
 *
 * 제한)
 *  players의 길이 = 24
 *      0 ≤ players의 원소 ≤ 1,000
 *      players[i]는 i시 ~ i+1시 사이의 게임 이용자의 수를 나타냅니다.
 *  1 ≤ m ≤ 1,000
 *  1 ≤ k ≤ 24
 *
 * 문제)
 *  1. 게임 이용자 수가 m명당 서버1대가 필요합니다.
 *  2. 서버를 증설 했을 경우 k시간 이후에는 서버를 반납 해야 합니다.
 *  3. 각 시간대별 players배열이 주어졌을 경우 하루동안 서버를 최소 몇대 증설해야 하는지 구하기
 *
 * 풀이)
 *  O(24) 구현 문제
 *   1. 0시 ~ 23시 까지 탐색
 *   2. 현재 서버의 수와 player 수에 따른 필요 서버수를 차이를 구한다.
 *   3. 현재 서버수가 부족할 경우 현재 서버의 수를 증가 시켜준다.
 *    - decreaseServerCount[i] 배열에 서버를 증설한 시간(i + k)에 서버를 증설한 수를 넣어준다.
 *    - 수를 넣어주면서 증설한 서버 수를 increaseServerCount에 누적해서 더해준다
 *   4. 현재 서버수를 조회할 때 decreaseServerCount[i] 만큼 빼주면서 서버 수를 비교한다.
 */
class Lesson389479 {
    public static void main(String[] args) {
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        System.out.println(solution(players, m, k));

        int[] players2 = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
        int m2 = 5;
        int k2 = 1;
        System.out.println(solution(players2, m2, k2));
    }

    public static int solution(int[] players, int m, int k) {
        int increaseServerCount = 0;
        int currentServerCount = 0;
        int[] decreaseServerCount = new int[24];
        decreaseServerCount[0] = 0;

        for (int i = 0 ; i < 24; i++) {
            // 현재 시간대에 필요한 서버의 수
            int currentPlayer = players[i];
            int requiredServerCount = currentPlayer / m;
            currentServerCount -= decreaseServerCount[i];
            if (currentServerCount < requiredServerCount) {
                int count = requiredServerCount - currentServerCount;
                increaseServerCount += count;
                currentServerCount += count;
                if (i + k < 24) {
                    decreaseServerCount[i+k] = count;
                }
            }
        }
        return increaseServerCount;
    }
}
