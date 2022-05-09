package algorithm.programmers.dfsNBfs;

class Lesson43165 {

    static int targetNumber = 0;
    static int answerCount = 0;

    static int solution(int[] numbers, int target) {
        targetNumber = target;
        dfs(numbers, 0, 0);
        return answerCount;
    }

    static void dfs(int[] numbers, int sum, int index) {
        if(index >= numbers.length) {
            if(sum == targetNumber) {
                answerCount++;
            }
            return;
        }
        dfs(numbers, sum + numbers[index], index+1);
        dfs(numbers, sum - numbers[index], index+1);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("===START===");
        System.out.println(solution(numbers, target));
        System.out.println("===END===");
    }
}
