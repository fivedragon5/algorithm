package algorithm.code.programmers.study;

import java.util.HashMap;

    /*
        U,D,R,L : 상,하,우,좌
        좌표평면 (0,0) 시작 (x,y) -5 <= x,y <= 5
        dirs 500 이하 자연수  1 ~ 500
     */

class Lesson49994 {
    //상 우 하 좌
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int currentX = 0;
    static int currentY = 0;

    static int solution(String dirs) {
        int answer = 0;
        HashMap<String, boolean[]> point = new HashMap<>();
        String[] commands = dirs.split("");

        int x = 0;
        int y = 0;
        int dir = 0;

        for (int i = 0; i < commands.length; i++) {

            if(commands[i].equals("U")) dir = 0;
            else if(commands[i].equals("R")) dir = 1;
            else if(commands[i].equals("D")) dir = 2;
            else if(commands[i].equals("L")) dir = 3;

            x = currentX + dx[dir];
            y = currentY + dy[dir];

            if (Math.abs(x) > 5 || Math.abs(y) > 5) {
                continue;
            }

            //System.out.print("방향:" + dir + "/현재 경로 (" + currentX + "," + currentY + ") ");
            //System.out.println("다음 경로 (" + x + "," + y + ")");

            //x,y값을 조회 후 처음 가는 길이면 answer++ (getOrDefault로 조회 (value가 없을 수 있음))
            //조회 할때 map x|y 하고 value는 {0,0,0,0} 각각의 원소는 상,하,우,좌 의 길을 의미한다.
            boolean[] check = point.getOrDefault(currentX+"|"+currentY, new boolean[4]);

            if (!check[dir]) {
                boolean[] nextCheck = point.getOrDefault(x+"|"+y, new boolean[4]);
                int reversDir = (dir + 2) % 4;
                check[dir] = true;
                nextCheck[reversDir] = true;
                answer++;

                point.put(currentX+"|"+currentY, check);
                point.put(x+"|"+y, nextCheck);

            }
            currentX = x;
            currentY = y;
        }

        return answer;
    }

    public static void main(String[] args) {
        //String dirs = "ULURRDLLU";
        String dirs = "LULLLLLLU";
        System.out.println("===START===");
        System.out.println(solution(dirs));
        System.out.println("===END===");
    }
}
