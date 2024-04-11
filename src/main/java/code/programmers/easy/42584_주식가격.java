package code.programmers.easy;

class Lesson42584 {
    static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        answer[prices.length-1] = 0;

        for(int i = 0 ; i < prices.length - 1 ; i++ ) {
            if(answer[i] == 1) {
                answer[i] = prices.length - i - 1;
                continue;
            }
            for( int j = i+1 ; j < prices.length ; j ++) {
                if(prices[i] > prices[j]) {
                    answer[i] =  j - i;
                    break;
                }
                if(j == prices.length-1) answer[i] = prices.length - i - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.println("===START===");
        System.out.println(solution(prices));
        System.out.println("===END===");
    }
}
