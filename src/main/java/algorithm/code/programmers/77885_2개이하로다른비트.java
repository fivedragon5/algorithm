package algorithm.code.programmers;

import java.util.LinkedList;
import java.util.List;

class Lesson77885 {

    public static void main(String[] args) {
        long[] numbers = {2,7};
        System.out.println("===START===");
        System.out.println(solution(numbers));
        System.out.println("===END===");
    }

    static long[] solution(long[] numbers) {
        List<Integer> list = new LinkedList<>();

        for(long number : numbers) {
            list.add(minNumber((int) number));
        }

        long[] answer = new long[list.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    static int minNumber(int start) {
        //String startStr = Integer.toBinaryString(start);
        StringBuffer startStr = new StringBuffer(Integer.toBinaryString(start));

        int index = startStr.lastIndexOf("0");
        System.out.println("1," + startStr);
        if (index > -1) {
            startStr = startStr.replace(index, index,"1");
        }

        System.out.println("0," + startStr);

        return 0;
    }

    static boolean isDiff(StringBuffer str1, StringBuffer str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count > 2) return false;
        }
        return true;
    }

/**
 * 시간초과
 */
//    static int minNumber(int start) {
//        String startStr = Integer.toBinaryString(start);
//        int startLength = startStr.length();
//        String diffStr = "";
//        while(true) {
//            diffStr = Integer.toBinaryString(++start);
//
//            if(startLength != diffStr.length()) {
//                startStr = "0" + startStr;
//                startLength++;
//            }
//
//            if (isDiff(startStr, diffStr)) {
//                return start;
//            }
//        }
//    }
//
//    static boolean isDiff(String str1, String str2) {
//        int count = 0;
//        for (int i = 0; i < str1.length(); i++) {
//            if (str1.charAt(i) != str2.charAt(i)) {
//                count++;
//            }
//            if (count > 2) return false;
//        }
//        return true;
//    }
}
