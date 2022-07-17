package algorithm.code.programmers.easy;

class Lesson87389{
    static int solution(int n) {
        int maxLimit = (int) Math.sqrt(n-1);
        for(int i = 2; i <= maxLimit ; i++)
            if((n-1)%i == 0) return i;
        return n-1;
    }

    public static void main(String[] args) {
        int n1 = 10; // 3
        int n2 = 12; // 11
        int n3 = 3; // 11
        System.out.println("===START===");
        System.out.println(solution(n1));
        System.out.println(solution(n2));
        System.out.println(solution(n3));
        System.out.println("===END===");
    }
}
