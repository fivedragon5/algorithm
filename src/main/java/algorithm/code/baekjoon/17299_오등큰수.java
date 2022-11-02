package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Problem17299 {

    public static void main(String args[]) throws IOException {

        /**
            1 <= N <= 1,000,000
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        int[] answer = new int[N];

        st = new StringTokenizer(br.readLine());

        HashMap<Integer, Integer> numbersCount = new HashMap<>();

        for (int i = 0; i < N; i++) {
             int number = Integer.parseInt(st.nextToken());
            array[i] = number;
            numbersCount.put(number, numbersCount.getOrDefault(number,0) + 1);
        }

        int currentMaxCount = -1;
        Stack<Integer> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();

        for (int i = N-1; i >= 0; i--) {
            int indexNumber = array[i];
            int count = numbersCount.get(indexNumber);

            if(currentMaxCount < count) {
                stack.clear();
                currentMaxCount = count;
            }

            while(!stack.isEmpty()) {
                int number = stack.peek();
                if(count < numbersCount.get(number)) {
                    answer[i] = number;
                    break;
                }
                else {
                    stack.pop();
                }
            }

            if(stack.isEmpty()) answer[i] = -1;

            stack.add(indexNumber);
        }

        for (int n : answer) {
            sb.append(n + " ");
        }

        System.out.println(sb.toString());


//        for (int i = 0; i < N-1; i++) {
//            boolean isFlag = false;
//            int count = numbersCount.get(array[i]);
//            for (int j = i+1; j < N; j++) {
//                int number = array[j];
//                if(count < numbersCount.get(number)) {
//                    sb.append(number + " ");
//                    isFlag = true;
//                    break;
//                }
//            }
//            if(!isFlag) {
//                sb.append("-1 ");
//            }
//        }
//        sb.append("-1 ");
//        System.out.println(sb.toString());
    }
}

/**
 7
 1 1 2 3 4 2 1
 */
