package algorithm.programmers;

class lesson72414 {
    static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int answerInt = 0;
        int playTime = stringToSec(play_time);
        int advTime = stringToSec(adv_time);
        int[] viewArray = new int[playTime + 1];
        //logs의 시간을 초단위로 변환 1분 60 1시간 3600 100시간 360000초 int로 충분
        for (String log : logs) {
            String[] time = log.split("-");
            int start = stringToSec(time[0]);
            int end = stringToSec(time[1]);
            viewArray[start]++;
            viewArray[end]--;

        }

        for (int i = 1 ; i < playTime ; i++) {
            viewArray[i] += viewArray[i-1];
        }
        
        //초기값 셋팅
        long maxSum = 0;
        long sum = 0;
        for (int i = 0 ; i < advTime ; i ++) sum += viewArray[i];
        maxSum = sum;

        for (int i = advTime ; i < playTime ; i++) {
            sum -= viewArray[i-advTime];
            sum += viewArray[i];

            if (sum > maxSum) {
                answerInt = i-advTime+1;
                maxSum = sum;
            }
        }
        return secToString(answerInt);
    }

    static int stringToSec(String str) {
        String[] strArr = str.split(":");
        return (Integer.parseInt(strArr[0]) * 60 * 60) + (Integer.parseInt(strArr[1]) * 60) + Integer.parseInt(strArr[2]);
    }

    static String secToString(int sec) {
        String str = "";
        int hour = sec/3600;
        int minute = (sec-(hour*3600))/60;
        int second = sec-(hour*3600)-(minute*60);
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println("===START===");
        System.out.println(solution(play_time, adv_time, logs));
        System.out.println("===END===");
    }
}
