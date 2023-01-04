package algorithm.code.programmers.soon;

/**
 *
 * TODO : 어렵다 나중에 다시 풀어보자.
 * 제한)
 * clockHands[n][m]
 * 2 <= n, m <= 8
 * 0 ≤ clockHands[n][m] ≤ 3
 * 0:12시, 1:3시, 2:6시, 3:9시
 * 해결 가능한 퍼즐만 주어집니다.
 */
class Lesson131702 {
    static int solution(int[][] clockHands) {
        int answer = 0;
        return answer;
    }

    static void rotate() {

    }

    static int plus(int number) {
        return number == 3 ? 0 : number + 1;
    }

    public static void main(String[] args) {
        int[][] clockHands = {{0,3,3,0},{3,2,2,3},{0,3,2,0},{0,3,3,3}};
        System.out.println("===START===");
        System.out.println(solution(clockHands));
        System.out.println("===END===");
    }
}

/*
0 3 3 0
3 2 2 3
0 3 2 0
0 3 3 3

------

0 1 3 0
1 2 0 0
3 0 2 2
0 2 0 0x

 */