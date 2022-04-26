package algorithm.programmers;

import java.util.LinkedList;

class Lesson12977 {
    static int solution(int[] nums) {
        int answer = 0;
        LinkedList<Integer> link = new LinkedList<>();
        for(int i = 0; i < nums.length ; i++) {
            for(int j = i+1 ; j < nums.length; j++) {
                for(int k = j+1 ; k < nums.length; k++) {
                    link.add(nums[i]+nums[j]+nums[k]);
                }
            }
        }
        boolean flag = true;
        for(int number : link) {
            flag = true;
            for(int i = 2 ; i <= (int) Math.sqrt(number); i++) {
                if(number%i == 0 || number%2 == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        System.out.println("===START===");
        System.out.println(solution(array));
        System.out.println("===END===");
    }
}
