package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/letter-tile-possibilities/description/?envType=daily-question&envId=2025-02-17
 *
 * 제한)
 *  1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 *
 * 문제)
 *  1. n 개의 타일이 있으며, 각 타일에는 하나의 문자 tiles[i]가 적혀 있습니다.
 *  2. 이 타일에 적힌 문자들을 사용하여 만들 수 있는 비어 있지 않은 모든 문자열의 개수를 반환하시오.
 *
 * 풀이)
 *  1. 주어진 tiles 단어를 순회하면서 DFS
 *  2. 문자열이 완성될때마다 Set에 저장
 *   - 중복 제거
 *  3. Set Size 반환
 */

public class Question_20250217 {
    public static void main(String args[]) throws IOException {
        String tiles = "AAB";
        System.out.println(numTilePossibilities(tiles));

//        tiles = "AAABBC";
//        System.out.println(numTilePossibilities(tiles));
//
//        tiles = "V";
//        System.out.println(numTilePossibilities(tiles));
    }
    public static int numTilePossibilities(String tiles) {
        Set<String> result = new HashSet<>();

        for (int i = 1 ; i <= tiles.length(); i++) {
            boolean[] visited = new boolean[tiles.length()];
            dfs(result, tiles, i, "", visited);
        }
        return result.size();
    }

    private static void dfs(Set<String> result, String tiles, int targetLength, String current, boolean[] visited) {
        if (targetLength == current.length()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < tiles.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(result, tiles, targetLength, current + tiles.charAt(i), visited);
                visited[i] = false;
            }
        }
    }
}
