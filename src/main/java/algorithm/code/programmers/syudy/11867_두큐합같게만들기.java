package algorithm.code.programmers.syudy;

import java.util.ArrayList;

class Lesson118667 {
    /**
     * 문제)
     * 1.길이가 같은 두개의 큐
     * 2.하나의 큐를 골라 pop, 다른큐에 insert => 1회 수행한 것으로 간주
     * 3.먼저 집어넣은 원소가 먼저 나오는 구조 (배열 앞쪽에 있을수록 먼저 집어넣은 원소임을 의미)
     * 4.pop: 배열의 첫번째 원소 추출, insert:배옆의 끝에 원소 추가
     * 제한 사항)
     * 1 ≤ queue1의 길이 = queue2의 길이 ≤ 300,000
     * 1 ≤ queue1의 원소, queue2의 원소 ≤ 109
     * 주의: 언어에 따라 합 계산 과정 중 산술 오버플로우 발생 가능성이 있으므로 long type 고려가 필요합니다.
     */
    static int solution(int[] queue1, int[] queue2) {
        int answer = 1000000;
        int totalLength = queue1.length*2;
        int divideLength = queue1.length;
        long sumElement = 0;
        int[] queue = new int[totalLength];
        ArrayList<int[]> answerList = new ArrayList<>();

        for(int i = 0; i < queue1.length; i++) {
            queue[i] = queue1[i];
            queue[divideLength+i] = queue2[i];
            sumElement = sumElement + queue1[i] + queue2[i];
        }

        long sumDivide = sumElement/2;

        int left = 0;
        int right = 0;
        long sum = queue[0];

        while (left < totalLength) {
            if(right >= totalLength-1 && sum <= sumDivide) {
                break;
            }

            if (sum == sumDivide) {
                answerList.add(new int[]{left,right});
                right++;
                sum += queue[right];
            }
            else if (sum > sumDivide) {
                sum -= queue[left];
                left++;
            }
            else {
                right++;
                sum += queue[right];
            }
        }

        //정답이 나올 수 없을경우 -1 return
        if(answerList.size() < 1) {
            return -1;
        }
        
        //정답 구하는 식 작성해보기
        for(int[] array: answerList) {
            //System.out.println(Arrays.toString(array));
            //걸칠경우 or 안걸칠경우
            int leftPer = array[0] % divideLength;
            int rightPer = divideLength - (array[1] % divideLength) - 1;

            if (
                    array[0] <= divideLength-1 &&
                    array[1] > divideLength-1
            ) {
                //걸칠경우
                answer = Math.min(answer, array[0] + array[1] - divideLength + 1);
                answer = Math.min(answer, leftPer + divideLength - rightPer);
            }
            else {
                //안걸칠경우
                if (rightPer == 0) {
                    answer = Math.min(answer, leftPer);
                }
                else {
                    answer =  Math.min(answer, divideLength + (leftPer * 2) + array[1] - array[0] + 1);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        //int[] queue1 = {3, 2, 7, 2};
        //int[] queue2 = {4, 6, 5, 1};
        //int[] queue1 = {1,2,1,2};
        //int[] queue2 = {1,10,1,2};
        //int[] queue1 = {1,1};
        //int[] queue2 = {1,5};
        int[] queue1 = {10,1,3};
        int[] queue2 = {7,7,8};
        System.out.println("===START===");
        System.out.println(solution(queue1, queue2));
        System.out.println("===END===");
    }
}
