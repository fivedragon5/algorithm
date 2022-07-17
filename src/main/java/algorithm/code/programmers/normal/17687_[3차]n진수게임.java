package algorithm.code.programmers.normal;

class Lesson17687 {
    static public String solution(int n, int t, int m, int p) {
        StringBuffer answer = new StringBuffer();
        int number = 0;
        int turn = 0;
        while(answer.length() < t) {
            String convertNumber = Integer.toString(number, n).toUpperCase();
            for (int i = 0 ; i < convertNumber.length() ; i ++) {
                if(turn%m == p-1) {
                    answer.append(convertNumber.charAt(i));
                    if(answer.length() >= t) break;
                }
                turn++;
            }
            number++;
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int n = 2;
        int t = 4;
        int m = 2;
        int p = 1;
        System.out.println("===START===");
        System.out.println(solution(n, t, m, p));
        System.out.println("===END===");
    }
}
