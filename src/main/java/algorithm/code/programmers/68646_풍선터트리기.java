package algorithm.code.programmers;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Lesson68646 {
    static int solution(int[] a) {
        int answer = 2;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int currentNumber = a[0];
        int[] sortArray = a.clone();
        Arrays.sort(sortArray);

        List<Integer> list = new ArrayList<>();
        int listIndex = list.indexOf(currentNumber);

        for(int i = 0; i < a.length; i++) {
            list.add(sortArray[i]);
        }

        list.remove(listIndex);

        listIndex = list.indexOf(a[a.length-1]);
        //list.remove(listIndex);

        queue.add(currentNumber);

        for(int i = 1; i < a.length-1; i ++) {
            currentNumber = a[i];

            System.out.println(i + "// current:" + currentNumber);
            System.out.println(list.toString());

            listIndex = list.indexOf(currentNumber);
            list.remove(listIndex);

            int leftMinNumber = queue.peek();
            int rightMinNumber = list.indexOf(0);

            if(!(leftMinNumber < currentNumber && rightMinNumber < currentNumber)) {
                answer++;
            }

            queue.add(currentNumber);
        }

        return answer;
    }

    public static void main(String[] args) {
        //int[] a = {9,-1,-5}; // 3
        int[] a = {-16,27,65,-2,58,-92,-71,-68,-61,-33}; // 6
        System.out.println("===START===");
        System.out.println(solution(a));
        System.out.println("===END===");
    }
}
