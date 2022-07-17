package algorithm.code.programmers.normal;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Lesson42626 {
    static int solution(int[] scoville, int K) {
    int answer = 0;
        Arrays.sort(scoville);
    List<Integer> list = Arrays.stream(scoville).boxed().collect(Collectors.toList());
        while(true) {
        if(list.get(0) >= K) break;
        if(list.size() < 2) return -1;
        int hot = list.get(0) + (list.get(1) * 2);
        list.remove(0);
        list.remove(0);

        int index = 0;

        for(int number : list) {
            if(number >= hot) break;
            index++;
        }
        list.add(index, hot);
        answer++;
    }
        return answer;
}

    static int solution2(int[] scoville, int K) {
        //PriorityQueue사용
        //JAVA Heap관련 자료 참고 https://shanepark.tistory.com/261
        int answer = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : scoville) {
            minHeap.add(num);
        }

        while(minHeap.peek() <= K) {
            int firstMin = minHeap.poll();
            if(firstMin >= K) return answer;
            if(minHeap.isEmpty()) return -1;
            int secondMin = minHeap.poll();
            int hot = firstMin + (secondMin * 2);
            minHeap.add(hot);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        //int[] scoville = {1, 2, 1};
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7; //2
        System.out.println("===START===");
        System.out.println(solution2(scoville, K));
        System.out.println("===END===");
    }
}
