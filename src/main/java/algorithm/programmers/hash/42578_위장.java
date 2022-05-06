package algorithm.programmers.hash;

import java.util.HashMap;

class Lesson42578 {
    static int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> map = new HashMap<>();
        for (String[] cloth : clothes)
            map.put(cloth[1], map.getOrDefault(cloth[1], 1) + 1);

        for(String a : map.keySet()) {
            answer *= map.get(a);
        }

//        Iterator<Integer> a = map.values().iterator();
//        while (a.hasNext()) {
//            answer *= a.next().intValue();
//        }

        return answer-1;
    }

    public static void main(String[] args) {
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}; // 5
        String[][] clothes2 = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}; // 3
        System.out.println("===START===");
        System.out.println(solution(clothes));
        //System.out.println(solution(clothes2));
        System.out.println("===END===");
    }
}
