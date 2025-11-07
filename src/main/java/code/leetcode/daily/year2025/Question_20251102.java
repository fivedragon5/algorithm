package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/?envType=daily-question&envId=2025-11-02
 *
 * 제한)
 *  1 <= m, n <= 10^5
 *  2 <= m * n <= 10^5
 *  1 <= guards.length, walls.length <= 5 * 10^4
 *  2 <= guards.length + walls.length <= m * n
 *  guards[i].length == walls[j].length == 2
 *  0 <= rowi, rowj < m
 *  0 <= coli, colj < n
 *  All the positions in guards and walls are unique.
 *
 * 문제)
 *  1. m x n 그리드가 주어진다.
 *  2. guards와 walls라는 2차원 배열이 주어진다.
 *  3. guards[i] = [rowi, coli]는 (rowi, coli) 위치에 경비원이 있음을 나타낸다.
 *  4. walls[j] = [rowj, colj]는 (rowj, colj) 위치에 벽이 있음을 나타낸다.
 *  5. 경비원은 상하좌우로 감시를 하고, 벽에 의해 감시가 차단된다.
 *  6. 이때, 감시를 받지 않는 그리드의 수를 반환한다.
 *
 * 풀이)
 *  방식1) 경비원 기준 dfs를 통해 감시 영역 마킹 -> 시간 초과
 *  방식2) 행, 열 단위 스윕을 통해 감시 영역 마킹 -> O(m*n)
 *   - 행 단위로 좌->우, 우->좌 스윕
 *   - 열 단위로 상->하, 하->상 스윕
 *   - 각 스윕에서 경비원을 만나면 감시 시작, 벽을 만나면 감시 종료
 *   - 감시 중인 그리드는 감시 영역으로 마킹
 *   - 최종적으로 경비원, 벽, 감시 영역이 아닌 그리드 수 반환
 *
 */

public class Question_20251102 {
    public static void main(String args[]) throws IOException {
        int m = 4;
        int n = 6;
        int[][] guards = {{0,0},{1,1},{2,3}};
        int[][] walls = {{0,1},{2,2},{1,4}};
        System.out.println(countUnguarded(m, n, guards, walls));
    }

    private static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        Set<Long> guardSet = new HashSet<>();
        Set<Long> wallSet = new HashSet<>();
        for (int[] g : guards) guardSet.add(((long) g[0]) * n + g[1]);
        for (int[] w : walls) wallSet.add(((long) w[0]) * n + w[1]);
        boolean[] guarded = new boolean[m * n];

        // Row-wise sweep
        for (int i = 0; i < m; i++) {
            boolean seen = false;
            for (int j = 0; j < n; j++) {
                long idx = (long)i * n + j;
                if (wallSet.contains(idx)) seen = false;
                else if (guardSet.contains(idx)) seen = true;
                else if (seen) guarded[(int)idx] = true;
            }
            seen = false;
            for (int j = n - 1; j >= 0; j--) {
                long idx = (long)i * n + j;
                if (wallSet.contains(idx)) seen = false;
                else if (guardSet.contains(idx)) seen = true;
                else if (seen) guarded[(int)idx] = true;
            }
        }

        // Col-wise sweep
        for (int j = 0; j < n; j++) {
            boolean seen = false;
            for (int i = 0; i < m; i++) {
                long idx = (long)i * n + j;
                if (wallSet.contains(idx)) seen = false;
                else if (guardSet.contains(idx)) seen = true;
                else if (seen) guarded[(int)idx] = true;
            }
            seen = false;
            for (int i = m - 1; i >= 0; i--) {
                long idx = (long)i * n + j;
                if (wallSet.contains(idx)) seen = false;
                else if (guardSet.contains(idx)) seen = true;
                else if (seen) guarded[(int)idx] = true;
            }
        }
        int unguardedCount = 0;
        for (int i = 0; i < m * n; i++) {
            if (!guardSet.contains((long) i) && !wallSet.contains((long) i) && !guarded[i]) {
                unguardedCount++;
            }
        }
        return unguardedCount;
    }
}
