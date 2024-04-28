/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 * 5 <= sequence.length <= 1,000,000
 * 1 <= sequence[i] <= 1,000
 * 5 <= k <= 1,000,000,000
 */
package code.programmers.easy;

class Lesson178870 {
    static public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 1000000};
        int length = sequence.length;
        int left = 0;
        int right = 0;
        int sum = sequence[0];

        while(right <= length) {
            if (sum == k) {
                setAnswer(left, right, answer);
                sum -= sequence[left];
                left++;
            }
            else if(sum < k) {
                right++;
                if (right >= length) break;
                sum += sequence[right];
            }
            else {
                sum -= sequence[left];
                left++;
            }
        }
        System.out.println(answer[0] + "," + answer[1]);
        return answer;
    }

    private static void setAnswer(int left, int right, int answer[]) {
        if(right - left < answer[1] - answer[0]) {
            answer[0] = left;
            answer[1] = right;
        }
    }

    public static void main(String[] args) {
//        int[] sequence = {1, 2, 3, 4, 5};
//        int k = 7;

//        int[] sequence = {1, 1, 1, 2, 3, 4, 5};
//        int k = 5;

        int[] sequence = {2, 2, 2, 2, 2};
        int k = 6;

        System.out.println("===START===");
        System.out.println(solution(sequence, k));
        System.out.println("===END===");
    }
}
