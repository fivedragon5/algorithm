package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem14891 {

    static ArrayList<ArrayList<String>> gearList;

    public static void main(String srgs[]) throws IOException {
        /**
         * 1, 2, 4, 8점
         * 3번, 9번
10101111
01111101
11001110
00000010
2
3 -1
1 1
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        gearList = new ArrayList<>();
        gearList.add(new ArrayList<>());
        
        //gear정보를 list에 담기
        for(int i = 0; i < 4; i++) {
            String[] gear = st.nextToken().split("");
            ArrayList<String> tempList = new ArrayList<>();
            for (String g : gear) {
                tempList.add(g);
            }
            gearList.add(tempList);
            st = new StringTokenizer(br.readLine());
        }

//        for (ArrayList a : gearList) {
//            System.out.println(a.toString());
//        }
//
//        System.out.println("");
        
        //명령 갯수
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            int[] workList = {2,0,0,0,0};
            st = new StringTokenizer(br.readLine());
            //기어 번호
            int gearNumber = Integer.parseInt(st.nextToken());
            //회전 방향 (1:시계, -1:반시계)
            int rotation = Integer.parseInt(st.nextToken());
            workList[gearNumber] = rotation;

            //기어 들의 회전 작업 기록
            nextGear(gearNumber, workList);
            preGear(gearNumber, workList);

            //System.out.println(Arrays.toString(workList) + "작업순서" + i);

            //기어 회전
            rotateGear(workList);

//            for (ArrayList a : gearList) {
//                System.out.println(a.toString());
//            }
        }

        int sum = 0;
        for (int i = 1; i <= 4 ; i++) {
            String c = gearList.get(i).get(0);
            if("1".equals(c)) {
                sum += Math.pow(2, i-1);
            }
        }
        System.out.println(sum);
    }

    public static void nextGear(int gearNumber, int[] workList) {
        if(gearNumber > 3) return;
        if(!gearList.get(gearNumber).get(2).equals(gearList.get(gearNumber+1).get(6))) {
            workList[gearNumber+1] = workList[gearNumber] * -1;
            nextGear(gearNumber+1, workList);
        }
    }

    public static void preGear(int gearNumber, int[] workList) {
        if(gearNumber < 2) return;
        if(!gearList.get(gearNumber).get(6).equals(gearList.get(gearNumber-1).get(2))) {
            workList[gearNumber-1] = workList[gearNumber] * -1;
            preGear(gearNumber-1, workList);
        }
    }

    public static void rotateGear(int[] workList) {
        for(int i = 1; i < workList.length; i++) {
            int rotation = workList[i];
            if(rotation == 1) {
                String temp = gearList.get(i).remove(7);
                gearList.get(i).add(0, temp);
            }
            else if (rotation == -1){
                String temp = gearList.get(i).remove(0);
                gearList.get(i).add(temp);
            }
        }
    }
}
