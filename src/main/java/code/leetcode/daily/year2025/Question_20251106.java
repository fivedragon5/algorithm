package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/power-grid-maintenance/description/?envType=daily-question&envId=2025-11-06
 *
 * 제한)
 *  1 <= c <= 10^5
 *  0 <= n == connections.length <= min(10^5, c * (c - 1) / 2)
 *  connections[i].length == 2
 *  1 <= ui, vi <= c
 *  ui != vi
 *  1 <= queries.length <= 2 * 10^5
 *  queries[i].length == 2
 *  queries[i][0] is either 1 or 2.
 *  1 <= queries[i][1] <= c
 *
 * 문제)
 *  1. 발전소는 n개의 양방향 케이블로 연결되어 있다.
 *  2. connections라는 2차원 배열을 통해 표현된다
 *   - connections[i] = [ui, vi]는 발전소 ui와 vi가 케이블로 연결되어 있음을 나타낸다.
 *  3. 초기 상태에서는 모든 발전소가 운영중이다.
 *  4. queries라는 2차원 배열이 주어진다.
 *   - [1, x]: 발전소 x에 대한 점검 요청
 *    - x가 온라인 이면 x가 점검을 직접 해결
 *    - x가 오프라인 이면 동일한 파워 그리드 내에서 온라인인 발전소 중 가장 작은 id를 가진 발전소가 점검을 해결
 *    - 만약 해당 그리드에 온라인 발전소가 없다면 -1 반환
 *   - [2, x]: 발전소 x가 오프라인 상태로 전환
 *  5. 각 [1, x] 쿼리에 대해 점검을 해결한 발전소의 id를 배열로 반환
 *  6. 파워 그리드의 연결 구조는 유지 된다.
 *
 * 풀이)
 *  1. 파워 그리드의 연결 구조를 그래프로 표현 List<PriorityQueue<Integer>> : 파워그리드 별 우선순위 큐(발전소 ID 오름차순)
 *  2. dfs를 통해 각 파워 그리드 별로 우선순위 큐 생성 및 발전소 -> 파워그리드 매핑 정보 저장
 *  3. 각 쿼리를 순회하며 처리
 *  - [1, x] : x가 온라인인 경우 x 반환
 *  - [2, x] : x를 오프라인 처리 -> 동일 파워그리드 내 우선순위 큐에서 온라인 발전소 탐색, group이 없을 경우 -1 반환
 *  4. 결과 반환
 *
 */

public class Question_20251106 {
    public static void main(String args[]) throws IOException {
        int n = 5;
        int[][] connections = {{1,2},{2,3},{3,4},{4,5}};
        int[][] queries = {{1,3},{2,1},{1,1},{2,2},{1,2}};
        System.out.println(Arrays.toString(processQueries(n, connections, queries)));
    }

    private static int[] processQueries(int c, int[][] connections, int[][] queries) {
        // 발전소 연결 그래프
        Map<Integer, Integer> powerToGroupMap = new HashMap<>();
        List<PriorityQueue<Integer>> graph = makeGroup(c, connections, powerToGroupMap);

        // 각 발전소의 오프라인 여부
        boolean[] isOffline = new boolean[c + 1];

        // 정답 배열
        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0];
            int node = query[1];

            if (type == 1) {
                if (isOffline[node]) {
                    boolean isAllOffline = true;
                    int powerGroupId = powerToGroupMap.getOrDefault(node, -1);

                    if (powerGroupId == -1) {
                        result.add(-1);
                        continue;
                    }

                    PriorityQueue<Integer> pq = graph.get(powerGroupId);
                    while (!pq.isEmpty()) {
                        if (isOffline[pq.peek()]) {
                            pq.poll();
                        } else {
                            result.add(pq.peek());
                            isAllOffline = false;
                            break;
                        }
                    }
                    if (isAllOffline) {
                        result.add(-1);
                    }
                } else {
                    result.add(node);
                }
            } else if (type == 2) {
                isOffline[node] = true;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<PriorityQueue<Integer>> makeGroup(int n, int[][] connections, Map<Integer, Integer> powerToGroupMap) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }

        boolean[] isVisited = new boolean[n + 1];

        List<PriorityQueue<Integer>> pqList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                PriorityQueue<Integer> newGroupPq = new PriorityQueue<>();
                dfs(i, graph, isVisited, newGroupPq, powerToGroupMap, pqList.size());
                pqList.add(newGroupPq);
            }
        }

        return pqList;
    }

    private static void dfs(
            int node, List<List<Integer>> graph, boolean[] isVisited, PriorityQueue<Integer> pq,
            Map<Integer, Integer> powerToGroupMap, int groupId) {
        for(int neighbor : graph.get(node)) {
            if (!isVisited[neighbor]) {
                isVisited[neighbor] = true;
                pq.offer(neighbor);
                powerToGroupMap.put(neighbor, groupId);
                dfs(neighbor, graph, isVisited, pq, powerToGroupMap, groupId);
            }
        }
    }
}
