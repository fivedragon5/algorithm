package algorithm.code.programmers.study;

import java.util.ArrayList;
import java.util.List;

class Lesson17680 {
    static int solution(int cacheSize, String[] cities) {
        int miss = 0;
        int hit = 0;
        boolean isCheck = false;

        List<String> list = new ArrayList<>();

        for (String city : cities) {

            isCheck = false;
            int index = 0;
            String cityLower = city.toLowerCase();

            for (int i = 0; i < list.size(); i++) {

                if(list.size() == 0) continue;

                if (list.get(i).equals(cityLower)) {
                    index = i;
                    hit++;
                    isCheck = true;
                    break;
                }
            }
            if (isCheck) {
                list.remove(index);
                list.add(cityLower);
            }
            else {
                miss++;
                list.add(cityLower);
                if(list.size() > cacheSize) {
                    list.remove(0);
                }
            }
        }

        return miss * 5 + hit;
    }

    public static void main(String[] args) {
        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        //String[] cities = {};
        System.out.println("===START===");
        System.out.println(solution(cacheSize, cities));
        System.out.println("===END===");
    }
}
