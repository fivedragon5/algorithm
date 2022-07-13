package algorithm.code.programmers.syudy;

class Lesson92335 {
    static int solution(int n, int k) {
        int answer = 0;
        String convertStr = Integer.toString(n, k);
        String[] splitZero = convertStr.split("0");

        for (int i = 0; i < splitZero.length; i++) {
            if (!splitZero[i].equals("0") && splitZero[i].length() > 0) {
                answer++;
                long temp = Long.parseLong(splitZero[i]);
                if (temp == 1) answer--;
                for (int j = 2; j <= (int)Math.sqrt(temp); j++) {
                    System.out.println(temp + "//i: " + j);
                    if (temp%j == 0 || temp%2 == 0)  {
                        answer--;
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        //int n = 110011;
        //int k = 10; // k 3 ~ 10
        int n = 1000000;
        int k = 2; // k 3 ~ 10

        System.out.println("===START===");
        System.out.println(solution(n, k));
        System.out.println("===END===");
    }
}
