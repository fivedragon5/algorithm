package algorithm.code.programmers.study;

class Lesson77885 {

    public static void main(String[] args) {
        long[] numbers = {2,7,11};
        System.out.println("===START===");
        System.out.println(solution(numbers));
        System.out.println("===END===");
    }

    static long[] solution(long[] numbers) {

        int index = 0;

        for(long number : numbers) {
            if(number%2 == 0) numbers[index++] = number+1;
            else numbers[index++] = minNumber(number);
        }

        return numbers;
    }

    static long minNumber(long start) {
        StringBuilder startStr = new StringBuilder(Long.toBinaryString(start));
        String str = Long.toBinaryString(start);

        if (str.contains("0")) {
            int index = str.lastIndexOf('0');
            startStr.setCharAt(index, '1');
            if (index != startStr.length()-1) {
                startStr.setCharAt(index+1, '0');
            }
        }
        else {
            startStr.setCharAt(0, '0');
            startStr.insert(0,"1");
        }

        return Long.parseLong(startStr.toString(),2);
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
