package code.programmers.normal;


import java.util.HashMap;

class Lesson17677 {
    static int solution(String str1, String str2) {
        HashMap<String, Integer> str1Map = new HashMap<>();
        double gyo = 0;
        double hap = 0;

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for(int i = 0 ; i < str1.length()-1 ; i ++) {
            if (isValidationAlphabet(str1.charAt(i)) && isValidationAlphabet(str1.charAt(i+1))) {
                String temp = Character.toString(str1.charAt(i)) + Character.toString(str1.charAt(i+1));
                str1Map.put(temp, str1Map.getOrDefault(temp, 0) + 1);
                hap++;
            }
        }

        for(int i = 0 ; i < str2.length()-1 ; i ++) {
            if (isValidationAlphabet(str2.charAt(i)) && isValidationAlphabet(str2.charAt(i+1))) {
                String temp = Character.toString(str2.charAt(i)) + Character.toString(str2.charAt(i+1));
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

    static boolean isValidationAlphabet(Character c) {
        if (c < 48 || c > 90) return false;
        else if (c >= 65 && c <= 90) return true;
        else return false;
    }

    /**
     * 
     * 1. AUB = (A원소갯수 + B원소갯수) - (AnB)
     *      - 위 공식을 적용하여 반복문을 줄임
     *      - str1의 집합을 구하고 str2의 집합을 구하면서
     *        합집합과 교집합의 수를 계속 카운팅
     *
     * 2. TEST CASE 5,13번 주의
     *      - (교집합 = 0, 합집합 != 0) 일경우
     *      - (교집합 = 0, 합집합 = 0) 일경우
     *
     * 3. Character.isAlphabetic()사용가능
     *      - 구현한 isValidationAlphabet()대체 가능
     *        String.toUpperCase()사용후 ASCII code 값을 비교 구현
     *
     * 4. boxing unboxing
     *      - Integer -> int, char -> String 등등 유의
     */
    public static void main(String[] args) {
        System.out.println((int) Math.floor(0/1*65536));
        Character.isAlphabetic('A');
        //A = 65, Z = 90 48~57
        //String str1 = "FRANCE";
        //String str2 = "french";
        String str1 = "E=M*C^2";
        String str2 = "E=M*C^2";
        str1 = "aAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdaffpoqm84*484adsdsadsadsadASADZXVZXC4";
        str2 = "bsdadaAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdafaAbBcCdDeEFfasdfsdafsdafsdafQAA+sda/fs4adf4sadsadsad";
        Long start = System.currentTimeMillis();
        System.out.println(solution(str1, str2));
        Long end = System.currentTimeMillis();
        long c = end - start;
        System.out.println("===END=== time:" + c + "ms");
    }
}
