package algorithm.programmers;


import java.util.HashMap;

class Lesson17677 {
    static int solution(String str1, String str2) {
        HashMap<String, Integer> str1Map = new HashMap<>();
        double gyo = 0;
        double hap = 0;

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for(int i = 0 ; i < str1.length()-1 ; i ++) {
            if (isValidationChar(str1.charAt(i)) && isValidationChar(str1.charAt(i+1))) {
                String temp = (str1.charAt(i) + "" + str1.charAt(i+1));
                str1Map.put(temp, str1Map.getOrDefault(temp, 0) + 1);
                hap++;
            }
        }

        for(int i = 0 ; i < str2.length()-1 ; i ++) {
            if (isValidationChar(str2.charAt(i)) && isValidationChar(str2.charAt(i+1))) {
                String temp = (str2.charAt(i) + "" + str2.charAt(i+1));
                hap++;
                if(str1Map.get(temp) != null && str1Map.get(temp) != 0) {
                    hap--;
                    gyo++;
                    str1Map.put(temp, str1Map.get(temp) - 1);
                }
            }
        }
        //TEST CASE 5, 13 Check
        if (hap == 0 && gyo == 0) return 65536;
        int answer = (int) Math.floor(gyo/hap*65536);
        return answer;
    }

    static boolean isValidationChar(Character c) {
        if (c < 48 || c > 90) return false;
        else if (c >= 65 && c <= 90) return true;
        else return false;
    }

    public static void main(String[] args) {
        //A = 65, Z = 90 48~57
        //String str1 = "FRANCE";
        //String str2 = "french";
        String str1 = "E=M*C^2";
        String str2 = "E=M*C^2";
        Long start = System.currentTimeMillis();
        System.out.println(solution(str1, str2));
        Long end = System.currentTimeMillis();
        System.out.println("===END=== time:" + (start - end) + "ms");
    }
}
