package algorithm.code.programmers;

import java.util.ArrayList;
import java.util.Arrays;

class Lesson68645 {
    static int[] solution(int n) {
        int end = 0;
        int number = 2;
        int top = 1;
        int bottom = n-1;
        int x = 0;
        int y = 0;


        ArrayList<int[]> snailList = new ArrayList<>();
        int direction = 0; // 0:아래, 1:오른쪽, 2:위 왼쪽으로 갈 경우는 X

        for (int i = 0; i < n; i++) {
            snailList.add(new int[i+1]); //각 List에 int배열을 셋팅
            end += i+1; //최대값 셋팅
        }
        
        //0,0 셋팅
        snailList.get(y)[x] = 1;

        while(number <= end) {
            if (direction == 0) {
                //아래로 이동
                snailList.get(++y)[x] = number;
                if (bottom <= y) {
                    //바닥에 닿을경우
                    direction++;
                    bottom--;
                }
            }
            else if (direction == 2) {
                //좌상향 으로 이동
                snailList.get(--y)[--x] = number;
                //true가 나올경우 우측 condition은 확인X
                if (top >= y || snailList.get(y-1)[x-1] != 0) {
                    //위에 닿을 경우
                    direction = 0;
                    top++;
                }
            }
            else {
                //오른쪽으로 이동
                snailList.get(y)[++x] = number;
                if (bottom < x || snailList.get(y)[x+1] != 0) {
                    direction++;
                }
            }
            number++;
        }

        int[] answer = new int[end];
        int index = 0;
        for (int[] array : snailList) {
            for (int item : array) {
                answer[index++] = item;
            }
        }
        //print(snailList);

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;

        System.out.println("===START===");
        System.out.println(solution(n));
        System.out.println("===END===");
    }

    static public void print(ArrayList<int[]> snailList) {
        System.out.println("===== print");
        for(int[] array : snailList) {
            System.out.println(Arrays.toString(array));
        }
    }
}
