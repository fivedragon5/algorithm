package algorithm.code.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Lesson87377 {
    /**
        Ax + By + C = 0로 표현 할 수 있는 n개의 직선이 주어질 때

     제한사항)
        2 <= line.length <= 1,000
        line[N].length = 3
        line[N] = {A, B, C}
        -100,000 <= A,B,C <= 100,000

        A = 0이면서 B = 0인 경우는 주어지지 않습니다.
        무수히 많은 교점이 생기는 직선 쌍은 주어지지 않습니다.

        정답은 1,000 * 1,000 크기 이내에서 표현됩니다.
        별이 한 개 이상 그려지는 입력만 주어집니다.

        이때 모든 별을 포함하는 최소 사각형을 return 하도록 solution 함수를 완성해주세요.

        교점
            Ax + By + E = 0
            Cx + Dy + F = 0

            x = BF - ED / AD - BC
            y = EC - AF / AD - BC

         AD - BC = 0 평행 OR 일치

     */
    static String[] solution(int[][] line) {

        Set<String> set = new HashSet<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;

        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        long under = 0;

        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i+1; j < line.length; j++) {
                under = (long)line[i][0] * (long)line[j][1] - (long)line[i][1] * (long)line[j][0];
                if(under != 0) {
                    double x = (double) ((long)line[i][1] * (long)line[j][2] - (long)line[i][2] * (long)line[j][1]) / under;
                    double y = (double) ((long)line[i][2] * (long)line[j][0] - (long)line[i][0] * (long)line[j][2]) / under;
                    if (x % 1 == 0 && y % 1 == 0) {
                        minX = Math.min(minX,(long) x);
                        maxX = Math.max(maxX,(long) x);

                        minY = Math.min(minY,(long) y);
                        maxY = Math.max(maxY,(long) y);

                        set.add((long)x + "," + (long)y);
                    }
                }
            }
        }

        int lengthX =  (int) (maxX - minX + 1);
        int lengthY =  (int) (maxY - minY + 1);

        String[] answer = new String[lengthY];
        String[][] answerArray = new String[lengthY][lengthX];
        for (String[] fill : answerArray) {
            Arrays.fill(fill, ".");
        }

        /**
         1. set에 있는 좌표들을 순회
         2. 순회 하면서 x-moveX, y-moveY
         3. 값을 answerArray 좌표에 . -> * 로 변경해준다
         4. 변경해준 값을 String[]로 변환한 뒤 return
         */
        for (String point : set) {
            String[] points = point.split(",");
            int pointY = (int) (Long.parseLong(points[1]) - minY);
            int pointX = (int) (Long.parseLong(points[0]) - minX);
            answerArray[pointY][pointX] = "*";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lengthY; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < lengthX; j++) {
                sb.append(answerArray[i][j]);
            }
            answer[lengthY-i-1] = sb.toString();
        }

        //System.out.println(Arrays.toString(answer));

        return answer;
    }

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        //int[][] line = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
        //int[][] line = {{1, -1, 0}, {2, -1, 0}};
        //int[][] line = {{1, -1, 0}, {2, -1, 0}, {4, -1, 0}};

        System.out.println("===START===");
        System.out.println(solution(line));
        System.out.println("===END===");
    }
    /**
     답 1
     ["....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"]

     답 2
     ["*.*"]

     답 3
     ["*"]

     답 4
     ["*"]
     */
}
