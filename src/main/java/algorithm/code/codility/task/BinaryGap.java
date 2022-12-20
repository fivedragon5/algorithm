package algorithm.code.codility.task;

import java.io.IOException;

class BinaryGap {
    static public int solution(int N) {
        String binaryStr = Integer.toBinaryString(N);
        String[] splitStr = binaryStr.split("1");
        int count = 0;

        for (int i = 0; i < splitStr.length - 1; i++) {
            count = Math.max(count, splitStr[i].length());
        }

        if(splitStr.length > 0 && N%2 != 0) {
            count = Math.max(count, splitStr[splitStr.length-1].length());
        }

        return count;
    }

    public static void main(String args[]) throws IOException {
        int a = 1001;
        System.out.println(solution(a));
    }
}
