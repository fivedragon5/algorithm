package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Lesson92341 {

    static int limit = 1439;

    static int[] solution(int[] fees, String[] records) {
        HashMap<String, String> log = new HashMap<>();
        Map<String, Integer> feesList = new TreeMap<String, Integer>();

        for (String record : records) {
            String[] split = record.split(" ");
            if (split[2].equals("IN")) {
                int sum = (limit - timeToInt(split[0])) + feesList.getOrDefault(split[1], 0);
                log.put(split[1], split[0]);
                feesList.put(split[1], sum);
            }
            else {
                int currentSum = feesList.get(split[1]);
                int resetTime = timeToInt(log.get(split[1]));
                int addTime = timeToInt(split[0]);
                feesList.put(split[1], currentSum - (limit - resetTime) + addTime - resetTime);
                log.remove(split[1]);
            }
        }

        int[] answer = new int[feesList.size()];
        int index = 0;

        for (String carNumber : feesList.keySet()) {
            //int a = fees[0]; //기본요금
            int usage = feesList.get(carNumber) - fees[0];
            int fee = usage > 0 ? fees[1] + (int) Math.ceil((float) usage / fees[2]) * fees[3] : fees[1];
            answer[index] = fee;
            index++;
        }

        return answer;
    }

    static int timeToInt(String time) {

        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    public static void main(String[] args) {

        //기본시간(분) : 기본 요금(원) : 단위시간(분) : 단위요금(원)
        //1~1439     : 0 ~ 100000  : 1 ~ 1439   : 1 ~ 10000
        //단위시간 나누어떨어지지 않으면 올림 1439 -

        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        //result 14600,34400,5000
        System.out.println("===START===");
        System.out.println(solution(fees, records));
        System.out.println("===END===");
    }
}
