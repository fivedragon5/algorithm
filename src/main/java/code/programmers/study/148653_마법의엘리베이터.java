package code.programmers.study;

/**
 * 접근)
 * 1. 주어진 현재 층수(storey)가 10보다 작아질때까지 DFS 탐색
 *  - 현재 내가 주어진 층에서 0층으로 가기위해선 2가지 중에 선택을 해야함 두가지를 전부 확인하기 위해
 *   - 1. 올라갔다 내려가는 방법
 *      - ex) 17층 -> 20층 -> 0층
 *   - 2. 내려갔다 내려가는 방법
 *      - ex) 13층 -> 10층 -> 0층
 *
 * 풀이)
 * 1. DFS탐색 종료시 최소값을 갱신 시켜주기위해 전역 변수 min을 선언해준다
 *  - (최악의경우 5^9이 최대값이 나올것으로 예상 우선 Integer.MAX_VALUE로 설정)
 * 2. 주어진 층수(storey)가 10보다 작을때까지 나누기 10을 해서 2가지 선택지를 탐색
 *  - 현재 storey, 현재까지 사용한 마법의 돌 갯수 sum
 * 3. 10보다 작아질 경우 조건을 고려하여 최소값을 갱신
 *  - 5이하일 경우
 *      - 남은 층수 내려가기
 *  - 6이상일 경우
 *      - 올라갔다 내려가기
 *
 * 제한)
 * 1 ≤ storey ≤ 100,000,000
 */
class Lesson148653 {

    static int min = Integer.MAX_VALUE;

    static int solution(int storey) {
        dfs(storey, 0);
        return min;
    }

    static void dfs(int storey, int sum) {
        if (storey < 10) {
            if (storey >= 6) {
                min = Math.min(min, sum + 10 - storey + 1);
            }
            else {
                min = Math.min(min, sum + storey);
            }
            return;
        }

        int quotient = storey / 10;
        int remainder = storey % 10;

        dfs(quotient, sum + remainder);
        dfs(quotient + 1, sum + 10 - remainder);
    }

    public static void main(String[] args) {
        int storey = 95; //13
        // 20 -> -10 -10
        System.out.println("===START===");
        System.out.println(solution(storey));
        System.out.println("===END===");
    }
}
