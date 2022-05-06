package algorithm.programmers.search;

class Lesson12921 {
    static int solution(int n) {
        int answer = 0;

        for(int i = 2 ; i <= n ; i++)
            if(isPrime(i)) answer++;

        return answer;
    }

    static boolean isPrime(int a) {
        for(int i = 2 ; i <= (int)Math.sqrt(a) ; i++) {
            if(a%2 == 0 || a%i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("===START===");
        System.out.println(solution(n));
        System.out.println("===END===");
    }
}
