package algorithm.codility;

class Test {
    static void solution(long N) {
        long enable_print = N % 10;
        while (N > 0) {
            if (enable_print == 0 && N % 10 != 0) {
                enable_print = 1;
            }
            if (enable_print > 0) {
                System.out.print(N % 10);
            }
            N = N / 10;
        }
    }

    public static void main(String[] args) {
        //조건이 number의 범위가 1 <= N <= 10,000,000,000
        long number = 100000000000L; //출력 : 11001

        number = 10011; //출력 : 11001
        solution(number);
        System.out.println();
        number = 11001; //출력 : 10011
        solution(number);
        System.out.println();
        number = 1; //출력 : 1
        solution(number);
        System.out.println();

        number = 10010; //출력 : 1001
        solution(number);
        System.out.println();

        number = 100100; //출력 : 1001 ->
        solution(number);
        System.out.println();

//        number = 10; //출력 : 1
//        solution(number);
    }
}
