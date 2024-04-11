package code.programmers.easy;

class Lesson76501 {
    static int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for(int i = 0 ; i < absolutes.length ; i ++) {
            if (signs[i]) answer += absolutes[i];
            else answer -= absolutes[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] absolutes = {4, 7, 12};
        boolean[] signs = {true,false,true}; // 9
        System.out.println("===START===");
        System.out.println(solution(absolutes, signs));
        System.out.println("===END===");
    }
}
