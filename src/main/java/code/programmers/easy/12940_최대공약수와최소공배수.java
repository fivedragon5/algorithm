package code.programmers.easy;

class Lesson12940 {
    static int[] solution(int n, int m) {
        int gcd = gcd(n, m);
        int[] answer = {gcd, lcm(n,m,gcd)};
        return answer;
    }

    static int gcd(int a, int b) {
        while(b > 0) {
            int r = b;
            b = a % b;
            a = r;
        }
        return a;
    }

    static int lcm(int a, int b, int gcd) {
        return ( a * b ) / gcd;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 30;
        System.out.println("===START===");
        System.out.println(solution(n, m));
        System.out.println("===END===");
    }
}
