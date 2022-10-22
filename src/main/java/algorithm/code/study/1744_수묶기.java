package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Problem1744 {

    /**
     수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.
     0 < N < 50보다 작은 자연수이다.
     -1000 <= number <= 1000
     */

    public static void main(String args[]) throws IOException {
        /**
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        boolean isExistZero = false;
        long answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            if(number > 0) positives.add(number);
            else if(number < 0) negatives.add(number);
            else if(number == 0) isExistZero = true;
        }

        Collections.sort(positives);
        Collections.sort(negatives);

        if(positives.size() % 2 != 0) answer += positives.get(0);
        
        //더할때 int의 범위를 넘어 갈 수 있음.
        //casting 하면서 시도해볼것

        for (int i = positives.size()-1; i > 0; i -= 2) {
            answer += positives.get(i) * positives.get(i-1);
        }

        for (int i = 0; i < negatives.size()-1; i += 2) {
            answer += negatives.get(i) * negatives.get(i+1);
        }

        if(negatives.size() % 2 != 0) {
            if (!isExistZero) {
                answer += negatives.get(negatives.size()-1);
            }
        }

        System.out.println(answer);
    }
}


/**
 *
 ex)
 4
 -1
 2
 1
 3

 6

 6
 0
 1
 2
 4
 3
 5

 27

 1
 -1

 -1

 3
 -1
 0
 1

 1

 7
 -1
 -2
 -3
 -4
 -5
 5
 4

 */
