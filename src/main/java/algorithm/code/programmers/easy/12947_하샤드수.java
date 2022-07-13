package algorithm.code.programmers.easy;

class Lesson12947 {
    static boolean solution(int x) {
        String numberStr = String.valueOf(x);

        int sum = 0;
        for(int i = 0; i < numberStr.length() ; i ++)
            sum += Integer.parseInt(String.valueOf(numberStr.charAt(i)));

        if(x%sum == 0) return true;

        return false;
    }

    public static void main(String[] args) {
        int number = 10;
        System.out.println("===START===");
        System.out.println(solution(number));
        System.out.println("===END===");
    }
}
