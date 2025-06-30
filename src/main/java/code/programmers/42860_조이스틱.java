package code.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 *
 * 제한)
 *  name은 알파벳 대문자로만 이루어져 있습니다.
 *  name의 길이는 1 이상 20 이하입니다.
 *
 * 문제)
 *  조이스틱으로 알파벳 이름을 완성하기
 *   - 상 : 다음 알파벳
 *   - 하 : 이전 알파벳 (A에서 아래쪽으로 이동하면 Z)
 *   - 좌 : 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
 *   - 우 : 커서를 오른쪽으로 이동 ( 마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
 *  만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 반환하기
 *
 * 풀이)
 *  1. 각 알파벳을 A에서 시작하여 상하로 조작하는 횟수를 계산
 *  - A에서 Z로 이동하는 경우, 26 - (현재 알파벳 - A) 만큼의 조작이 필요
 *  - A에서 현재 알파벳으로 이동하는 경우, (현재 알파벳 - A) 만큼의 조작이 필요
 *  2. 커서 이동을 최소화하기 위해 좌우 이동 횟수를 계산
 *  - 커서가 왼쪽으로 이동할 때, 연속된 A를 건너뛰는 경우를 고려
 *  - 커서가 오른쪽으로 이동할 때, 연속된 A를 건너뛰는 경우를 고려
 *
 */
class Lesson42860 {
    public static void main(String[] args) {
        String a = "ABABAAAAABA";
        System.out.println(solution(a)); // 8

        String name = "JEROEN";
        System.out.println(solution(name)); // 56

        String name2 = "JAN";
        System.out.println(solution(name2)); // 23
    }

    public static int solution(String name) {
        int len = name.length();
        int answer = 0;

        // 1. 알파벳 변경(상하 조작) 횟수 합산
        for (int i = 0; i < len; i++) {
            int diff = name.charAt(i) - 'A';
            answer += Math.min(diff, 26 - diff);
        }

        // 2. 커서 이동(좌우) 최소값 계산
        int minMove = len - 1; // 오른쪽으로만 이동하는 기본 케이스

        for (int i = 0; i < len; i++) {
            int next = i + 1; // next : i보다 크고 연속된 'A'가 아닌 첫 번째 위치의 index
            // 연속된 'A' 구간 건너뛰기
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }
            // i까지 오른쪽, 다시 왼쪽으로 돌아가서 마지막 변경 위치까지
            // (0 ~ i 까지의 거리) + (len ~ next 까지의 거리) + (i와 len - next 중 작은 거리 한번더 더하기 *왕복*)
            int move = i + len - next + Math.min(i, len - next);
            minMove = Math.min(minMove, move);
        }

        return answer + minMove;
    }
}
