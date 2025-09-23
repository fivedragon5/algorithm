package code.exam.socar;

/**
 * 2025.09.17
 * 제한)
 *  3 <= n <= 250
 *  3 <= m <= 250
 *  order.length = n * m - 2
 *      - order[i] = i+1 번째로 사라지는 발판의 좌표를 나타냄
 *       -> i+1번째로 사라지는 발판의 좌표는 (order[i][0], order[i][1])
 *      - 1 <= order[i][0] <= n
 *      - 1 <= order[i][1] <= m
 *      - order[i] != (1,1), order[i] != (n,m)
 *      - order 배열의 원소는 중복되지 않음
 *
 *  정확성 테스트 케이시 제한 사항
 *   - 3 <= n, m <= 50
 *
 *  효율성 테스트 케이시 제한 사항
 *   - 주어진 조건 외 추가 제한사항 없음
 *
 * 문제)
 *  1. n * m 개의 발판이 세로 n, 가로 m 형태로 나열되어 있음
 *  2. 가장 왼쪽 위 발판의 좌표는 (1,1), 가장 오른쪽 아래 발판의 좌표는 (n,m)
 *  3. 한 발판에서 다른 발판으로 이동할 때 상하자우 방향의 인접한 발판으로 이동 가능, 단 대각선 방향으로 이동할 수 없음
 *  4. (1,1) 발판과 (n,m) 발판을 제외한 모든 발판은 주어진 순서대로 하나씩 사라짐
 *  5. 몇번째 발판이 사라졌을 때 부터 (1,1) 발판에서 출발해 (n,m) 발판으로 이동할 수 없게 되는지를 알고 싶음
 *
 * 풀이)
 *  - 유니온 파인드
 *  1. (1,1) 발판과 (n,m) 발판을 제외한 모든 발판이 사라진 상태에서 시작
 *  2. order 배열을 역순으로 탐색하며 발판을 하나씩 복원
 *  3. 발판이 복원될 때마다 상하좌우 인접한 발판들과 유니온 연산을 수행
 *  4. (1,1) 발판과 (n,m) 발판이 같은 집합에 속하는지 확인
 *      - 같은 집합에 속한다면, 현재 복원된 발판이 사라졌을 때 (1,1) 발판에서 (n,m) 발판으로 이동 가능
 *      - 다른 집합에 속한다면, 현재 복원된 발판이 사라졌을 때 (1,1) 발판에서 (n,m) 발판으로 이동 할 수 없음
 *  5. (1,1) 발판과 (n,m) 발판이 다른 집합에 속하게 되는 순간의 인덱스를 반환
 *
 */

class Socar_2 {
    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int[][] order = {{3,2}, {3,1}, {1,4}, {1,2}, {2,4}, {2,3}, {2,2}, {1,3}, {2,1}, {3,3}};
        System.out.println(solution(n, m, order)); // 6

        n = 4;
        m = 4;
        int[][] order2 = {{1,4}, {1,3}, {1,4}, {2,4}, {2,3}, {2,2}, {3,2}, {3,3}, {3,4}, {4,3}, {4,2}, {4,1}, {3,1}, {2,1}};
        System.out.println(solution(n, m, order2)); // 10

        n = 4;
        m = 5;
        int[][] order3 = {{4,1}, {4,2}, {4,3}, {4,4}, {3,4}, {2,4}, {1,2}, {1,4}, {2,1}, {2,2}, {2,3}, {3,1}, {3,2}, {3,3}, {1,5}, {2,5}, {3,5}, {1,3}};
        System.out.println(solution(n, m, order3)); // 8
    }

    private static int[] PARENTS;
    private static int N, M;

    public static int solution(int n, int m, int[][] order) {
        N = n;
        M = m;
        int size = N * M;
        PARENTS = new int[size];

        for (int i = 0; i < size; i++)  {
            PARENTS[i] = i;
        }

        boolean[] isStepPresent = new boolean[size];
        isStepPresent[toIndex(1,1)] = true;
        isStepPresent[toIndex(n,m)] = true;

        return findDisconnectStep(order, isStepPresent);
    }

    private static int findDisconnectStep(int[][] order, boolean[] isStepPresent) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int start = toIndex(1,1);
        int end = toIndex(N,M);

        for (int i = order.length - 1; i >= 0; i--) {
            int x = order[i][0];
            int y = order[i][1];
            int current = toIndex(x, y);
            isStepPresent[current] = true;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
                    int neighborIndex = toIndex(nx, ny);
                    if (isStepPresent[neighborIndex]) {
                        union(current, neighborIndex);
                    }
                }
            }

            if (find(start) == find(end)) {
                return i + 1;
            }
        }

        return -1;
    }

    private static int toIndex(int x, int y)  {
        return (x - 1) * M + (y - 1);
    }

    private static int find(int x) {
        if (PARENTS[x] == x) return x;
        return PARENTS[x] = find(PARENTS[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            PARENTS[rootB] = rootA;
        }
    }
}
