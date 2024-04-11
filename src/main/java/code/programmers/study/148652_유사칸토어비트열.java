package code.programmers.study;

/**
 * 접근)
 *  r까지의 1의 갯수 - l까지의 1의 갯수 = 정답
 *  f(n) = f(n-1) f(n-1) n-1 * 0 f(n-1) f(n-1)
 *  위 식을 참고하여 n을 5개의 구역으로 나누었을때 l,r의 구역을 확인하여 1의 갯수를 누적해 나간다.
 *
 * 풀이)
 * 1. 주어진 l,r에 대한 x 값을 구한다(5^x-1 < l <= 5^x)
 * 2. 5로 나누어 l,r 이 5개의 구역중 어느 부분에 위치 하는지 확인하고 그전까지의 1의 갯수를 누적해서 더해준다.
 * 3. r의 1의 갯수와 l의 1의 갯수를 빼준다.
 *
 * 제한)
 * 1 ≤ n ≤ 20
 * 1 ≤ l, r ≤ 5n
 * l ≤ r < l + 10,000,000
 * l과 r은 비트열에서의 인덱스(1-base)이며 폐구간 [l, r]을 나타냅니다.
 *
 */
class Lesson148652 {

    static int solution(int n, long l, long r) {
        int leftSize = 0;
        int rightSize = 0;
        int depth = 1;
        long size = 1;

        while (leftSize == 0 || rightSize == 0) {
            size *= 5;
            if (leftSize == 0 && size > l-1) {
                leftSize = depth;
            }
            if (rightSize == 0 && size > r) {
                rightSize = depth;
            }
            depth++;
        }

        long left = counterOne(l-1, leftSize);
        long right = counterOne(r, rightSize);

        System.out.println(left + "/" + right);

        return (int) (right - left);
    }

    static long counterOne(long end, int size) {
        long count = 0;
        while (size != 0) {
            long std = (long) Math.pow(5, size - 1);
            long div = end / std;
            count += cacl(size - 1, div);
            end = end % std;
            if (div == 2 && end > 0) break;
            size--;
        }
        return count;
    }

    static long cacl(int a, long b) {
        if (b <= 2) {
            return (long) Math.pow(4, a) * b;
        }
        else {
            return (long) Math.pow(4, a) * (b - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        long l = 27;
        long r = 68;
        System.out.println("===START===");
        System.out.println(solution(n, l, r));
//        System.out.println(solution(2, l, r));
//        System.out.println(solution(3, l, r));
//        System.out.println(solution(4, l, r));
        System.out.println("===END===");
    }
}
//1 : 4^1개
//11011
//2: 0, 5 4^2개
//11011 11011 00000 11011 11011
//3: 0, 5^2

//11011
//1101111011000001101111011
//1101111011000001101111011 1101111011000001101111011 0000000000000000000000000 11011110110000011011 110111101111011000001101111011
//1101111011000001101111011110111101100000110111101100000000000000000000000001101111011000001101111011110111101100000110111101111011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001101111011000001101111011110111101100000110111101100000000000000000000000001101111011000001101111011110111101100000110111101111011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011

