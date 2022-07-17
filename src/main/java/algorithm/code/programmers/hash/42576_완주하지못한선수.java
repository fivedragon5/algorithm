package algorithm.code.programmers.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

class Lesson42576 {

    static String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        System.out.println(Arrays.toString(participant));
        System.out.println(Arrays.toString(completion));
        for(int i = 0; i < completion.length; i ++) {
            if(!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[completion.length];
    }

    static String solution2(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        for(String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        for(String name : completion) {
            map.put(name, map.get(name) - 1);
        }

//        for(String key : map.keySet()) {
//            if(map.get(key) != 0) {
//                answer = key;
//                break;
//            }
//        }

        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            if(entry.getValue() != 0) {
                answer = entry.getKey();
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = {"josipa", "filipa", "marina", "nikola"};
        System.out.println("===START===");
        //System.out.println(solution(participant, completion));
        System.out.println(solution2(participant, completion));
        System.out.println("===END===");
    }
}
