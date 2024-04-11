package code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Problem1461 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> leftBooks = new LinkedList<>();
        List<Integer> rightBooks = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int locationOfBook = Integer.parseInt(st.nextToken());
            if (locationOfBook < 0) {
                leftBooks.add(Math.abs(locationOfBook));
            }
            else {
                rightBooks.add(locationOfBook);
            }
        }
        leftBooks.sort(Comparator.reverseOrder());
        rightBooks.sort(Comparator.reverseOrder());

        int moveCount = 0;
        int bookStack = 0;
        int temp = 0;
        int max = Math.max(leftBooks.get(0), rightBooks.get(0));

        for (int book : leftBooks) {
            if (bookStack == 0) {
                temp = book * 2;
            }
            bookStack++;
            if (bookStack >= M) {
                moveCount += temp;
                bookStack = 0;
                temp = 0;
            }
        }

        bookStack = 0;
        moveCount += temp;

        for (int book : rightBooks) {
            if (bookStack == 0) {
                temp = book * 2;
            }
            bookStack++;
            if (bookStack >= M) {
                moveCount += temp;
                bookStack = 0;
                temp = 0;
            }
        }
        System.out.println(moveCount + (temp * 2) - max);
    }
}
/*
7 2
-37 2 -6 -39 -29 11 -28

131
 */
