package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem2108 {

    static int N;

    public static void main(String args[]) throws IOException {
        /** 500000 * 4000 < 20억 => int 사용가능
         * 1. 평균 소수점1번쨰 짜리
         * 2. 중앙값
         * 3. 최빈값 : 가장많이 등장 여러개일경우 2번째
         * 4. 범위 : 최대 - 최소
         5
         1
         3
         8
         -2
         2
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        List<Integer> list = new LinkedList<>();
        int[] array = new int[N];

        int maxCount = 0;
        int maxCountKey = -1;

        boolean isFlag = false;

        double sum = 0;

        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            
            
            array[i] = a;
            //평균을 구하기 위해 sum을 해줌
            sum += a;
            
            
            //최빈값 관련
            int count = map.getOrDefault(a, 0);
            count++;

            map.put(a, count);

            if (maxCount < count) {
                isFlag = false;
                maxCount = count;
                maxCountKey = a;
                list.clear();
                list.add(a);
            }
            else if (maxCount == count) {
                isFlag = true;
                list.add(a);
            }
        }
        
        //정렬
        Arrays.sort(array);

        double temp = sum/N;
        System.out.println(Math.round(temp));

        System.out.println(array[N/2]);

        if (isFlag) {
            list.sort((n1, n2) -> n1 - n2);
            System.out.println(list.get(1));
        }
        else {
            System.out.println(maxCountKey);
        }
        System.out.println(array[N-1]-array[0]);
    }

//    static void solve() throws IOException {
//
//        int sum = 0;
//
//        int duplicateMaxCount = -1;
//        int duplicateFirstMaxNumber = 0;
//        int duplicateSecondMaxNumber = 0;
//        boolean isMany = false;
//
//        int max = -4000;
//
//        //  <= N
//
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//
//        HashMap<Integer,Integer> map = new HashMap<>();
//
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//
//            minHeap.add(a);
//
//            sum += a;
//
//            int count = map.getOrDefault(a, 0);
//            count++;
//            map.put(a, count);
//
//            if (max < a) max = a;
//
//            if(duplicateMaxCount < count) {
//                duplicateMaxCount = count;
//                duplicateFirstMaxNumber = a;
//            }
//            else if(duplicateMaxCount == count) {
//                isMany = true;
//                //같을경우 생각해보자.
//            }
//        }
//
//        //1
//        float temp = sum/N;
//        System.out.println(Math.round(temp));
//
//        //2
//        int middle = N/2 + 1;
//        System.out.println(middle);
//
//        //3
//        System.out.println(map.get(duplicateFirstMaxNumber));
//
//        //4
//        int min = minHeap.peek();
//        System.out.println(max - min);
//    }
}
