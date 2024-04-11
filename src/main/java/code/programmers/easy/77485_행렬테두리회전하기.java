package code.programmers.easy;

class Lesson77485 {
    static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] sqare = new int[rows][columns];
        int answerIndex = 0;
        int number = 1;

        for(int row = 0 ; row < rows ; row++) {
            for(int column = 0; column < columns ; column++) {
                sqare[row][column] = number;
                number++;
            }
        }

        for(int[] query : queries) {
            int min = number;
            int x1 = query[0]-1;
            int y1 = query[1]-1;
            int x2 = query[2]-1;
            int y2 = query[3]-1;
            int temp1 = 0;
            int temp2 = 0;
            int direction = 1;
            int xPointer = x1;
            int yPointer = y1;
            temp1 = sqare[xPointer][yPointer];

            while (direction < 5) {
                if (direction == 1) {
                    yPointer++;
                    if(yPointer == y2) direction++;
                }
                else if (direction == 2) {
                    xPointer++;
                    if(xPointer == x2) direction++;
                }
                else if (direction == 3) {
                    yPointer--;
                    if(yPointer == y1) direction++;
                }
                else if (direction == 4) {
                    xPointer--;
                    if(xPointer == x1) direction++;
                }

                temp2 = sqare[xPointer][yPointer];
                sqare[xPointer][yPointer] = temp1;
                temp1 = temp2;

                if(min > temp2) min = temp2;

                if (xPointer == x1 && yPointer == y1) {
                    answer[answerIndex] = min;
                    answerIndex++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        //int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[][] queries = {{2,3,3,4},{2,3,3,4},{2,3,3,4},{2,3,3,4}};

        int rows2 = 3;
        int columns2 = 3;
        int[][] queries2 = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};

//        int rows2 = 6;
//        int columns2 = 6;
//        int[][] queries2 = {{1,1,6,6}};

        System.out.println("===START===");
        System.out.println(solution(rows, columns, queries));
        //System.out.println(solution(rows2, columns2, queries2));
        System.out.println("===END===");
    }
}
