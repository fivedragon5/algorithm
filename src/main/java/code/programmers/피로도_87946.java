package code.programmers;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 *
 * 제한)
 *  k는 1 이상 5,000 이하인 자연수입니다.
 *  dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
 *      dungeons의 가로(열) 길이는 2 입니다.
 *      dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
 *      "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
 *      "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
 *      서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.
 *
 * 문제)
 *  1. 유저의 현재 피로도 k와, 각 던전의 최소 필요 피로도와 소모 피로도가 주어진다.
 *  2. 각 던전당 하루에 한번 탐험할 수 있다고 할 때, 한 유저가 오늘 최대로 돌 수 있는 던전의 수 를 구하기
 *
 * 풀이)
 *
 *
 */
class Lesson87946 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = new int[][]{{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));
    }

    private static int MAX = 0;

    public static int solution(int k, int[][] dungeons) {
        Arrays.sort(dungeons, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[0], b[0]); // 오름차순
            }
            return Integer.compare(b[0], a[0]); // 내림차순
        });

        bfs(k, dungeons, 0, 0);

        return MAX;
    }

    private static void bfs(int currentK, int[][] dungeons, int currentDungeonCount, int dungeonIndex) {
        if (dungeonIndex >= dungeons.length) {
            MAX = Math.max(MAX, currentDungeonCount);
            return;
        }
        int[] currentDungeon = dungeons[dungeonIndex];

        // 현재 던전을 돌지 않을 경우
        bfs(currentK, dungeons, currentDungeonCount, dungeonIndex + 1);

        // 현재 던전을 돌 경우
        if (currentK >= currentDungeon[0]) {
            bfs(currentK - currentDungeon[1], dungeons, currentDungeonCount + 1, dungeonIndex + 1);
        }
    }
}
