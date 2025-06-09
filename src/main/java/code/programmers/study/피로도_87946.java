package code.programmers.study;

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
 *  DFS, 백트래킹
 *   - 던전의 최대 수 가 8이므로, 모든 경우의 수를 탐색하기 충분
 *  1. 던전을 돌 수 있는 모든 경우의 수를 계산
 *  2. 매번 최대값을 갱신
 *
 */
class Lesson87946 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = new int[][]{{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));
    }

    private static int MAX_COUNT = 0;

    public static int solution(int k, int[][] dungeons) {
        backTracking(k, dungeons, new boolean[dungeons.length], 0);
        return MAX_COUNT;
    }

    private static void backTracking(int k, int[][] dungeons, boolean[] visited, int count) {
        MAX_COUNT = Math.max(MAX_COUNT, count);

        for (int i = 0; i < dungeons.length; i++) {
            int[] dungeon = dungeons[i];
            if (!visited[i] && dungeon[0] <= k) {
                visited[i] = true; // 던전 방문 처리
                backTracking(k - dungeon[1], dungeons, visited, count + 1); // 소모 피로도를 빼고 재귀 호출
                visited[i] = false; // 던전 방문 취소
            }
        }
    }
}
