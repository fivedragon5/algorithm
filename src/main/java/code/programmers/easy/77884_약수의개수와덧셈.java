package code.programmers.easy;

class Lesson77884 {
    static int solution(int left, int right) {
        int answer = 0;
        for(int i = left ; i <= right ; i++) {
            double a = Math.sqrt(i);
            if(a%1 == 0) answer -= i;
            else answer += i;
        }
        return answer;
    }

    public static void main(String[] args) {
        int left = 13;
        int right = 17; // 43

        int left2 = 24;
        int right2 = 27; // 52
        System.out.println("===START===");
        System.out.println(solution(left, right));
        System.out.println(solution(left2, right2));
        System.out.println("===END===");
    }
}
