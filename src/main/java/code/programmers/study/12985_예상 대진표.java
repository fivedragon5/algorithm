package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 *
 * 제한)
 * N : 21 이상 220 이하인 자연수 (2의 지수 승으로 주어지므로 부전승은 발생하지 않습니다.)
 * A, B : N 이하인 자연수 (단, A ≠ B 입니다.)
 *
 * 문제)
 *  1. 이 대회는 N명이 참가하고, 토너먼트 형식으로 진행
 *  2. N 명의 참가자는 각각1부터 N까지의 번호를 가지고 있습니다.
 *  3. (1,2)번, (3,4)번 ... (N-1,N)번의 참가자끼리 게임을 진행한다.
 *  4. 각 게임에서 이긴 사람은 다음 라운드에 진출 이때, 다음 라운드에 진출할 참가자의 번호는 다시 1번부터 N/2번을 차례대로 배정받습니다.
 *  5. 처음 라운드에 A번을 가진 참가자는 경쟁자로 B번을 가진 참가자와 몇 번째 라운드에서 만나는지 반환하기
 *   - 단 A,B번 참가자는 서로 붙게 되기 전까지 항상 이긴다고 가정
 *
 * 풀이)
 *  1. A번과 B번 참가자가 만나는 라운드를 찾기 위해, 두 참가자의 번호를 다음 라운드의 번호로 변환하는 함수를 만든다.
 *  2. 두 참가자의 번호가 같아질 때까지 반복하며 라운드 수를 증가시킨다.
 *  3. 두 참가자의 번호가 같아지면, 그 라운드 수에서 1을 빼서 반환한다.
 *   - 다음 라운드의 참가자 번호는 (참가자 번호 + 1) / 2 로 계산할 수 있다.
 */
class Lesson12985 {
    public static void main(String[] args) {
        int N = 8; int A = 4; int B = 7;
        System.out.println(solution(N, A, B)); // 3

        A = 1; B = 2;
        System.out.println(solution(N, A, B)); // 1

        A = 2; B = 3;
        System.out.println(solution(N, A, B)); // 1
    }

    public static int solution(int n, int a, int b) {
        int round = 1; // 라운드 수
        while (a != b) {
            a = nextRoundNumber(a); // 다음 라운드의 A번 참가자 번호
            b = nextRoundNumber(b); // 다음 라운드의 B번 참가자 번호
            round++; // 라운드 증가
        }
        return round - 1; // 만난 라운드 수 반환
    }

    private static int nextRoundNumber(int number) {
        return (number + 1) / 2;
    }
}
