package algorithm.code.study;

/**
 * TODO : 다시풀기
 * 접근)
 *  n번째 유사 칸토열 = (n-1)유사 칸토열 + (n-1)유사 칸토열 + 5^n-1개의 0 + (n-1)유사 칸토열 + (n-1)유사 칸토열
 * 풀이)
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
        long totalLength = (long) Math.pow(5, n);

        long left = totalLength / l;
        long right = totalLength / r;

        System.out.println(left + "/" + right);

        return 0;
    }

    static String cantor(String str, int repeatCount) {
        String fiveZero = "00000";
        for (int j = 2; j <= repeatCount; j++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str);
            for (int i = 0; i < Math.pow(5, j - 2); i++) {
                sb.append(fiveZero);
            }
            sb.append(str);
            sb.append(str);
            str = sb.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        int n = 2;
        long l = 4;
        long r = 17;
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
//1101111011000001101111011
//3: 0, 5^2
//11011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011
//1101111011000001101111011110111101100000110111101100000000000000000000000001101111011000001101111011110111101100000110111101111011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001101111011000001101111011110111101100000110111101100000000000000000000000001101111011000001101111011110111101100000110111101111011110110000011011110111101111011000001101111011000000000000000000000000011011110110000011011110111101111011000001101111011

