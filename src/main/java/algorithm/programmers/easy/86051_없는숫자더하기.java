package algorithm.programmers.easy;

class Lesson86051 {
    static int solution(int[] numbers) {
        int answer = 0;
        for(int number : numbers)
            answer += number;
        return 45-answer;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,6,7,8,0};
        System.out.println("===START===");
        System.out.println(solution(numbers));
        System.out.println("===END===");
    }
}
