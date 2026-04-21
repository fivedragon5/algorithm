package code.leetcode.daily.year2026;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/description/?envType=daily-question&envId=2026-04-21
 *
 * 제한)
 *  n == source.length == target.length
 *  1 <= n <= 10^5
 *  1 <= source[i], target[i] <= 10^5
 *  0 <= allowedSwaps.length <= 10^5
 *  allowedSwaps[i].length == 2
 *  0 <= ai, bi <= n - 1
 *  ai != bi
 *
 * 문제)
 *  1. 두개의 정수 배열 source, target이 주어집니다.
 *  2. allowedSwaps 배열이 주어지는데,
 *     여기서 allowedSwaps[i] = [ai, bi]는 source 배열의 인덱스
 *     $a_i$와 $b_i$에 있는 요소를 서로 바꿀 수 있음을 의미합니다.
 *  3. 특정 인덱스 쌍에 대해서는 횟수 제한 없이, 어떤 순서로든 요소를 바꿀 수 있습니다.
 *  4. 두 배열 source와 target의 **해밍 거리(Hamming distance)**란,
 *     같은 위치에 있는 요소가 서로 다른 인덱스의 개수를 의미합니다.
 *     공식적으로는 $0 \le i \le n-1$ 범위에서 source[i] != target[i]를 만족하는 인덱스 $i$의 개수입니다.
 *  5. source 배열에 허용된 스왑 연산을 원하는 만큼 수행했을 때,
 *     source와 target 사이의 최소 해밍 거리를 구하여 반환하기
 *
 * 풀이)
 *  1. UnionFind
 *   - allowedSwaps를 돌며 서로 연결된 인덱스들을 하나의 집합으로 묶음
 *  2. 중첩 HashMap 구조
 *   - Map<Integer, Map<Integer, Integer>>: 바깥쪽 맵은 각 그룹의 루트(대표 인덱스)를 키로 가집니다.
 *                                          안쪽 맵은 해당 그룹 내에 존재하는 숫자와 그 개수를 저장합니다.
 *  3. target 배열의 각 원소가 자신이 속한 그룹(find(i))의 source 중에 있는지 확인
 *     - 있다면 matchingCount를 올리고 해당 숫자의 개수를 1 감소시킵니다.
 *     - 해당 숫자를 그 위치(또는 스왑 가능한 범위 내의 위치)에 배치하여 해밍 거리를 늘리지 않음
 *
 */

public class Question_20260421 {
    public static void main(String args[]) throws IOException {
        int[] source = {1,2,3,4};
        int[] target = {2,1,4,5};
        int[][] allowedSwaps = {{0,1}, {2,3}};
        minimumHammingDistance(source, target, allowedSwaps);
    }

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 1. 모든 스왑 가능 쌍을 union 연산으로 그룹화
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }

        // 2. 그룹별로 source에 있는 숫자들의 빈도수를 저장
        Map<Integer, Map<Integer, Integer>> groupCounts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groupCounts.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> countMap = groupCounts.get(root);
            countMap.put(source[i], countMap.getOrDefault(source[i], 0) + 1);
        }

        // 3. target을 순회하며 각 그룹 내에서 일치하는 숫자가 있는지 확인
        int matchingCount = 0;
        for (int i = 0; i < n; i++) {
            int root = find(i);
            int targetVal = target[i];
            Map<Integer, Integer> countMap = groupCounts.get(root);

            if (countMap.containsKey(targetVal) && countMap.get(targetVal) > 0) {
                matchingCount++;
                countMap.put(targetVal, countMap.get(targetVal) - 1);
            }
        }

        // 4. 최소 해밍 거리 = 전체 길이 - 일치하는 숫자의 개수
        return n - matchingCount;
    }

    private static int[] parent;

    // Union-Find : 루트 노드를 찾는 find 연산
    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union-Find : 두 그룹을 합치는 union 연산
    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
