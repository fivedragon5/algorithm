package algorithm.code.programmers.stackNQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Lesson42587 {
    static int solution(int[] priorities, int location) {
        int answer = 0;
        List<PrintJob> list = new ArrayList<>();
        for(int i = 0 ; i < priorities.length ; i ++) {
            list.add(new PrintJob(i, priorities[i]));
        }

        int pointer = 0;

        while(!list.isEmpty()) {
            PrintJob job = list.remove(0);
            if (list.stream().anyMatch(a1 -> job.print < a1.print)) {
                list.add(job);
            }
            else {
                if(location == job.index) return pointer+1;
                else pointer++;
            }
        }

        return answer;
    }

    static int solution2(int[] priorities, int location) {
        //for문이 더 빠름..
        int answer = 0;
        List<PrintJob> list = new ArrayList<>();
        for(int i = 0 ; i < priorities.length ; i ++) {
            list.add(new PrintJob(i, priorities[i]));
        }

        int pointer = 0;

        while(!list.isEmpty()) {
            PrintJob job = list.remove(0);

            int max = 0;
            for (int i = 0; i < list.size() ; i++) {
                if(max < list.get(i).print) {
                    max = list.get(i).print;
                }
            }
            if(max > job.print) list.add(job);

            else {
                if(location == job.index) return pointer+1;
                else pointer++;
            }
        }

        return answer;
    }

    static int solution3(int[] priorities, int location) {
        int answer = 0;
        Queue<PrintJob> queList = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++) {
            queList.add(new PrintJob(i ,priorities[i]));
        }

        while (!queList.isEmpty()) {
            PrintJob job = queList.poll();
        }
        return answer;
    }

    static class PrintJob {

        int index;
        int print;

        public PrintJob(int index, int print) {
            this.index = index;
            this.print = print;
        }
    }

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2; // 1

        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location2 = 0; // 5
        System.out.println("===START===");
//        System.out.println(solution(priorities, location));
//        System.out.println(solution(priorities2, location2));
        System.out.println(solution2(priorities, location));
        System.out.println(solution2(priorities2, location2));

        System.out.println("===END===");
    }
}
