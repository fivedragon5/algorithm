package code.programmers.study;

class Lesson68936 {
    static int[] solution(int[][] arr) {
        int[] answer = {};

        //제곱 수
        int n = arr.length;

        for(int i = n; i > 0; i--) {

        }

        return answer;
    }

    public static void main(String[] args) {
        /*
            1. arr의 행의 개수는 1 이상 1024 이하이며, 2의 거듭 제곱수 형태를 하고 있습니다. 즉, arr의 행의 개수는 1, 2, 4, 8, ..., 1024 중 하나입니다.
            2. arr의 각 행의 길이는 arr의 행의 개수와 같습니다. 즉, arr은 정사각형 배열입니다.
            3. arr의 각 행에 있는 모든 값은 0 또는 1 입니다
         */
        int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}}; // [4,9]
        //int[][] arr = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}}; // [10,15]
        System.out.println("===START===");
        System.out.println(solution(arr));
        System.out.println("===END===");
    }
}
