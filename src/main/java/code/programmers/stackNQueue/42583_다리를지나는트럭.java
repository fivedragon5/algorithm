package code.programmers.stackNQueue;

import java.util.ArrayList;
import java.util.List;

class Lesson42583{
    static int solution(int bridge_length, int weight, int[] truck_weights) {
        int truckNumber = 0;
        int finshedCount = 0;
        int time = 0;
        List<Bridge> bridgeList = new ArrayList<>();

        while (truck_weights.length > finshedCount) {

            int currentBridgeWeight = 0;
            boolean isFlag = false;
            for(Bridge bridge : bridgeList) {
                bridge.position += 1;
                if(bridge.position >= bridge_length) {
                    isFlag = true;
                    finshedCount++;
                }
                else currentBridgeWeight += bridge.weight;
            }

            if(isFlag) bridgeList.remove(0);

            if(truckNumber < truck_weights.length) {
                if(bridge_length > bridgeList.size()
                        && weight >= currentBridgeWeight + truck_weights[truckNumber]) {
                    bridgeList.add(new Bridge(truck_weights[truckNumber]));
                    truckNumber++;
                }
            }
            time++;
        }

        return time;
    }

    static class Bridge {
        int weight;
        int position;
        Bridge(int weight) {
            this.weight = weight;
            this.position = 0;
        }
    }

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6}; //8

        int bridge_length2 = 100;
        int weight2 = 100;
        int[] truck_weights2 = {10,10,10,10,10,10,10,10,10,10}; // 110

        System.out.println("===START===");
        //System.out.println(solution(bridge_length, weight, truck_weights));
        System.out.println(solution(bridge_length2, weight2, truck_weights2));
        System.out.println("===END===");
    }
}
