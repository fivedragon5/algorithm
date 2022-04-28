package algorithm.programmers;

import java.util.HashSet;

class Lesson1845 {
    static int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int maxResult = nums.length/2;

        for(int mon : nums)
            set.add(mon);

        if(maxResult > set.size()) return set.size();

        return maxResult;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,2}; // 2
        System.out.println("===START===");
        System.out.println(solution(nums));
        System.out.println("===END===");
    }
}
