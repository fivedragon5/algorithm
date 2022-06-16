package algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

class Solution {
    static String solution(String parameter) {
        String answer = "";
        List<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);

//        a.forEach(c -> System.out.println(c));

        LambTest lambTest = (c, d) -> c+d;

        return answer;
    }

    @FunctionalInterface
    interface LambTest {
        public int sum(int a, int b);
    }

    public static void main(String[] args) {
        String parameter = "";
        System.out.println("===START===");

        System.out.println(solution(parameter));

        System.out.println("===END===");
    }
}
