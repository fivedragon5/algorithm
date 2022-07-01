package algorithm.programmers;

class Lesson92335 {
    static int solution(int n, int k) {
        int answer = 0;
        String a = Integer.toString(n, k);
        String b = Integer.toHexString(n);

        System.out.println(a);
        System.out.println(b);

        return answer;
    }

    public static void main(String[] args) {
//        int n = 437674;
//        int k = 3;

        int n = 437674;
        int k = 16;
        System.out.println("===START===");
        System.out.println(solution(n, k));
        System.out.println("===END===");
    }
}
