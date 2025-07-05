package code.programmers.study;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/62048
 *
 * 제한)
 *  1 <= W, H <= 100,000,000
 *
 * 문제)
 *  1. 가로 w, 높이 h인 직사각형 종이가 있다.
 *  2. 종이에는 가로,세로 방향과 평행하게 격자 형태로 선이 그어져 있다. (1x1 크기의 정사각형 격자)
 *  3. 직사각형의 대각선 꼭지점 2개를 잇는 방향으로 종이가 잘렸을 때, 이 종이에서 사용할 수 있는 격자(1x1)의 개수를 구하기
 *
 * 풀이)
 *  1. 대각선이 지나가는 격자의 개수를 구하기 위해, w와 h의 최대공약수(gcd)를 구한다.
 *  2. 대각선이 지나가는 격자의 개수는 (w + h - gcd(w, h)) 이다.
 *  3. 따라서, 사용할 수 있는 격자의 개수는 w * h - (w + h - gcd(w, h)) 이다.
 *
 */

class Lesson62048 {
    public static void main(String[] args) {
        int w = 8;
        int h = 12;
        System.out.println(solution(w, h)); // 80L
    }

    public static long solution(int w, int h) {
        long gcd = gcd(w, h);
        // 전체 격자의 수 - 대각선이 지나가는 격자의 수(최대 공약수)
        return (long) w * h - (w + h - gcd);
    }

    // 최대 공약수
    private static long gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
